package BOJ_15652

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val pick = IntArray(M)
    val sb = StringBuilder()
    fun dfs(level: Int, last: Int){
        if(level == M){
            for(i in 0 until M){
                sb.append(pick[i]).append(" ")
            }
            sb.append("\n")
            return
        }
        for(i in last..N){
            pick[level] = i
            dfs(level + 1, i)
        }
    }
    dfs(0, 1)
    print(sb)
}