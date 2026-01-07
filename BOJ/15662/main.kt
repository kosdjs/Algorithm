package BOJ_15662

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val cogwheels = Array(T + 1){IntArray(8)}
    val topIdx = IntArray(T + 1)
    for(i in 1..T){
        var cogwheel = nextInt()
        for(j in 7 downTo 0){
            cogwheels[i][j] = cogwheel % 10
            cogwheel /= 10
        }
    }
    fun rotate(n: Int, d: Int){
        topIdx[n] += d * -1
        if(topIdx[n] < 0) topIdx[n] += 8
        topIdx[n] %= 8
    }
    val K = nextInt()
    repeat(K){
        val n = nextInt()
        val d = nextInt()
        val direction = IntArray(T + 1)
        direction[n] = d
        var cur = n
        while(cur > 1){
            val curLeftIdx = if(topIdx[cur] - 2 < 0) topIdx[cur] - 2 + 8 else topIdx[cur] - 2
            val nextRightIdx = (topIdx[cur - 1] + 2) % 8
            if(cogwheels[cur][curLeftIdx] == cogwheels[cur - 1][nextRightIdx]){
                break
            }
            direction[cur - 1] = direction[cur] * -1
            cur--
        }
        cur = n
        while(cur < T){
            val curRightIdx = (topIdx[cur] + 2) % 8
            val nextLeftIdx = if(topIdx[cur + 1] - 2 < 0) topIdx[cur + 1] - 2 + 8 else topIdx[cur + 1] - 2
            if(cogwheels[cur][curRightIdx] == cogwheels[cur + 1][nextLeftIdx]){
                break
            }
            direction[cur + 1] = direction[cur] * -1
            cur++
        }
        for(i in 1..T){
            rotate(i, direction[i])
        }
    }
    var count = 0
    for(i in 1..T){
        if(cogwheels[i][topIdx[i]] == 1) count++
    }
    println(count)
}