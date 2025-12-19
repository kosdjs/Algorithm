package BOJ_14938

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val m = nextInt()
    val r = nextInt()
    val t = IntArray(n + 1)
    val dist = Array(n + 1){IntArray(n + 1){Int.MAX_VALUE} }
    for(i in 1..n){
        t[i] = nextInt()
    }
    repeat(r){
        val u = nextInt()
        val v = nextInt()
        val d = nextInt()
        dist[u][v] = d
        dist[v][u] = d
    }
    for(k in 1..n){
        for(i in 1..n){
            for(j in 1..n){
                if(dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE){
                    dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j])
                }
            }
        }
    }
    var answer = 0
    for(i in 1..n){
        var count = t[i]
        for(j in 1..n){
            if(i == j) continue
            if(dist[i][j] <= m){
                count += t[j]
            }
        }
        answer = maxOf(answer, count)
    }
    println(answer)
}