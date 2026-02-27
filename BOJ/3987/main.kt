package BOJ_3987

import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = Array(N){ CharArray(M) }
    val visit = Array(N){Array(M){ BooleanArray(4) } }
    for(i in 0 until N){
        val s = readLine()
        for(j in 0 until M){
            map[i][j] = s[j]
        }
    }
    st = StringTokenizer(readLine())
    val PR = st.nextToken().toInt() - 1
    val PC = st.nextToken().toInt() - 1
    var direction = 0
    var answer = 0
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, 1, 0, -1)
    fun dfs(i: Int, j: Int, count: Int, d: Int): Int{
        if(i < 0 || j < 0 || i >= N || j >= M) return count - 1
        else if(map[i][j] == 'C') return count - 1
        else if(visit[i][j][d]) return Int.MAX_VALUE
        else{
            var nd = d
            if(map[i][j] == '/'){
                nd = when(d){
                    0 -> 1
                    1 -> 0
                    2 -> 3
                    3 -> 2
                    else -> -1
                }
            } else if(map[i][j] == '\\'){
                nd = when(d){
                    0 -> 3
                    1 -> 2
                    2 -> 1
                    3 -> 0
                    else -> -1
                }
            }
            visit[i][j][d] = true
            val result = dfs(i + dy[nd], j + dx[nd], count + 1, nd)
            visit[i][j][d] = false
            return result
        }
    }
    for(i in 0 until 4){
        val cur = dfs(PR, PC, 1, i)
        if(cur > answer){
            direction = i
            answer = cur
        }
    }
    println(when(direction){
        0 -> "U"
        1 -> "R"
        2 -> "D"
        3 -> "L"
        else -> "else"
    })
    println(if(answer == Int.MAX_VALUE) "Voyager" else answer)
}