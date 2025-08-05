package BOJ_5972

import java.util.PriorityQueue

data class Node(val idx: Int, val cost: Int): Comparable<Node> {
    override fun compareTo(other: Node) = this.cost - other.cost
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val dist = IntArray(N + 1) { Int.MAX_VALUE }
    val graph = Array(N + 1) { ArrayList<Pair<Int, Int>>() }
    repeat(M) {
        val (A, B, C) = br.readLine().split(" ").map { it.toInt() }
        graph[A].add(B to C)
        graph[B].add(A to C)
    }
    dist[1] = 0
    val pq = PriorityQueue<Node>()
    pq.add(Node(1, 0))
    while (pq.isNotEmpty()) {
        val (cur, cost) = pq.poll()
        if (dist[cur] < cost) continue
        for ((next, weight) in graph[cur]) {
            if (dist[next] > dist[cur] + weight) {
                dist[next] = dist[cur] + weight
                pq.add(Node(next, dist[next]))
            }
        }
    }
    println(dist[N])
}