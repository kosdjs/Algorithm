package BOJ_1865

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val TC = nextInt()
    repeat(TC){
        val N = nextInt()
        val M = nextInt()
        val W = nextInt()
        val dist = IntArray(N + 1)
        val edges = ArrayList<IntArray>()
        repeat(M){
            val S = nextInt()
            val E = nextInt()
            val T = nextInt()
            edges.add(intArrayOf(S, E, T))
            edges.add(intArrayOf(E, S, T))
        }
        repeat(W){
            val S = nextInt()
            val E = nextInt()
            val T = nextInt()
            edges.add(intArrayOf(S, E, T * -1))
        }
        repeat(N - 1){
            for((S, E, T) in edges){
                if(dist[E] > dist[S] + T) dist[E] = dist[S] + T
            }
        }
        var possible = false
        for((S, E, T) in edges){
            if(dist[E] > dist[S] + T) {
                possible = true
                break
            }
        }
        println(if (possible) "YES" else "NO")
    }
}