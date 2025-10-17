package BOJ_6068

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val work = PriorityQueue<IntArray>(compareByDescending { it[1] })
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        work.add(intArrayOf(st.nextToken().toInt(), st.nextToken().toInt()))
    }
    var answer = Int.MAX_VALUE
    while (work.isNotEmpty()){
        val (t, s) = work.poll()
        answer = minOf(answer, s) - t
    }
    println(if(answer < 0) -1 else answer)
}