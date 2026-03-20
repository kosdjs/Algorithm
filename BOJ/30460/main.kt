package BOJ_30460

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var prev = IntArray(4){-2000}
    prev[0] = 0
    var cur = IntArray(4)
    for(i in 0 until N){
        val A = nextInt()
        cur[0] = maxOf(prev[0], prev[3]) + A
        cur[1] = maxOf(prev[0], prev[3]) + A * 2
        cur[2] = prev[1] + A * 2
        cur[3] = prev[2] + A * 2
        prev = cur.also { cur = prev }
    }
    println(prev.max())
}