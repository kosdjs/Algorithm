package BOJ_11404

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val dp = Array(n){IntArray(n){n*100000} }
    for(i in 1..m){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        if(dp[a-1][b-1] > c){
            dp[a-1][b-1] = c
        }
    }
    for(k in 0 until n){
        for(i in 0 until n){
            for(j in 0 until n){
                if(i == j){
                    dp[i][j] = 0
                }
                if(dp[i][k] + dp[k][j] < dp[i][j]){
                    dp[i][j] = dp[i][k] + dp[k][j]
                }
            }
        }
    }
    for (i in 0 until n){
        for (j in 0 until n){
            if(dp[i][j] == n*100000){
                dp[i][j] = 0
            }
            print("${dp[i][j]} ")
        }
        println()
    }
}