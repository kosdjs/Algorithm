package BOJ_15553

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    val diff = IntArray(N - 1)
    var prev = nextInt()
    for(i in 0 until N - 1){
        val cur = nextInt()
        diff[i] = cur - prev - 1
        prev = cur
    }
    diff.sort()
    var answer = N
    for(i in 0 until N - K){
        answer += diff[i]
    }
    println(answer)
}