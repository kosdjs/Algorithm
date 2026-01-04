package BOJ_27211

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    var count = 0
    val map = Array(N){IntArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
        }
    }
    val visit = Array(N){BooleanArray(M)}
    val queue = ArrayDeque<IntArray>()
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    for(i in 0 until N){
        for(j in 0 until M){
            if(map[i][j] == 0 && !visit[i][j]){
                visit[i][j] = true
                count++
                //bfs
                queue.add(intArrayOf(i, j))
                while(queue.isNotEmpty()){
                    val (y, x) = queue.removeFirst()
                    for(k in 0 until 4){
                        var ny = y + dy[k]
                        if(ny < 0) ny += N
                        else if(ny >= N) ny -= N
                        var nx = x + dx[k]
                        if(nx < 0) nx += M
                        else if(nx >= M) nx -= M
                        if(map[ny][nx] == 0 && !visit[ny][nx]){
                            visit[ny][nx] = true
                            queue.add(intArrayOf(ny, nx))
                        }
                    }
                }
            }
        }
    }
    println(count)
}