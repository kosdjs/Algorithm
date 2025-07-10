package BOJ_2096

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = Array(N){IntArray(3)}
    val min = Array(N){IntArray(3)}
    val max = Array(N){IntArray(3)}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        arr[i][0] = st.nextToken().toInt()
        arr[i][1] = st.nextToken().toInt()
        arr[i][2] = st.nextToken().toInt()
    }
    min[0][0] = arr[0][0]
    min[0][1] = arr[0][1]
    min[0][2] = arr[0][2]
    max[0][0] = arr[0][0]
    max[0][1] = arr[0][1]
    max[0][2] = arr[0][2]
    for(i in 1 until N){
        for(j in 0..2){
            var minScore = Int.MAX_VALUE
            var maxScore = 0
            for(k in j-1..j+1){
                if(k < 0 || k >= 3) continue
                if(min[i-1][k] < minScore) minScore = min[i-1][k]
                if(max[i-1][k] > maxScore) maxScore = max[i-1][k]
            }
            min[i][j] = minScore + arr[i][j]
            max[i][j] = maxScore + arr[i][j]
        }
    }
    var minResult = Int.MAX_VALUE
    var maxResult = 0
    for(j in 0..2){
        if(min[N-1][j] < minResult) minResult = min[N-1][j]
        if(max[N-1][j] > maxResult) maxResult = max[N-1][j]
    }
    println("$maxResult $minResult")
}