package BOJ_1612

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    if(N % 2 == 0 || N % 5 == 0) {
        println(-1)
        return@run
    }
    var num = 0
    var count = 1
    while (true){
        num = (num * 10 + 1) % N
        if(num == 0){
            println(count)
            break
        }
        count++
    }
}