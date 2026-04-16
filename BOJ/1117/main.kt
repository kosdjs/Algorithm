package BOJ_1117

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val W = nextLong()
    val H = nextLong()
    val f = nextLong()
    val c = nextLong()
    val x1 = nextLong()
    val y1 = nextLong()
    val x2 = nextLong()
    val y2 = nextLong()
    var answer = W * H
    val doubleX = minOf(f, W - f)
    if(doubleX > x1){
        if(doubleX < x2){
            answer -= (doubleX - x1) * (y2 - y1) * (c + 1) * 2
            answer -= (x2 - doubleX) * (y2 - y1) * (c + 1)
        } else {
            answer -= (x2 - x1) * (y2 - y1) * (c + 1) * 2
        }
    } else {
        answer -= (x2 - x1) * (y2 - y1) * (c + 1)
    }
    println(answer)
}