package BOJ_17845

import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = IntArray(N + 1) { 0 }
    for(i in 1..K) {
        val st = StringTokenizer(br.readLine())
        val I = st.nextToken().toInt()
        val T = st.nextToken().toInt()
        for (j in N downTo T)
            dp[j] = maxOf(dp[j], dp[j - T] + I)
    }
    println(dp[N])
}