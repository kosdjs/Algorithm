package BOJ_2225

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = Array(N + 1){LongArray(K + 1){0} }
    for(j in 0..K){
        dp[0][j] = 1
    }
    for(i in 1..N){
        for(j in 1..K){
            dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000
        }
    }
    println(dp[N][K])
}