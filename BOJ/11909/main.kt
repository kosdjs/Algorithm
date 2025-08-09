package BOJ_11909

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val map = Array(n){ IntArray(n) }
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until n){
            map[i][j] = st.nextToken().toInt()
        }
    }
    val dist = Array(n){ IntArray(n){Int.MAX_VALUE} }
    dist[0][0] = 0
    for(i in 0 until n){
        for(j in 0 until n){
            if(i > 0){
                val cost = if (map[i][j] >= map[i - 1][j]) map[i][j] - map[i - 1][j] + 1 else 0
                if(dist[i][j] > dist[i - 1][j] + cost){
                    dist[i][j] = dist[i - 1][j] + cost
                }
            }
            if(j > 0){
                val cost = if (map[i][j] >= map[i][j - 1]) map[i][j] - map[i][j - 1] + 1 else 0
                if(dist[i][j] > dist[i][j - 1] + cost){
                    dist[i][j] = dist[i][j - 1] + cost
                }
            }
        }
    }
    println(dist[n - 1][n - 1])
}