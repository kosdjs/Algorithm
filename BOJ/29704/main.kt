package BOJ_29704

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val T = nextInt()
    val dp = IntArray(T + 1)
    var total = 0
    repeat(N){
        val d = nextInt()
        val m = nextInt()
        total += m
        for(i in T downTo d){
            dp[i] = maxOf(dp[i], dp[i - d] + m)
        }
    }
    var max = 0
    for(i in 1..T){
        max = maxOf(max, dp[i])
    }
    println(total - max)
}