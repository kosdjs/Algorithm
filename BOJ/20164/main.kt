package BOJ_20164

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    nextToken()
    val N = nval.toInt()
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    fun solve(num: Int, count: Int){
        var temp = num
        var newCount = count
        while(temp > 0){
            if(temp % 2 == 1){
                newCount++
            }
            temp /= 10
        }
        if(num / 10 == 0){
            min = minOf(min, newCount)
            max = maxOf(max, newCount)
            return
        } else if (num / 100 == 0){
            solve((num / 10) + (num % 10), newCount)
        } else {
            val s = num.toString()
            for(i in 0 until s.length - 2){
                for(j in i + 1 until s.length - 1){
                    solve(s.substring(0, i + 1).toInt() + s.substring(i + 1, j + 1).toInt() + s.substring(j + 1).toInt(), newCount)
                }
            }
        }
    }
    solve(N, 0)
    println("$min $max")
}