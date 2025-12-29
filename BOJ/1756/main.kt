package BOJ_1756

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val D = nextInt()
    val N = nextInt()
    val oven = IntArray(D + 1){Int.MAX_VALUE}
    for(i in 1..D){
        oven[i] = minOf(oven[i - 1], nextInt())
    }
    var pos = D
    repeat(N){
        val pizza = nextInt()
        while (pos >= 1 && oven[pos] < pizza) pos--
        if(pos < 1) {
            pos = -1
            return@repeat
        }
        pos--
    }
    println(pos + 1)
}