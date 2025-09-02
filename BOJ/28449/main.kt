package BOJ_28449

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val hi = IntArray(100001)
    st = StringTokenizer(br.readLine())
    repeat(N){
        hi[st.nextToken().toInt()]++
    }
    for(i in 1..100000){
        hi[i] += hi[i-1]
    }
    var win = 0L
    var lose = 0L
    var draw = 0L
    st = StringTokenizer(br.readLine())
    for(i in 0 until M){
        val arc = st.nextToken().toInt()
        win += N - hi[arc]
        lose += hi[arc - 1]
        draw += hi[arc] - hi[arc - 1]
    }
    println("$win $lose $draw")
}