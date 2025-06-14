package BOJ_1052

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    var answer = 0
    for(i in 1..K){
        var water = 1
        while(water < N){
            water *= 2
        }
        if(i == K){
            answer = water - N
        } else {
            water /= 2
            N -= water
        }
    }
    println(answer)
}