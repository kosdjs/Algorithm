package BOJ_17088

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    var answer = Int.MAX_VALUE
    if(N == 1){
        println(0)
        return@run
    }
    for(op1 in -1..1){
        for(op2 in -1..1){
            val first = arr[0] + op1
            val second = arr[1] + op2
            val diff = second - first

            var count = 0
            if(op1 != 0) count++
            if(op2 != 0) count++

            var prev = second
            var possible = true

            for(idx in 2 until N){
                val target = prev + diff
                val change = target - arr[idx]

                if (change in -1..1) {
                    if (change != 0) count++
                    prev = target
                } else {
                    possible = false
                    break
                }
            }
            if(possible) answer = minOf(answer, count)
        }
    }
    println(if(answer == Int.MAX_VALUE) -1 else answer)
}