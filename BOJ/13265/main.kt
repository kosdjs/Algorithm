package BOJ_13265

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val sb = StringBuilder()
    repeat(T){
        val n = nextInt()
        val m = nextInt()
        val graph = Array(n + 1){ArrayList<Int>()}
        val color = IntArray(n + 1)
        var isPossible = true
        repeat(m){
            val x = nextInt()
            val y = nextInt()
            graph[x].add(y)
            graph[y].add(x)
        }
        val queue = ArrayDeque<Int>()
        for(i in 1..n){
            if(!isPossible) break
            if(color[i] == 0){
                color[i] = 1
                queue.add(i)
                while(queue.isNotEmpty()){
                    val x = queue.removeFirst()
                    for(y in graph[x]){
                        if(color[x] == color[y]){
                            isPossible = false
                            break
                        }
                        if(color[y] == 0){
                            color[y] = if(color[x] == 1) 2 else 1
                            queue.add(y)
                        }
                    }
                    if(!isPossible) break
                }
            }
        }
        sb.append(if(isPossible) "possible" else "impossible").append("\n")
    }
    print(sb)
}