package BOJ_13910

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val woks = IntArray(M)
    val dp = IntArray(N + 1){Int.MAX_VALUE}
    dp[0] = 0
    for(i in 0 until M){
        woks[i] = nextInt()
    }
    val size = HashSet<Int>()
    for(i in 0 until M){
        size.add(woks[i])
        for(j in i + 1 until M){
            size.add(woks[i] + woks[j])
        }
    }
    for(s in size.toSortedSet()){
        for(i in s..N){
            if(dp[i - s] == Int.MAX_VALUE) continue
            dp[i] = minOf(dp[i], dp[i - s] + 1)
        }
    }
    println(if(dp[N] != Int.MAX_VALUE) dp[N] else -1)
}