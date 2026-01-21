package BOJ_9764

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val N = IntArray(T)
    for(i in 0 until T){
        N[i] = nextInt()
    }
    val max = N.max()
    val dp = IntArray(max + 1)
    val mod = 100999
    dp[0] = 1
    for(i in 1..max){
        for(j in max downTo i){
            dp[j] += dp[j - i]
            dp[j] %= mod
        }
    }
    val sb = StringBuilder()
    for(n in N){
        sb.append(dp[n]).append("\n")
    }
    print(sb)
}