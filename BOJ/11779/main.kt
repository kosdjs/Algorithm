package BOJ_11779

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val m = nextInt()
    val adjList = Array<MutableList<IntArray>>(n + 1){mutableListOf()}
    repeat(m){
        val u = nextInt()
        val v = nextInt()
        val d = nextInt()
        adjList[u].add(intArrayOf(v, d))
    }
    val dist = IntArray(n + 1){Int.MAX_VALUE}
    val prev = IntArray(n + 1)
    val start = nextInt()
    val end = nextInt()
    dist[start] = 0
    val queue = PriorityQueue<IntArray>{o1, o2 -> o1[1] - o2[1]}
    queue.add(intArrayOf(start, 0))
    while(queue.isNotEmpty()){
        val (u, d) = queue.poll()
        if(dist[u] != d) continue
        for((v, w) in adjList[u]){
            if(dist[v] > dist[u] + w){
                dist[v] = dist[u] + w
                prev[v] = u
                queue.add(intArrayOf(v, dist[v]))
            }
        }
    }
    val path = mutableListOf<Int>()
    var v = end
    while(v != 0){
        path.add(v)
        v = prev[v]
    }
    path.reverse()
    println(dist[end])
    println(path.size)
    for(u in path){
        print("$u ")
    }
}