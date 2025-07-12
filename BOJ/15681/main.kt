package BOJ_15681

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val R = st.nextToken().toInt()
    val Q = st.nextToken().toInt()
    val graph = Array(N + 1){ArrayList<Int>()}
    for(i in 1 until N){
        st = StringTokenizer(br.readLine())
        val U = st.nextToken().toInt()
        val V = st.nextToken().toInt()
        graph[U].add(V)
        graph[V].add(U)
    }
    val dp = IntArray(N + 1){1}
    solve(dp, graph, R, R)
    for(i in 1..Q){
        println(dp[br.readLine().toInt()])
    }
}

fun solve(dp: IntArray, graph: Array<ArrayList<Int>>, current: Int, parent: Int): Int{
    if(graph[current].size == 1 && current != parent){
        return dp[current]
    }
    for(v in graph[current]){
        if(v == parent) continue
        dp[current] += solve(dp, graph, v, current)
    }
    return dp[current]
}