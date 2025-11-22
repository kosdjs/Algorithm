package BOJ_1629

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val A = nextLong()
    val B = nextLong()
    val C = nextLong()
    fun solve(A: Long, B: Long, C: Long): Long{
        if(B == 1L){
            return A % C
        }
        val half = solve(A, B / 2, C) % C
        if(B % 2 == 0L){
            return (half * half) % C
        } else {
            return (((half * half) % C) * (A % C)) % C
        }
    }
    println(solve(A, B, C))
}