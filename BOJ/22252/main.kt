package BOJ_22252

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    val Q = readLine().toInt()
    val map = HashMap<String, PriorityQueue<Int>>()
    var answer = 0L
    for(i in 0 until Q){
        val st = StringTokenizer(readLine())
        val op = st.nextToken().toInt()
        if(op == 1){
            val name = st.nextToken()
            val k = st.nextToken().toInt()
            if(!map.contains(name)){
                map[name] = PriorityQueue<Int>(reverseOrder())
            }
            for(j in 0 until k){
                map[name]!!.add(st.nextToken().toInt())
            }
        } else {
            val name = st.nextToken()
            val b = st.nextToken().toInt()
            val pq = map[name]
            if(pq != null){
                for(j in 0 until b){
                    if(pq.isEmpty()) break
                    answer += pq.poll()
                }
            }
        }
    }
    println(answer)
}