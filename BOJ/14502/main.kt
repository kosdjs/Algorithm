package BOJ_14502

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val map = Array(N){IntArray(M)}
    val empties = ArrayList<IntArray>()
    val viruses = ArrayList<IntArray>()
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
            if(map[i][j] == 0){
                empties.add(intArrayOf(i, j))
            } else if(map[i][j] == 2){
                viruses.add(intArrayOf(i, j))
            }
        }
    }
    val temp = Array(N){IntArray(M)}
    var answer = 0
    fun copy(){
        for(i in 0 until N){
            temp[i] = map[i].copyOf()
        }
    }
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    fun bfs(){
        var count = 0
        val queue = ArrayDeque<IntArray>()
        for(arr in viruses){
            queue.add(arr)
        }
        while(queue.isNotEmpty()){
            val(y, x) = queue.removeFirst()
            for(i in 0 until dy.size){
                val ny = y + dy[i]
                val nx = x + dx[i]
                if(ny >= 0 && ny < N && nx >= 0 && nx < M && temp[ny][nx] == 0){
                    temp[ny][nx] = 2
                    queue.add(intArrayOf(ny, nx))
                }
            }
        }
        for(i in 0 until N){
            for(j in 0 until M){
                if(temp[i][j] == 0) count++
            }
        }
        answer = maxOf(answer, count)
    }
    fun dfs(level: Int, idx: Int){
        if(level == 3){
            copy()
            bfs()
            return
        }
        for(i in idx until empties.size){
            val (y, x) = empties[i]
            map[y][x] = 1
            dfs(level + 1, i + 1)
            map[y][x] = 0
        }
    }
    dfs(0, 0)
    println(answer)
}