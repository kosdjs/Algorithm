package BOJ_17485

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val prev = Array(3){ IntArray(M) }
    val curr = Array(3){ IntArray(M) }
    for(i in 0 until N){
        for(k in 0 until 3){
            curr[k].copyInto(prev[k])
        }
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            val cost = st.nextToken().toInt()
            if(j == 0){
                curr[0][j] = minOf(prev[1][j], prev[2][j + 1]) + cost
                curr[1][j] = prev[2][j + 1] + cost
                curr[2][j] = prev[1][j] + cost
            } else if(j == M - 1){
                curr[0][j] = prev[1][j] + cost
                curr[1][j] = prev[0][j - 1] + cost
                curr[2][j] = minOf(prev[0][j - 1], prev[1][j]) + cost
            } else {
                curr[0][j] = minOf(prev[1][j], prev[2][j + 1]) + cost
                curr[1][j] = minOf(prev[0][j - 1], prev[2][j + 1]) + cost
                curr[2][j] = minOf(prev[0][j - 1], prev[1][j]) + cost
            }
        }
    }
    var answer = Int.MAX_VALUE
    for(k in 0 until 3) {
        for (j in 0 until M) {
            answer = minOf(answer, curr[k][j])
        }
    }
    println(answer)
}