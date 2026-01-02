package BOJ_20002

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var answer = Int.MIN_VALUE
    val arr = Array(N + 1){ IntArray(N + 1) }
    for(i in 1..N){
        for(j in 1..N){
            arr[i][j] = nextInt() + arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1]
            var k = 1
            while(i - k >= 0 && j - k >= 0){
                answer = maxOf(answer, arr[i][j] - arr[i - k][j] - arr[i][j - k] + arr[i - k][j - k])
                k++
            }
        }
    }
    println(answer)
}