package BOJ_30646

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val sum = LongArray(N + 1)
    val map = HashMap<Int, IntArray>()
    for(i in 1..N){
        val a = nextInt()
        sum[i] = sum[i - 1] + a
        if(map.contains(a)) map[a]!![1] = i
        else {
            map[a] = intArrayOf(i, i)
        }
    }
    var answer = 0L
    var count = 0
    for(arr in map.values){
        val curSum = sum[arr[1]] - sum[arr[0] - 1]
        if(curSum > answer){
            answer = curSum
            count = 1
        } else if(curSum == answer) count++
    }
    println("$answer $count")
}