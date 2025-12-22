package BOJ_2638

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    var cheeseCount = 0
    val map = Array(N){IntArray(M)}
    val air = Array(N){IntArray(M)}
    val visit = Array(N){BooleanArray(M)}
    val queue = ArrayDeque<IntArray>()
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
            if(map[i][j] == 1) cheeseCount++
        }
    }
    var time = 0
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    while(cheeseCount > 0){
        visit[0][0] = true
        queue.add(intArrayOf(0,0))
        while(queue.isNotEmpty()){
            val (y, x) = queue.removeFirst()
            for(i in 0 until 4){
                val ny = y + dy[i]
                val nx = x + dx[i]
                if(ny >= 0 && ny < N && nx >= 0 && nx < M){
                    air[ny][nx]++
                    if(map[ny][nx] == 0 && !visit[ny][nx]){
                        visit[ny][nx] = true
                        queue.add(intArrayOf(ny, nx))
                    }
                }
            }
        }
        for(y in 0 until N){
            for(x in 0 until M){
                if(map[y][x] == 1 && air[y][x] >= 2){
                    map[y][x] = 0
                    cheeseCount--
                }
                air[y][x] = 0
                visit[y][x] = false
            }
        }
        time++
    }
    println(time)
}