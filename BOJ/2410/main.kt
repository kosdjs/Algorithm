package BOJ_2410

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)
    dp[1] = 1
    for(i in 2..n){
        if(i % 2 == 0){
            dp[i] = dp[i - 1] + dp[i / 2]
        } else {
            dp[i] = dp[i - 1]
        }
        dp[i] %= 1000000000
    }
    println(dp[n])
}