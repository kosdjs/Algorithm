package BOJ_2056

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    var answer = 0
    val times = IntArray(N + 1){0}
    val dp = IntArray(N + 1){0}
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        times[i] = st.nextToken().toInt()
        val count = st.nextToken().toInt()
        var max = 0
        for(j in 1..count){
            val idx = st.nextToken().toInt()
            if(max < dp[idx]){
                max = dp[idx]
            }
        }
        dp[i] = max + times[i]
        if(answer < dp[i]){
            answer = dp[i]
        }
    }
    println(answer)
}