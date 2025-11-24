package BOJ_11725

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt():Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val graph = ArrayList<ArrayList<Int>>()
    for(i in 0..N){
        graph.add(arrayListOf())
    }
    for(i in 1 until N){
        val u = nextInt()
        val v = nextInt()
        graph[u].add(v)
        graph[v].add(u)
    }
    val parents = IntArray(N + 1)
    val visit = BooleanArray(N + 1)
    fun dfs(u: Int){
        visit[u] = true
        for(v in graph[u]){
            if(!visit[v]){
                parents[v] = u
                dfs(v)
            }
        }
    }
    dfs(1)
    val bw = System.out.bufferedWriter()
    for(i in 2..N){
        bw.append("${parents[i]}")
        bw.newLine()
    }
    bw.flush()
    bw.close()
}