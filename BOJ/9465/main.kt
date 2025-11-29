package BOJ_9465

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val bw = System.out.bufferedWriter()
    repeat(T){
        val n = nextInt()
        val arr = Array(2){IntArray(n)}
        for(i in 0 until 2){
            for(j in 0 until n){
                arr[i][j] = nextInt()
            }
        }
        val dp = Array(2){IntArray(n)}
        dp[0][0] = arr[0][0]
        dp[1][0] = arr[1][0]
        if(n > 1){
            dp[0][1] = dp[1][0] + arr[0][1]
            dp[1][1] = dp[0][0] + arr[1][1]
        }
        for(j in 2 until n){
            for(i in 0 until 2){
                dp[i][j] = arr[i][j] + maxOf(dp[1 xor i][j - 2], dp[1 xor i][j - 1])
            }
        }
        bw.append("${maxOf(dp[0][n - 1], dp[1][n - 1])}\n")
    }
    bw.flush()
    bw.close()
}