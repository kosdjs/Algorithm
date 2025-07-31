package BOJ_1516

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val dp = IntArray(N + 1){0}
    val times = IntArray(N + 1){0}
    val prev = IntArray(N + 1){0}
    val next = Array(N + 1){ArrayList<Int>()}
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        times[i] = st.nextToken().toInt()
        var j = st.nextToken().toInt()
        while(j != -1){
            prev[i]++
            next[j].add(i)
            j = st.nextToken().toInt()
        }
    }
    val queue = ArrayDeque<Int>()
    for(i in 1..N){
        if(prev[i] == 0){
            queue.add(i)
        }
    }
    while(queue.isNotEmpty()){
        val i = queue.removeFirst()
        dp[i] += times[i]
        for(j in next[i]){
            prev[j]--
            if(dp[j] < dp[i]){
                dp[j] = dp[i]
            }
            if(prev[j] == 0){
                queue.add(j)
            }
        }
    }
    for(i in 1..N){
        println(dp[i])
    }
}