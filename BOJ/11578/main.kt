package BOJ_11578

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var solve = 0
    for(i in 1..N){
        solve = solve or (1 shl i)
    }
    val M = nextInt()
    var answer = M + 1
    val team = IntArray(M)
    for(i in 0 until M){
        val O = nextInt()
        var P = 0
        for(j in 0 until O){
            P = P or (1 shl nextInt())
        }
        team[i] = P
    }
    fun find(count: Int, idx: Int, mask: Int){
        if(count > answer) return
        if(mask == solve){
            answer = minOf(answer, count)
            return
        }
        for(i in idx until M){
            find(count + 1, i + 1, mask or team[i])
        }
    }
    find(0, 0, 0)
    println(if(answer == M + 1) -1 else answer)
}