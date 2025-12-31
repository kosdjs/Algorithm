package BOJ_22352

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val before = Array(N){IntArray(M)}
    val after = Array(N){IntArray(M)}
    val visit = Array(N){BooleanArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            before[i][j] = nextInt()
        }
    }
    for(i in 0 until N){
        for(j in 0 until M){
            after[i][j] = nextInt()
        }
    }
    var diffCount = 0
    var other = false // 백신 이전의 한 영역 전체가 다른 값으로 변하지 않으면
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    val queue = ArrayDeque<IntArray>()
    for(i in 0 until N){
        for(j in 0 until M){
            if(visit[i][j] || before[i][j] == after[i][j]) continue
            visit[i][j] = true
            diffCount++
            queue.add(intArrayOf(i, j))
            while(queue.isNotEmpty()){
                val (y, x) = queue.removeFirst()
                for(k in 0 until 4){
                    val ny = y + dy[k]
                    val nx = x + dx[k]
                    if(ny >= 0 && ny < N && nx >= 0 && nx < M && !visit[ny][nx]){
                        if(before[i][j] == before[ny][nx]){
                            if(after[i][j] != after[ny][nx]){
                                other = true
                                break
                            } else {
                                visit[ny][nx] = true
                                queue.add(intArrayOf(ny, nx))
                            }
                        }
                    }
                }
                if(other) break
            }
            if(other) break
        }
        if(other) break
    }
    println(if(other || diffCount > 1) "NO" else "YES")
}