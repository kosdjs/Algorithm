package BOJ_1149

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var prevR = 0
    var prevG = 0
    var prevB = 0
    for(i in 0 until N){
        val curR = nextInt() + minOf(prevG, prevB)
        val curG = nextInt() + minOf(prevR, prevB)
        val curB = nextInt() + minOf(prevR, prevG)
        prevR = curR
        prevG = curG
        prevB = curB
    }
    println(minOf(prevR, prevG, prevB))
}