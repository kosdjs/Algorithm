package BOJ_21773

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    class Process(val id: Int, var time: Int, var priority: Int): Comparable<Process>{
        override fun compareTo(other: Process): Int {
            return if (this.priority != other.priority) {
                other.priority - this.priority
            } else {
                this.id - other.id
            }
        }
    }
    var T = nextInt()
    val n = nextInt()
    val queue = PriorityQueue<Process>()
    for(i in 0 until n){
        queue.add(Process(nextInt(), nextInt(), nextInt()))
    }
    val sb = StringBuilder()
    while(queue.isNotEmpty() && T > 0){
        val p = queue.poll()
        sb.append(p.id).append("\n")
        p.time--
        p.priority--
        if(p.time > 0) queue.add(p)
        T--
    }
    print(sb)
}