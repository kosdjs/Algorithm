package BOJ_1238

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val X = nextInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    val revGraph = ArrayList<ArrayList<IntArray>>()
    for(i in 0..N){
        graph.add(arrayListOf())
        revGraph.add(arrayListOf())
    }
    repeat(M){
        val u = nextInt()
        val v = nextInt()
        val dist = nextInt()
        graph[u].add(intArrayOf(v, dist))
        revGraph[v].add(intArrayOf(u, dist))
    }
    var max = Int.MIN_VALUE
    val dist = IntArray(N + 1){Int.MAX_VALUE}
    val revDist = IntArray(N + 1){Int.MAX_VALUE}
    dist[X] = 0
    val queue = PriorityQueue<IntArray>{o1, o2 -> o1[1] - o2[1]}
    queue.add(intArrayOf(X, 0))
    while(queue.isNotEmpty()){
        val (u, curDist) = queue.poll()
        for((v, newDist) in graph[u]){
            if(dist[v] > curDist + newDist){
                dist[v] = curDist + newDist
                queue.add(intArrayOf(v, dist[v]))
            }
        }
    }
    queue.add(intArrayOf(X, 0))
    revDist[X] = 0
    while(queue.isNotEmpty()){
        val(u, curDist) = queue.poll()
        for((v, newDist) in revGraph[u]){
            if(revDist[v] > curDist + newDist){
                revDist[v] = curDist + newDist
                queue.add(intArrayOf(v, revDist[v]))
            }
        }
    }
    for(i in 1..N){
        if(i == X) continue
        max = maxOf(max, dist[i] + revDist[i])
    }
    println(max)
}