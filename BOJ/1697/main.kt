package BOJ_1697

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    if(N == K){
        println(0)
        return
    }
    val visit = BooleanArray(100001)
    visit[N] = true
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(N, 0))
    while(queue.isNotEmpty()){
        val (x, d) = queue.removeFirst()
        for(nx in intArrayOf(x + 1, x - 1, x * 2)){
            if(nx == K){
                println(d + 1)
                return
            }
            if(nx >= 0 && nx <= 100000 && !visit[nx]){
                visit[nx] = true
                queue.add(intArrayOf(nx, d + 1))
            }
        }
    }
}