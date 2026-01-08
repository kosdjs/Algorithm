package BOJ_15922

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var answer = 0
    var start = nextInt()
    var end = nextInt()
    repeat(N - 1){
        val x = nextInt()
        val y = nextInt()
        if(x <= end){
            end = maxOf(end, y)
        } else {
            answer += end - start
            start = x
            end = y
        }
    }
    answer += end - start
    println(answer)
}