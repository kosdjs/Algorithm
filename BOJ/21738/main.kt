package BOJ_21738

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val S = nextInt()
    val P = nextInt()
    var answer = N - 1
    var count = 2
    val graph = Array(N + 1){ArrayList<Int>()}
    for(i in 1 until N){
        val A = nextInt()
        val B = nextInt()
        graph[A].add(B)
        graph[B].add(A)
    }
    val queue = ArrayDeque<Int>()
    val visit = IntArray(N + 1){-1}
    queue.add(P)
    visit[P] = 0
    while(queue.isNotEmpty()){
        val A = queue.removeFirst()
        for(B in graph[A]){
            if(visit[B] == -1){
                visit[B] = visit[A] + 1
                queue.add(B)
                if(B <= S){
                    if(count > 0){
                        count--
                        answer -= visit[B]
                    } else {
                        break
                    }
                }
            }
        }
        if(count == 0) break
    }
    println(answer)
}