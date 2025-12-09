package BOJ_15663

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val num = IntArray(N)
    for(i in 0 until N){
        num[i] = nextInt()
    }
    num.sort()
    val pick = IntArray(M)
    val bw = System.out.bufferedWriter()
    val visit = BooleanArray(N)
    fun dfs(level: Int){
        if(level == M){
            for(i in 0 until M){
                bw.append("${pick[i]} ")
            }
            bw.newLine()
            return
        }
        var last = 0 // 현재 level에서 마지막으로 사용한 숫자
        for(i in 0 until N){
            val cur = num[i]
            if(visit[i]) continue
            if(last == cur) continue // 현재 숫자가 마지막으로 사용한 숫자와 같으면 건너뜀

            visit[i] = true
            last = cur
            pick[level] = cur
            dfs(level + 1)
            visit[i] = false
        }
    }
    dfs(0)
    bw.flush()
    bw.close()
}