package BOJ_3020

import java.util.StringTokenizer

fun main(){
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val H = st.nextToken().toInt()
    val bottom = IntArray(H){0}
    val top = IntArray(H){0}
    for(i in 0 until N){
        if(i % 2 == 0){
            bottom[readLine()!!.toInt() - 1]++
        } else {
            top[H - readLine()!!.toInt()]++
        }
    }
    for(i in H - 2 downTo 0){
        bottom[i] += bottom[i + 1]
    }
    for(i in 1 until H){
        top[i] += top[i - 1]
    }
    var min = Int.MAX_VALUE
    var count = 1
    for(i in 0 until H){
        if(min > bottom[i] + top[i]){
            min = bottom[i] + top[i]
            count = 1
        } else if(min == bottom[i] + top[i]){
            count++
        }
    }
    println("$min $count")
}