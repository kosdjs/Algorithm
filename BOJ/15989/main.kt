package BOJ_15989

fun main(){
    val br = System.`in`.bufferedReader()
    val dp = Array(10001){ IntArray(3) {0} }
    dp[1] = intArrayOf(1, 1, 1)
    dp[2] = intArrayOf(1, 2, 2)
    dp[3] = intArrayOf(1, 2, 3)
    for(i in 4..10000){
        dp[i][0] = 1
        dp[i][1] = dp[i-1][0] + dp[i-2][1]
        dp[i][2] = dp[i-1][0] + dp[i-2][1] + dp[i-3][2]
    }
    for(i in 1..br.readLine().toInt()){
        println(dp[br.readLine().toInt()][2])
    }
}