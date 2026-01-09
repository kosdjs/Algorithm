package BOJ_19951

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val ground = IntArray(N + 1)
    for(i in 1..N){
        ground[i] = nextInt()
    }
    val sum = IntArray(N + 1)
    repeat(M){
        val a = nextInt()
        val b = nextInt()
        val k = nextInt()
        sum[b] += k
        sum[a - 1] -= k
    }
    for(i in N - 1 downTo 1){
        sum[i] += sum[i + 1]
    }
    val sb = StringBuilder()
    for(i in 1..N){
        ground[i] += sum[i]
        sb.append(ground[i]).append(" ")
    }
    print(sb)
}