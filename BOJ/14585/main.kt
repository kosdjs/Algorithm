package BOJ_14585

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val dp = Array(301){IntArray(301)}
    var answer = 0
    for(i in 0 until N){
        val x = nextInt()
        val y = nextInt()
        if(x + y < M){
            dp[y][x] = M - x - y
        }
    }
    for(i in 0..300){
        for(j in 0..300){
            if(i > 0 && j > 0){
                dp[i][j] += maxOf(dp[i - 1][j], dp[i][j - 1])
            } else if(i > 0){
                dp[i][j] += dp[i - 1][j]
            } else if(j > 0){
                dp[i][j] += dp[i][j - 1]
            }
            answer = maxOf(answer, dp[i][j])
        }
    }
    println(answer)
}