package BOJ_27210

import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val min = IntArray(N)
    val max = IntArray(N)
    var answer = 0
    for(i in 0 until N){
        val num = if(st.nextToken().toInt() == 1) 1 else -1
        if(i == 0){
            min[i] = num
            max[i] = num
        } else{
            min[i] = minOf(num, min[i - 1] + num)
            max[i] = maxOf(num, max[i - 1] + num)
        }
        answer = maxOf(answer, abs(min[i]), max[i])
    }
    println(answer)
}