package BOJ_2591

fun main(){
    val br = System.`in`.bufferedReader()
    val numStr = br.readLine()
    val num = IntArray(numStr.length + 1)
    for(i in 1..numStr.length){
        num[i] = numStr[i - 1] - '0'
    }
    val dp = IntArray(numStr.length + 1){0}
    dp[0] = 1
    dp[1] = 1
    for(i in 2..numStr.length){
        if(num[i] != 0){
            dp[i] += dp[i-1]
        }
        if(num[i-1] > 0 && num[i-1] * 10 + num[i] <= 34){
            dp[i] += dp[i-2]
        }
    }
    println(dp[numStr.length])
}