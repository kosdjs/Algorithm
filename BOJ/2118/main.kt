package BOJ_2118

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N + 1)
    arr[1] = nextInt()
    for(i in 2..N){
        arr[i] = nextInt() + arr[i - 1]
    }
    var left = 1
    var right = 1
    var answer = 0
    while(left <= right && right < N + 1){
        val d1 = arr[right] - arr[left - 1]
        val d2 = arr[N] - d1
        if(d1 > d2){
            answer = maxOf(answer, d2)
            left++
        } else {
            answer = maxOf(answer, d1)
            right++
        }
    }
    println(answer)
}