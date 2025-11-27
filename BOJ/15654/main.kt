package BOJ_15654

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt():Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    arr.sort()
    val bw = System.out.bufferedWriter()
    val chosen = IntArray(M)
    fun solve(level: Int, bits: Int){
        if(level == M){
            for(i in 0 until M){
                bw.append("${chosen[i]} ")
            }
            bw.newLine()
            return
        }
        for(i in 0 until N){
            val bit = 1 shl i
            if(bit and bits != 0) continue
            chosen[level] = arr[i]
            solve(level + 1, bits or bit)
        }
    }
    solve(0, 0)
    bw.flush()
    bw.close()
}