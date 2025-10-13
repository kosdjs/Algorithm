package BOJ_7983

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val homework = PriorityQueue<IntArray>(compareByDescending { it[1] })
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        homework.add(intArrayOf(st.nextToken().toInt(), st.nextToken().toInt()))
    }
    var answer = Int.MAX_VALUE
    while(homework.isNotEmpty()){
        val (d, t) = homework.poll()
        if(answer > t){
            answer = t
        }
        answer -= d
    }
    println(answer)
}