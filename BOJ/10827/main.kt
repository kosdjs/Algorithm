package BOJ_10827

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    nextToken()
    val a = nval.toBigDecimal()
    val b = nextInt()
    println(a.pow(b).toPlainString())
}