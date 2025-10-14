package BOJ_23843

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val time = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        time[i] = st.nextToken().toInt()
    }
    time.sortDescending()
    val queue = PriorityQueue<Int>()
    for(i in 0 until N){
        if(queue.size < M){
            queue.add(time[i])
        } else {
            queue.add(queue.poll() + time[i])
        }
    }
    println(queue.max())
}