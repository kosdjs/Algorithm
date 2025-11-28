package BOJ_11053

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
    val tails = IntArray(N)
    var length = 0
    for(x in arr){
        var left = 0
        var right = length
        while(left < right){
            val mid = (left + right) / 2
            if(tails[mid] < x) left = mid + 1
            else right = mid
        }
        tails[left] = x
        if(left == length) length++
    }
    println(length)
}