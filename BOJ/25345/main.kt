package BOJ_25345

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    val mod = 1_000_000_007
    val comb = Array(N + 1){IntArray(N + 1)}
    comb[0][0] = 1
    for(i in 1..N){
        comb[i][0] = 1
        for(j in 1..i){
            comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % mod
        }
    }
    var base = 2L
    var e = K - 1
    var result = 1L
    while(e > 0){
        if(e % 2 == 1){
            result = result * base % mod
        }
        base = (base * base) % mod
        e /= 2
    }
    println(result * comb[N][K] % mod)
}