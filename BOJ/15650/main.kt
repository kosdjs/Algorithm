package BOJ_15650

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val bw = System.out.bufferedWriter()
    fun solve(level: Int, num: Int, bits: Int){
        if(level == M){
            for(i in 1..N){
                if((1 shl i) and bits != 0){
                    bw.append("$i ")
                }
            }
            bw.newLine()
            return
        }
        for(i in num + 1..N){
            solve(level + 1, i, bits or (1 shl i))
        }
    }
    solve(0, 0, 0)
    bw.flush()
    bw.close()
}