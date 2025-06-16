package BOJ_14567

import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val result = IntArray(N + 1)
    val graph = ArrayList<ArrayList<Int>>()
    val prerequisite = IntArray(N + 1){0}
    for(i in 0..N){
        graph.add(arrayListOf())
    }
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        graph[u].add(v)
        prerequisite[v] += 1
    }
    val queue = ArrayDeque<IntArray>()
    for(i in 1..N){
        if(prerequisite[i] == 0){
            queue.add(intArrayOf(i, 1))
        }
    }
    while (queue.isNotEmpty()){
        val temp = queue.poll()
        val u = temp[0]
        val level = temp[1]
        if(prerequisite[u] == 0){
            result[u] = level
            for(v in graph[u]){
                prerequisite[v] -= 1
                if(prerequisite[v] == 0){
                    queue.add(intArrayOf(v, level + 1))
                }
            }
        }
    }
    for(i in 1..N){
        print("${result[i]} ")
    }
}