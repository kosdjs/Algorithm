package BOJ_1684

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val arr = IntArray(n){nextInt()}
    arr.sort()
    for(i in n - 1 downTo 1){
        arr[i] -= arr[i - 1]
    }
    fun gcd(a: Int, b: Int): Int{
        var x = maxOf(a, b)
        var y = minOf(a, b)
        var r: Int
        while (true){
            if(x == 0) return y
            if(y == 0) return x
            r = x % y
            x = y
            y = r
        }
    }
    var answer = arr[1]
    for(i in 2 until n){
        answer = gcd(answer, arr[i])
    }
    println(answer)
}