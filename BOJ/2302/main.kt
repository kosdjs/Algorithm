package BOJ_2302

import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    val dp = IntArray(N+1)
    dp[0] = 1
    dp[1] = 1
    for(i in 2..N){
        dp[i] = dp[i-1] + dp[i - 2]
    }
    var answer = 1
    var last = 0
    for(i in 1..M){
        val vip = br.readLine().toInt()
        answer *= dp[vip - last - 1]
        last = vip
    }
    answer *= dp[N - last]

    println(answer)
}