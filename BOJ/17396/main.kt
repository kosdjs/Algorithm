package BOJ_17396

import java.util.PriorityQueue

data class Node(val idx: Int, val cost: Long): Comparable<Node>{
    override fun compareTo(other: Node): Int {
        return if(this.cost < other.cost) -1 else if(this.cost == other.cost) 0 else 1
    }
}

fun main(){
    val br = System.`in`.bufferedReader()
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val sight = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val dist = LongArray(N){ Long.MAX_VALUE}
    val graph = Array(N){ ArrayList<Pair<Int, Int>>() }
    repeat(M){
        val (a, b, t) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(b to t)
        graph[b].add(a to t)
    }
    val pq = PriorityQueue<Node>()
    dist[0] = 0
    pq.add(Node(0, 0))
    while (pq.isNotEmpty()){
        val (cur, cost) = pq.poll()
        if(cur == N - 1) break
        if(cost > dist[cur]) continue
        for((next, weight) in graph[cur]){
            if(sight[next] == 1 && next != N - 1) continue
            if(dist[next] > dist[cur] + weight){
                dist[next] = dist[cur] + weight
                pq.add(Node(next, dist[next]))
            }
        }
    }
    if(dist[N - 1] == Long.MAX_VALUE){
        println(-1)
    } else {
        println(dist[N - 1])
    }
}