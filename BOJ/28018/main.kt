package BOJ_28018

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val sum = IntArray(1000002)
    repeat(N){
        val S = nextInt()
        val E = nextInt()
        sum[S]++
        sum[E + 1]--
    }
    for(i in 1 until sum.size){
        sum[i] += sum[i - 1]
    }
    val Q = nextInt()
    val sb = StringBuilder()
    repeat(Q){
        val time = nextInt()
        sb.append(sum[time]).append("\n")
    }
    print(sb)
}