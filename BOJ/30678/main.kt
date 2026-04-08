package BOJ_30678

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    val size = IntArray(N + 1)
    size[0] = 1
    for(i in 1..N){
        size[i] = size[i - 1] * 5
    }
    val result = Array(size[N]){CharArray(size[N]){' '}}
    fun star(i: Int, j: Int, count: Int){
        if(count == 0){
            result[i][j] = '*'
            return
        }
        val s = size[count - 1]
        star(i, j + s * 2, count - 1)
        star(i + s, j + s * 2, count - 1)
        for(t in 0 until 5) star(i + s * 2, j + s * t, count - 1)
        for(t in 1 until 4) star(i + s * 3, j + s * t, count - 1)
        for(t in 1 until 4 step 2) star(i + s * 4, j + s * t, count - 1)
    }
    star(0, 0, N)
    val sb = StringBuilder()
    for(i in 0 until result.size){
        sb.append(result[i]).append("\n")
    }
    print(sb)
}