package BOJ_17953

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val dp = Array(N){IntArray(M)}
    for(j in 0 until M){
        for(i in 0 until N){
            dp[i][j] = nextInt()
        }
    }
    for(i in 1 until N){
        for(j in 0 until M){
            var max = 0
            for(k in 0 until M){
                max = maxOf(max, dp[i - 1][k] + if(j == k) (dp[i][j] / 2) else dp[i][j])
            }
            dp[i][j] = max
        }
    }
    println(dp[N - 1].maxOf { it })
}