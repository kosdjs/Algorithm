package BOJ_12851

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    val queue = ArrayDeque<IntArray>()
    if(N != K){
        queue.add(intArrayOf(N, 0))
    }
    val dist = IntArray(100001){Int.MAX_VALUE}
    dist[N] = 0
    val count = IntArray(100001)
    count[N] = 1
    while(queue.isNotEmpty()){
        val(x, d) = queue.removeFirst()
        if(d > dist[x]) continue
        for(nx in intArrayOf(x - 1, x + 1, x * 2)){
            if(nx < 0 || nx > 100000) continue
            if(d + 1 < dist[nx]){
                dist[nx] = d + 1
                count[nx] = count[x]
                queue.add(intArrayOf(nx, d + 1))
            } else if(d + 1 == dist[nx]){
                count[nx] += count[x]
            }
        }
    }
    println(dist[K])
    println(count[K])
}