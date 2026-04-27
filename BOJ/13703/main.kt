package BOJ_13703

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val k = nextInt()
    val n = nextInt()
    if(k == 0){
        println(0)
    } else {
        var prev = LongArray(k + n + 1)
        var cur = LongArray(k + n + 1)
        prev[k] = 1
        for(time in 1..n){
            cur.fill(0)
            for(i in 1..k + time){
                if(i != n + k) cur[i] += prev[i + 1]
                cur[i] += prev[i - 1]
            }
            prev = cur.also { cur = prev }
        }
        println(prev.sum())
    }
}