package BOJ_3067

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val sb = StringBuilder()
    repeat(T){
        val N = nextInt()
        val coins = IntArray(N)
        for(i in 0 until N){
            coins[i] = nextInt()
        }
        val M = nextInt()
        val dp = IntArray(M + 1)
        dp[0] = 1
        for(coin in coins){
            for(i in coin..M){
                dp[i] += dp[i - coin]
            }
        }
        sb.append(dp[M]).append("\n")
    }
    print(sb)
}