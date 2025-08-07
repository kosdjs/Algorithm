package BOJ_14284

import java.util.PriorityQueue
import java.util.StringTokenizer

data class Node(val Idx: Int, val cost: Int): Comparable<Node>{
    override fun compareTo(other: Node): Int {
        return this.cost - other.cost
    }
}

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val graph = Array(n + 1){ ArrayList<Pair<Int, Int>>() }
    repeat(m){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        graph[a].add(b to c)
        graph[b].add(a to c)
    }
    val dist = IntArray(n + 1){Int.MAX_VALUE}
    st = StringTokenizer(br.readLine())
    val s = st.nextToken().toInt()
    val t = st.nextToken().toInt()
    dist[s] = 0
    val pq = PriorityQueue<Node>()
    pq.add(Node(s, 0))
    while (pq.isNotEmpty()){
        val (cur, cost) = pq.poll()
        if(cur == t) break
        if(cost > dist[cur]) continue
        for((next, weight) in graph[cur]){
            if(dist[next] > dist[cur] + weight){
                dist[next] = dist[cur] + weight
                pq.add(Node(next, dist[next]))
            }
        }
    }
    println(dist[t])
}