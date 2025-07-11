package BOJ_15486

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val dp = IntArray(N + 50){0}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        val T = st.nextToken().toInt()
        val P = st.nextToken().toInt()
        if(i > 0 && dp[i] < dp[i-1]) dp[i] = dp[i-1]
        if(dp[i + T] < dp[i] + P) dp[i + T] = dp[i] + P
    }
    if(dp[N] < dp[N-1]) dp[N] = dp[N-1]
    println(dp[N])
}