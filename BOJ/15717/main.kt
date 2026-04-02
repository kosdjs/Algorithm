package BOJ_15717

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toLong()
    val mod = 1_000_000_007
    var res = 1L
    var base = 2L
    var e = N - 1
    while(e > 0){
        if(e % 2 == 1L){
            res = (res * base) % mod
        }
        base = (base * base) % mod
        e /= 2
    }
    println(res)
}