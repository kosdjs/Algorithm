package BOJ_1068

import java.io.*
import java.util.*
import kotlin.collections.ArrayDeque

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    var start = 0
    val graph = arrayListOf<ArrayList<Int>>()
    var answer = 0
    val visit = BooleanArray(N){false}
    visit[br.readLine().toInt()] = true
    for(i in 0 until N){
        graph.add(arrayListOf())
    }
    for(i in 0 until N){
        val j = st.nextToken().toInt()
        if(j == -1){
            start = i
        } else {
            graph[j].add(i)
        }
    }
    val queue = ArrayDeque<Int>()
    queue.add(start)
    while(queue.isNotEmpty()){
        val i = queue.removeFirst()
        if(visit[i]) continue
        var count = 0
        for(j in graph[i]){
            if(visit[j]) continue
            else{
                count++
                queue.add(j)
            }
        }
        if(count == 0){
            answer++
        }
    }
    println(answer)
}