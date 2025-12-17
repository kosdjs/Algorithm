package BOJ_15666

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val arr = IntArray(N)
    val pick = IntArray(M)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    arr.sort()
    val sb = StringBuilder()
    fun dfs(level: Int, idx: Int){
        if(level == M){
            for(i in 0 until M){
                sb.append(pick[i]).append(" ")
            }
            sb.append("\n")
            return
        }
        var last = 0
        for(i in idx until N){
            if(arr[i] == last) continue
            last = arr[i]
            pick[level] = arr[i]
            dfs(level + 1, i)
        }
    }
    dfs(0, 0)
    print(sb)
}