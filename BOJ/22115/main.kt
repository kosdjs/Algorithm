package BOJ_22115

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val C = IntArray(N)
    for(i in 0 until N){
        C[i] = st.nextToken().toInt()
        if(C[i] == K){
            println(1)
            return
        }
    }
    val dp = IntArray(K + 1){Int.MAX_VALUE}
    dp[0] = 0
    for(c in C){
        if(c <= K){
            dp[c] = 1
        }
        for(i in K downTo 1){
            if(dp[i] != Int.MAX_VALUE){
                if(i + c <= K){
                    dp[i + c] = minOf(dp[i + c], dp[i] + 1)
                }
            }
        }
    }
    println(if(dp[K] != Int.MAX_VALUE) dp[K] else -1)
}