package BOJ_14217

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val m = nextInt()
    val graph = Array(n + 1){ArrayList<Int>()}
    for(i in 0 until m){
        val a = nextInt()
        val b = nextInt()
        graph[a].add(b)
        graph[b].add(a)
    }
    val dist = IntArray(n + 1){Int.MAX_VALUE}
    val sb = StringBuilder()
    val queue = ArrayDeque<Int>()
    for(k in 0 until nextInt()){
        val a = nextInt()
        val i = nextInt()
        val j = nextInt()
        if(a == 1){
            graph[i].add(j)
            graph[j].add(i)
        } else {
            graph[i].remove(j)
            graph[j].remove(i)
        }
        dist.fill(Int.MAX_VALUE)
        dist[1] = 0
        //bfs
        queue.add(1)
        while(queue.isNotEmpty()){
            val x = queue.removeFirst()
            for(y in graph[x]){
                if(dist[y] == Int.MAX_VALUE){
                    dist[y] = dist[x] + 1
                    queue.add(y)
                }
            }
        }
        for(l in 1..n){
            sb.append(if(dist[l] != Int.MAX_VALUE) dist[l] else -1).append(" ")
        }
        sb.append("\n")
    }
    print(sb)
}