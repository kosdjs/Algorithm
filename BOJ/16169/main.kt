package BOJ_16169

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val rank = Array(n + 2){ArrayList<Int>()}
    val speed = IntArray(n + 1)
    val dp = IntArray(n + 1){0}
    var answer = 0
    for(i in 1..n){
        val st = StringTokenizer(br.readLine())
        rank[st.nextToken().toInt()].add(i)
        speed[i] = st.nextToken().toInt()
    }
    for(i in 1 .. n){
        for(j in rank[i]){
            dp[j] += speed[j]
            for(k in rank[i + 1]){
                val transfer = (k - j) * (k - j)
                if(dp[k] < dp[j] + transfer){
                    dp[k] = dp[j] + transfer
                }
            }
            if(dp[j] > answer){
                answer = dp[j]
            }
        }
    }
    println(answer)
}