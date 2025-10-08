package BOJ_3933

import kotlin.math.pow

fun main(){
    val br = System.`in`.bufferedReader()
    val max = 2.0.pow(15).toInt()
    var num = 1
    val dp = Array(5){ IntArray(max) }
    dp[0][0] = 1
    while(num * num < max){
        val square = num * num
        for(i in 1..4){
            for(j in square until max){
                dp[i][j] += dp[i - 1][j - square]
            }
        }
        num++
    }
    val sb = StringBuilder()
    while(true){
        val n = br.readLine().toInt()
        if(n == 0){
            break
        }
        var sum = 0
        for(i in 0..4){
            sum += dp[i][n]
        }
        sb.append("$sum\n")
    }
    print(sb)
}