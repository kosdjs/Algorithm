package BOJ_1351

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toLong()
    val P = st.nextToken().toInt()
    val Q = st.nextToken().toInt()
    val dp = HashMap<Long, Long>()
    dp[0] = 1
    println(solve(dp, N, P, Q))
}

fun solve(dp: HashMap<Long, Long>, idx: Long, P: Int, Q: Int): Long{
    if(dp.contains(idx)){
        return dp[idx]!!
    } else {
        dp[idx] = solve(dp, idx / P, P, Q) + solve(dp, idx / Q, P, Q)
        return dp[idx]!!
    }
}