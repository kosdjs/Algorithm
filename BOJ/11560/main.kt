package BOJ_11560

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val dp = Array(21){LongArray(211)}
    dp[1][0] = 1
    dp[1][1] = 1
    for(i in 2..20){
        for(j in 0..i){
            for(k in 0..(i - 1) * i / 2){
                dp[i][j + k] += dp[i - 1][k]
            }
        }
    }
    val sb = StringBuilder()
    repeat(T){
        val k = nextInt()
        val N = nextInt()
        sb.append(dp[k][N]).append("\n")
    }
    print(sb)
}