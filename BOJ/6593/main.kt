package BOJ_6593

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    while(true){
        var st = StringTokenizer(br.readLine())
        val N = st.nextToken().toInt()
        val R = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        if(N == 0 && R == 0 && C == 0){
            break
        }
        var startN = 0
        var startR = 0
        var startC = 0
        var endN = 0
        var endR = 0
        var endC = 0
        val map = Array(N){Array(R){ CharArray(C) } }
        val visit = Array(N){Array(R){ BooleanArray(C) } }
        for(n in 0 until N){
            for(r in 0 until R){
                val s = br.readLine()
                for(c in 0 until C){
                    map[n][r][c] = s[c]
                    if(s[c] == 'S'){
                        startN = n
                        startR = r
                        startC = c
                    }
                    if(s[c] == 'E'){
                        endN = n
                        endR = r
                        endC = c
                    }
                }
            }
            br.readLine()
        }
        visit[startN][startR][startC] = true
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(startN, startR, startC, 0))
        val dr = intArrayOf(0, -1, 0, 1, 0, 0)
        val dc = intArrayOf(-1, 0, 1, 0, 0, 0)
        val dn = intArrayOf(0, 0, 0, 0, -1, 1)
        while(queue.isNotEmpty()){
            val(n, r, c, time) = queue.removeFirst()
            for(i in 0 until 6){
                val nn = n + dn[i]
                val nr = r + dr[i]
                val nc = c + dc[i]
                if(nn >= 0 && nn < N && nr >= 0 && nr < R && nc >= 0 && nc < C && map[nn][nr][nc] != '#' && !visit[nn][nr][nc]){
                    visit[nn][nr][nc] = true
                    queue.add(intArrayOf(nn, nr, nc, time + 1))
                    if(map[nn][nr][nc] == 'E'){
                        println("Escaped in ${time + 1} minute(s).")
                        queue.clear()
                    }
                }
            }
        }
        if(!visit[endN][endR][endC]){
            println("Trapped!")
        }
    }
}