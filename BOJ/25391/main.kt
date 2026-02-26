package BOJ_25391

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var M = nextInt()
    var K = nextInt()
    val queue1 = PriorityQueue<IntArray>{o1, o2 -> o2[1] - o1[1]}
    val queue2 = PriorityQueue<IntArray>{o1, o2 -> o2[0] - o1[0]}
    for(i in 0 until N){
        val arr = intArrayOf(nextInt(), nextInt())
        queue1.add(arr)
    }
    var answer = 0L
    while(K > 0){
        val (a, _) = queue1.poll()
        answer += a
        K--
    }
    queue2.addAll(queue1)
    while(M > 0){
        val (a, _) = queue2.poll()
        answer += a
        M--
    }
    println(answer)
}