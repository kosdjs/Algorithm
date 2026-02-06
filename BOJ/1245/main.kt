package BOJ_1245

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val farm = Array(N){IntArray(M)}
    val visit = Array(N){BooleanArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            farm[i][j] = nextInt()
        }
    }
    var answer = 0
    val queue = ArrayDeque<IntArray>()
    val dy = intArrayOf(1, 1, 0, -1, -1, -1, 0, 1)
    val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    var isPeak = true
    for(i in 0 until N){
        for(j in 0 until M){
            if(!visit[i][j]){
                isPeak = true
                visit[i][j] = true
                queue.add(intArrayOf(i, j))
                while(queue.isNotEmpty()){
                    val (y, x) = queue.removeFirst()
                    for(k in 0 until 8){
                        val ny = y + dy[k]
                        val nx = x + dx[k]
                        if(ny >= 0 && ny < N && nx >= 0 && nx < M){
                            if(farm[ny][nx] > farm[i][j]) isPeak = false
                            if(!visit[ny][nx] && farm[ny][nx] == farm[i][j]){
                                visit[ny][nx] = true
                                queue.add(intArrayOf(ny, nx))
                            }
                        }
                    }
                }
                if(isPeak) answer++
            }
        }
    }
    println(answer)
}