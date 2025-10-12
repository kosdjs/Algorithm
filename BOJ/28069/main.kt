package BOJ_28069

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = IntArray(N + 1){Int.MAX_VALUE}
    dp[0] = 0
    for(i in 0..N){
        if(i + 1 <= N){
            dp[i + 1] = minOf(dp[i + 1], dp[i] + 1)
        }
        if(i + i / 2 <= N){
            dp[i + i / 2] = minOf(dp[i + i / 2], dp[i] + 1)
        }
    }
    if(dp[N] <= K){
        println("minigimbob")
    } else {
        println("water")
    }
}