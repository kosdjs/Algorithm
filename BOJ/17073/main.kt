package BOJ_17073

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val W = nextInt()
    val edges = IntArray(N + 1)
    repeat(N - 1){
        val U = nextInt()
        val V = nextInt()
        edges[U]++
        edges[V]++
    }
    var leafCount = 0
    for(i in 2..N){
        if(edges[i] == 1) leafCount++
    }
    println(W.toDouble() / leafCount)
}