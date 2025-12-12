package BOJ_16953

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val A = nextInt()
    val B = nextInt()
    var count = 1
    var num = B
    while(num > 0){
        if(num == A){
            break
        }
        if(num % 10 == 1){
            num /= 10
        } else if(num % 2 == 0){
            num /= 2
        } else {
            count = -1
            break
        }
        count++
    }
    if(num == 0) count = -1
    println(count)
}