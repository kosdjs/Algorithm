package BOJ_24230

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val colors = IntArray(N + 1)
    val graph = Array(N + 1){ArrayList<Int>()}
    for(i in 1..N){
        colors[i] = nextInt()
    }
    repeat(N - 1){
        val a = nextInt()
        val b = nextInt()
        graph[a].add(b)
        graph[b].add(a)
    }
    val queue = ArrayDeque<Int>()
    queue.add(1)
    val visit = BooleanArray(N + 1)
    visit[1] = true
    var answer = if(colors[1] == 0) 0 else 1
    while(queue.isNotEmpty()){
        val a = queue.removeFirst()
        for(b in graph[a]){
            if(!visit[b]){
                visit[b] = true
                queue.add(b)
                if(colors[a] != colors[b]) answer++
            }
        }
    }
    println(answer)
}