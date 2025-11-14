package BOJ_1967

import java.io.StreamTokenizer

lateinit var graph: ArrayList<ArrayList<IntArray>>
lateinit var visit: BooleanArray

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    graph = arrayListOf()
    for(i in 0..n){
        graph.add(arrayListOf())
    }
    for(i in 1 until n){
        val parent = nextInt()
        val child = nextInt()
        val dist = nextInt()
        graph[parent].add(intArrayOf(child, dist))
        graph[child].add(intArrayOf(parent, dist))
    }
    visit = BooleanArray(n + 1)
    val (end, _) = dfs(1, 0)
    visit = BooleanArray(n + 1)
    val (_, maxDist) = dfs(end, 0)
    println(maxDist)
}

fun dfs(u: Int, dist: Int): IntArray{
    var result = intArrayOf(u, dist)
    visit[u] = true
    for((v, newDist) in graph[u]){
        if(!visit[v]){
            val next = dfs(v, dist + newDist)
            if(next[1] > result[1]){
                result = next
            }
        }
    }
    return result
}