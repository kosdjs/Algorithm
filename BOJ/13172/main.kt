package BOJ_13172

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val M = nextLong()
    val mod = 1_000_000_007L
    var answer = 0L
    fun pow(base: Long, exp: Long): Long{
        var res = 1L
        var bPow = base % mod
        var e = exp
        while(e>0L){
            if(e % 2L == 1L) res = (res * bPow) % mod
            bPow = (bPow * bPow) % mod
            e /= 2
        }
        return res
    }
    repeat(M.toInt()){
        val N = nextLong()
        val S = nextLong()
        answer += S * pow(N, mod - 2)
        answer %= mod
    }
    println(answer)
}