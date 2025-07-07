package BOJ_2565

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val map = Array(n + 1){IntArray(2)}
    for(i in 1..n){
        val st = StringTokenizer(br.readLine())
        map[i][0] = st.nextToken().toInt()
        map[i][1] = st.nextToken().toInt()
    }
    map.sortWith { o1, o2 -> o1[0] - o2[0]}
    val dp = IntArray(n + 1){1}
    for (i in 2..n){
        var max = 0
        for(j in 1 until i){
            if(map[i][1] > map[j][1]){
                if(max < dp[j]){
                    max = dp[j]
                }
            }
        }
        dp[i] = max + 1
    }
    var max = 0
    for(i in 1..n){
        if (max < dp[i]) max = dp[i]
    }
    println(n - max)
}