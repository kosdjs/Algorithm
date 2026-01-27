package BOJ_20500

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    val mod = 1_000_000_007
    val dp = Array(N + 1){IntArray(3)}
    dp[1][2] = 1
    for(i in 2..N){
        dp[i][0] = (dp[i - 1][2] + dp[i - 1][1]) % mod
        dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod
        dp[i][2] = (dp[i - 1][1] + dp[i - 1][0]) % mod
    }
    println(dp[N][0])
}