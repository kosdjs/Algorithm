package BOJ_1932

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val prev = IntArray(n)
    val cur = IntArray(n)
    for(i in 0 until n){
        for(j in 0..i){
            if(j != 0){
                cur[j] = maxOf(prev[j], prev[j - 1]) + nextInt()
            } else {
                cur[j] = prev[j] + nextInt()
            }
        }
        cur.copyInto(prev)
    }
    var max = 0
    for(i in 0 until n){
        max = maxOf(max, prev[i])
    }
    println(max)
}