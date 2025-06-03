package BOJ_1753

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()
    val start = br.readLine().toInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    for(i in 0..V){
        graph.add(arrayListOf())
    }
    for (i in 1..E){
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        graph[u].add(intArrayOf(v, w))
    }
    val dist = IntArray(V + 1){ Int.MAX_VALUE }
    dist[start] = 0
    val visit = BooleanArray(V + 1){false}
    for (route in graph[start]){
        dist[route[0]] = route[1]
    }
    val pq = PriorityQueue<Int>{ o1, o2 -> dist[o1] - dist[o2] }
    pq.add(start)
    while (pq.isNotEmpty()){
        val u = pq.poll()
        if(visit[u]) continue
        visit[u] = true
        for(route in graph[u]){
            val v = route[0]
            val w = route[1]
            if(dist[v] > dist[u] + w){
                dist[v] = dist[u] + w
            }
            pq.add(v)
        }
    }
    for(i in 1..V){
        if(dist[i] != Int.MAX_VALUE){
            println(dist[i])
        } else {
            println("INF")
        }
    }
}