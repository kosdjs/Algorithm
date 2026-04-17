package BOJ_7571

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val arrR = IntArray(M)
    val arrC = IntArray(M)
    for(i in 0 until M){
        arrR[i] = nextInt()
        arrC[i] = nextInt()
    }
    arrR.sort()
    arrC.sort()
    val midR = arrR[M / 2]
    val midC = arrC[M / 2]
    var answer = 0L
    for(i in 0 until M){
        answer += abs(arrR[i] - midR) + abs(arrC[i] - midC)
    }
    println(answer)
}