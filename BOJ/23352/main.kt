package BOJ_23352

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val map = Array(N){ IntArray(M){ nextInt() } }
    val visit = Array(N){ BooleanArray(M) }
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    val queue = ArrayDeque<IntArray>()
    var max = 0
    var maxLength = 0
    for(i in 0 until N){
        for(j in 0 until M){
            if(map[i][j] != 0){
                for(k in 0 until N){
                    visit[k].fill(false)
                }
                visit[i][j] = true
                queue.add(intArrayOf(i, j, 0))
                while (queue.isNotEmpty()){
                    val (y, x, length) = queue.removeFirst()
                    if(length == maxLength) max = maxOf(max, map[i][j] + map[y][x])
                    if(length > maxLength){
                        max = map[i][j] + map[y][x]
                        maxLength = length
                    }
                    for(k in 0 until 4){
                        val ny = y + dy[k]
                        val nx = x + dx[k]
                        if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue
                        if(map[ny][nx] == 0 || visit[ny][nx]) continue
                        visit[ny][nx] = true
                        queue.add(intArrayOf(ny, nx, length + 1))
                    }
                }
            }
        }
    }
    println(max)
}