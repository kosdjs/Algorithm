package BOJ_1167

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val V = nextInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    var visit = BooleanArray(V + 1)
    for(i in 0..V){
        graph.add(arrayListOf())
    }
    for(i in 1..V){
        val u = nextInt()
        while (true){
            val v = nextInt()
            if(v == -1){
                break
            }
            val dist = nextInt()
            graph[u].add(intArrayOf(v, dist))
            graph[v].add(intArrayOf(u, dist))
        }
    }
    fun dfs(u: Int, dist: Int): IntArray{
        var result = intArrayOf(u, dist)
        visit[u] = true
        for((v, vDist) in graph[u]){
            if(!visit[v]){
                val next = dfs(v, dist + vDist)
                if(next[1] > result[1]) result = next
            }
        }
        return result
    }
    val (end, _) = dfs(1, 0)
    visit = BooleanArray(V + 1)
    val (_, maxDist) = dfs(end, 0)
    println(maxDist)
}