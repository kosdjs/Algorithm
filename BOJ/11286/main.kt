package BOJ_11286

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.absoluteValue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val queue = PriorityQueue<Int>{ o1, o2 ->
        if(o1.absoluteValue != o2.absoluteValue) o1.absoluteValue - o2.absoluteValue
        else o1 - o2
    }
    val sb = StringBuilder()
    repeat(N){
        val num = nextInt()
        if(num != 0) queue.add(num)
        else{
            if(queue.isNotEmpty()) sb.append(queue.poll())
            else sb.append(0)
            sb.append("\n")
        }
    }
    print(sb)
}