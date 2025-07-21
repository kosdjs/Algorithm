package BOJ_10835

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val leftCards = IntArray(N + 1)
    val rightCards = IntArray(N + 1)
    var st = StringTokenizer(br.readLine())
    for(i in 1..N){
        leftCards[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 1..N){
        rightCards[i] = st.nextToken().toInt()
    }
    val dp = Array(N + 2){IntArray(N + 2){ 0 }}
    for(i in N downTo 1){
        for(j in N downTo 1){
            dp[i][j] = maxOf(dp[i + 1][j], dp[i + 1][j + 1])
            if(leftCards[i] > rightCards[j]){
                dp[i][j] = maxOf(dp[i][j], dp[i][j + 1] + rightCards[j])
            }
        }
    }
    println(dp[1][1])
}