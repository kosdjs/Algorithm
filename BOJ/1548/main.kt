package BOJ_1548

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    arr.sort()
    var answer = minOf(2, N)
    if(answer != N){
        for(i in 0 until N - 2){
            var j = arr.size - 1
            if(j - i + 1 < answer) break
            while(j >= i + 2){
                if(arr[i] + arr[i + 1] > arr[j]){
                    answer = maxOf(answer, j - i + 1)
                    break
                }
                j--
            }
        }
    }
    println(answer)
}