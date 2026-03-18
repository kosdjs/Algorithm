package BOJ_28017

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    var prev = IntArray(M){nextInt()}
    var cur = IntArray(M)
    repeat(N - 1){
        var min1 = Int.MAX_VALUE
        var min2 = Int.MAX_VALUE
        var minIdx = 0
        for(i in 0 until M){
            if (prev[i] < min1) {
                min2 = min1
                min1 = prev[i]
                minIdx = i
            } else if (prev[i] < min2) {
                min2 = prev[i]
            }
        }
        for(i in 0 until M){
            cur[i] = nextInt() + if(i == minIdx) min2 else min1
        }
        prev = cur.also { cur = prev }
    }
    println(prev.min())
}