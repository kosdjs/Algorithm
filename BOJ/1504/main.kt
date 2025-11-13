package BOJ_1504

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun readInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = readInt()
    val E = readInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    for(i in 0..N){
        graph.add(arrayListOf())
    }
    val dist = Array(N + 1){ IntArray(N + 1){Int.MAX_VALUE} }
    for(i in 0 until E){
        val a = readInt()
        val b = readInt()
        val c = readInt()
        graph[a].add(intArrayOf(b, c))
        graph[b].add(intArrayOf(a, c))
    }
    val v1 = readInt()
    val v2 = readInt()
    dijkstra(1, dist, graph)
    dijkstra(v1, dist, graph)
    dijkstra(v2, dist, graph)
    var answer = Int.MAX_VALUE
    if(dist[1][v1] != Int.MAX_VALUE && dist[v1][v2] != Int.MAX_VALUE && dist[v2][N] != Int.MAX_VALUE){
        answer = dist[1][v1] + dist[v1][v2] + dist[v2][N]
    }
    if(dist[1][v2] != Int.MAX_VALUE && dist[v2][v1] != Int.MAX_VALUE && dist[v1][N] != Int.MAX_VALUE){
        answer = minOf(answer, dist[1][v2] + dist[v2][v1] + dist[v1][N])
    }
    println(if(answer != Int.MAX_VALUE) answer else -1)
}

fun dijkstra(start: Int, dist: Array<IntArray>, graph:ArrayList<ArrayList<IntArray>>){
    val queue = PriorityQueue<IntArray>{o1, o2 -> o1[1] - o2[1]}
    dist[start][start] = 0
    queue.add(intArrayOf(start, 0))
    while (queue.isNotEmpty()){
        val (cur, cost) = queue.poll()
        if(dist[start][cur] < cost){
            continue
        }
        for((next, newCost) in graph[cur]){
            val newDist = cost + newCost
            if(dist[start][next] > newDist){
                dist[start][next] = newDist
                queue.add(intArrayOf(next, newDist))
            }
        }
    }
}