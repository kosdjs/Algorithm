package BOJ_2467

import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val N = readLine()!!.toInt()
    var left = 0
    var right = N - 1
    val arr = IntArray(N)
    val st = StringTokenizer(readLine())
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }
    var min = Int.MAX_VALUE
    var sum = 0
    val answer = IntArray(2)
    while(left < right){
        sum = arr[left] + arr[right]
        if(min > abs(sum)){
            min = abs(sum)
            answer[0] = left
            answer[1] = right
        }
        if(sum > 0){
            right--
        } else {
            left++
        }
    }
    println("${arr[answer[0]]} ${arr[answer[1]]}")
}