package BOJ_11054

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val A = IntArray(N)
    for(i in 0 until N){
        A[i] = nextInt()
    }
    val dp = Array(2){IntArray(N)}
    var max = 1
    for(i in 0 until N){
        for(j in 0 until i){
            if(A[i] > A[j]){
                dp[0][i] = maxOf(dp[0][i], dp[0][j])
            } else if(A[i] < A[j]){
                dp[1][i] = maxOf(dp[1][i], dp[0][j], dp[1][j])
            }
        }
        dp[0][i]++
        dp[1][i]++
        max = maxOf(max, dp[0][i], dp[1][i])
    }
    println(max)
}