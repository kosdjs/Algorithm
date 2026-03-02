package BOJ_23048

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    var count = 1
    var colors = IntArray(N + 1)
    colors[1] = 1
    for(i in 2..N){
        if(colors[i] == 0){
            count++
            var idx = i
            while(idx <= N){
                colors[idx] = count
                idx += i
            }
        }
    }
    val sb = StringBuilder()
    sb.append(count).append("\n")
    for(i in 1..N){
        sb.append(colors[i]).append(" ")
    }
    print(sb)
}