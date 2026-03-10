package BOJ_2115

import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run{
    val st = StringTokenizer(readLine())
    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val wall = Array(M){ BooleanArray(N) }
    var answer = 0
    for(i in 0 until M){
        val s = readLine()
        for(j in 0 until N){
            wall[i][j] = s[j] == 'X'
        }
    }
    for(i in 1 until M){
        var j = 1
        while(j < N - 1){
            if(wall[i - 1][j] == wall[i - 1][j + 1] && wall[i][j] == wall[i][j + 1] && wall[i - 1][j] != wall[i][j]){
                answer++
                j++
            }
            j++
        }
    }
    for(j in 1 until N){
        var i = 1
        while(i < M - 1){
            if(wall[i][j - 1] == wall[i + 1][j - 1] && wall[i][j] == wall[i + 1][j] && wall[i][j - 1] != wall[i][j]){
                answer++
                i++
            }
            i++
        }
    }
    println(answer)
}