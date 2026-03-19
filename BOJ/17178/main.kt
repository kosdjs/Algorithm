package BOJ_17178

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    val N = readLine().toInt()
    data class Ticket(val alp: Char, val num: Int): Comparable<Ticket>{
        override fun compareTo(other: Ticket): Int {
            return if(this.alp != other.alp) this.alp.compareTo(other.alp) else this.num.compareTo(other.num)
        }
    }
    val arr = Array(N){Array(5){Ticket('A', 0)} }
    val pq = PriorityQueue<Ticket>()
    for(i in 0 until N){
        val st = StringTokenizer(readLine())
        for(j in 0 until 5){
            val s = st.nextToken()
            val st2 = StringTokenizer(s, "-")
            arr[i][j] = Ticket(st2.nextToken()[0], st2.nextToken().toInt())
            pq.add(arr[i][j])
        }
    }
    val stack = Array(5 * N){Ticket('A', 0)}
    var top = -1
    for(i in 0 until N){
        for(j in 0 until 5){
            stack[++top] = arr[i][j]
            while(top >= 0){
                if(stack[top] == pq.peek()){
                    top--
                    pq.poll()
                } else break
            }
        }
        while(top >= 0){
            if(stack[top] == pq.peek()){
                top--
                pq.poll()
            } else break
        }
    }
    println(if(pq.isEmpty()) "GOOD" else "BAD")
}