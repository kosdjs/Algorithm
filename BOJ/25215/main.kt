package BOJ_25215

fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    val dp = Array(s.length + 1){ IntArray(2) }
    dp[0][1] = 1
    for(i in 1..s.length){
        if(s[i - 1].isUpperCase()){
            dp[i][0] = minOf(dp[i - 1][0] + 2, dp[i - 1][1] + 2)
            dp[i][1] = minOf(dp[i - 1][0] + 2, dp[i - 1][1] + 1)
        } else {
            dp[i][0] = minOf(dp[i - 1][0] + 1, dp[i - 1][1] + 2)
            dp[i][1] = minOf(dp[i - 1][0] + 2, dp[i - 1][1] + 2)
        }
    }
    println(dp[s.length].min())
}