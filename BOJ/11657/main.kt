package BOJ_11657

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt():Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val dist = LongArray(N + 1){Long.MAX_VALUE}
    dist[1] = 0
    val edges = ArrayList<IntArray>()
    repeat(M){
        val A = nextInt()
        val B = nextInt()
        val C = nextInt()
        edges.add(intArrayOf(A, B, C))
    }
    repeat(N - 1){
        for((A, B, C) in edges){
            if(dist[A] != Long.MAX_VALUE && dist[B] > dist[A] + C){
                dist[B] = dist[A] + C
            }
        }
    }
    var hasNegativeCycle = false
    for((A, B, C) in edges){
        if(dist[A] != Long.MAX_VALUE && dist[B] > dist[A] + C){
            hasNegativeCycle = true
            break
        }
    }
    if(hasNegativeCycle){
        println("-1")
    } else {
        for(i in 2..N){
            println("${if(dist[i] != Long.MAX_VALUE) dist[i] else -1}")
        }
    }
}