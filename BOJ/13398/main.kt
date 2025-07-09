package BOJ_13398

import java.util.StringTokenizer
import kotlin.math.max

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n)
    for(i in 0 until n){
        arr[i] = st.nextToken().toInt()
    }
    val dp = Array(n){IntArray(2)}
    var answer = Int.MIN_VALUE
    dp[0][0] = arr[0]
    dp[0][1] = max(answer, arr[0])
    if(dp[0][0] > answer) answer = dp[0][0]
    if(dp[0][1] > answer) answer = dp[0][1]
    for(i in 1 until n){
        dp[i][0] = max(dp[i-1][0] + arr[i], arr[i])
        if(dp[i][0] > answer) answer = dp[i][0]
        dp[i][1] = max(dp[i-1][0], dp[i-1][1] + arr[i])
        if(dp[i][1] > answer) answer = dp[i][1]
    }
    println(answer)
}