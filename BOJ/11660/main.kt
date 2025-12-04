package BOJ_11660

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val dp = Array(N + 1){IntArray(N + 1)}
    for(i in 1..N){
        for(j in 1..N){
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + nextInt() - dp[i - 1][j - 1]
        }
    }
    val bw = System.out.bufferedWriter()
    repeat(M){
        val x1 = nextInt()
        val y1 = nextInt()
        val x2 = nextInt()
        val y2 = nextInt()
        bw.append("${dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]}\n")
    }
    bw.flush()
    bw.close()
}