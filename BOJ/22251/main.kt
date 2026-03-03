package BOJ_22251

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    val P = nextInt()
    val X = nextInt()
    var answer = 0
    val diff = arrayOf(
        intArrayOf(0, 4, 3, 3, 4, 3, 2, 3, 1, 2),
        intArrayOf(4, 0, 5, 3, 2, 5, 6, 1, 5, 4),
        intArrayOf(3, 5, 0, 2, 5, 4, 3, 4, 2, 3),
        intArrayOf(3, 3, 2, 0, 3, 2, 3, 2, 2, 1),
        intArrayOf(4, 2, 5, 3, 0, 3, 4, 3, 3, 2),
        intArrayOf(3, 5, 4, 2, 3, 0, 1, 4, 2, 1),
        intArrayOf(2, 6, 3, 3, 4, 1, 0, 5, 1, 2),
        intArrayOf(3, 1, 4, 2, 3, 4, 5, 0, 4, 3),
        intArrayOf(1, 5, 2, 2, 3, 2, 1, 4, 0, 1),
        intArrayOf(2, 4, 3, 1, 2, 1, 2, 3, 1, 0),
    )
    for(i in 1..N){
        if(i == X) continue
        var before = X
        var after = i
        var d = 0
        while(before > 0 || after > 0){
            d += diff[before % 10][after % 10]
            before /= 10
            after /= 10
        }
        if(d <= P) answer++
    }
    println(answer)
}