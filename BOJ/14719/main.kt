package BOJ_14719

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val H = st.nextToken().toInt()
    val W = st.nextToken().toInt()
    var result = 0
    st = StringTokenizer(br.readLine())
    val map = Array(H){IntArray(W){0}}
    for(i in 0 until W){
        val h = st.nextToken().toInt()
        for(j in 0 until h){
            map[j][i] = 1
        }
    }
    for(i in 0 until H){
        var prev = 0 // 이전 칸의 상태 1이면 벽, 0이면 빈 공간
        var space = 0
        for(j in 0 until W){
            if(map[i][j] == 0){// 빈 공간일 때
                if (prev == 1){
                    space++
                }
            } else {// 벽일 때
                if (prev == 0){
                    prev = 1
                } else {
                    result += space
                    space = 0
                }
            }
        }
    }
    println(result)
}