package BOJ_2294

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val coin = IntArray(n)
    val dp = IntArray(k + 1){k + 1}
    for(i in 0 until n){
        coin[i] = br.readLine().toInt()
        if(coin[i] <= k){
            dp[coin[i]] = 1
        }
    }
//    coin.sort()
    for(i in 0 until n){
        for(j in coin[i]..k){
            if(dp[j - coin[i]] + 1 < dp[j]){
                dp[j] = dp[j - coin[i]] + 1
            }
        }
    }
    if(dp[k] == k + 1) dp[k] = -1
    println(dp[k])
}