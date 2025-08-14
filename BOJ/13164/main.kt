package BOJ_13164

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    var prev = st.nextToken().toInt()
    val diff = IntArray(N - 1)
    var answer = 0
    for(i in 0 until N - 1){
        var curr = st.nextToken().toInt()
        diff[i] = curr - prev
        answer += diff[i]
        prev = curr
    }
    diff.sortDescending()
    for(i in 0 until K - 1){
        answer -= diff[i]
    }
    println(answer)
}