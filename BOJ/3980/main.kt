package BOJ_3980

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val C = nextInt()
    val sb = StringBuilder()
    repeat(C){
        val visit = BooleanArray(11)
        val s = Array(11){IntArray(11)}
        for(i in 0 until 11){
            for(j in 0 until 11){
                s[i][j] = nextInt()
            }
        }
        var answer = 0
        fun solve(j: Int, sum: Int){
            if(j == 11){
                answer = maxOf(answer, sum)
                return
            }
            for(i in 0 until 11){
                if(visit[i] || s[i][j] == 0) continue
                visit[i] = true
                solve(j + 1, sum + s[i][j])
                visit[i] = false
            }
        }
        solve(0, 0)
        sb.append(answer).append("\n")
    }
    print(sb)
}