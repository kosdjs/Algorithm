package BOJ_1005

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val T = br.readLine().toInt()
    for(i in 1..T){
        var st = StringTokenizer(br.readLine())
        val N = st.nextToken().toInt()
        val K = st.nextToken().toInt()
        val D = IntArray(N + 1){0}
        st = StringTokenizer(br.readLine())
        for(i in 1..N){
            D[i] = st.nextToken().toInt()
        }
        val prev = IntArray(N + 1){0}
        val next = Array(N + 1){ArrayList<Int>()}
        val dp = IntArray(N + 1){0}
        for(i in 1..K){
            st = StringTokenizer(br.readLine())
            val X = st.nextToken().toInt()
            val Y = st.nextToken().toInt()
            prev[Y]++
            next[X].add(Y)
        }
        val queue = ArrayDeque<Int>()
        for(i in 1..N){
            if(prev[i] == 0) queue.add(i)
        }
        while(queue.isNotEmpty()){
            val x = queue.removeFirst()
            dp[x] += D[x]
            for(y in next[x]){
                if(dp[y] < dp[x]) dp[y] = dp[x]
                prev[y]--
                if(prev[y] == 0) queue.add(y)
            }
        }
        println(dp[br.readLine().toInt()])
    }
}