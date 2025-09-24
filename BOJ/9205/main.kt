package BOJ_9205

import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    repeat(t){
        val n = br.readLine().toInt()
        val map = Array(n + 2){ IntArray(2) }
        for(i in 0 until n + 2){
            val st = StringTokenizer(br.readLine())
            for(j in 0..1){
                map[i][j] = st.nextToken().toInt()
            }
        }
        val visit = BooleanArray(n + 2)
        visit[0] = true
        val queue = ArrayDeque<IntArray>()
        queue.add(map[0])
        while (queue.isNotEmpty()){
            val (x, y) = queue.removeFirst()
            for(i in 0 until n + 2){
                if(!visit[i]){
                    if(abs(map[i][0] - x) + abs(map[i][1] - y) <= 1000){
                        visit[i] = true
                        queue.add(map[i])
                    }
                }
            }
        }
        if(visit[n + 1]){
            println("happy")
        } else {
            println("sad")
        }
    }
}