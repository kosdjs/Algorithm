package BOJ_1520

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

val dx = arrayOf(-1, 0, 1, 0)
val dy = arrayOf(0, -1, 0, 1)

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = Array(N){IntArray(M)}
    val dp = Array(N){IntArray(M){-1}}
    for (i in 0..<N){
        st = StringTokenizer(br.readLine())
        for (j in 0..<M){
            map[i][j] = st.nextToken().toInt()
        }
    }
    solve(0, 0, N, M, map, dp)
    println(dp[0][0])
}

fun solve(x: Int, y: Int, N: Int, M: Int, map: Array<IntArray>, dp: Array<IntArray>): Int{
    if(x == N - 1 && y == M - 1){
        return 1
    }
    if (dp[x][y] != -1){
        return dp[x][y]
    }
    dp[x][y] = 0
    for(i in 0..3){
        val nx = x + dx[i]
        val ny = y + dy[i]
        if(nx in 0..<N && ny in 0..<M){
            if(map[x][y] > map[nx][ny]){
                dp[x][y] += solve(nx, ny, N, M, map, dp)
            }
        }
    }
    return dp[x][y]
}