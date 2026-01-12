package BOJ_8981

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int?{
        val token = nextToken()
        return if(token == StreamTokenizer.TT_EOF) null else nval.toInt()
    }
    val N = nextInt()
    if(N == null){
        println(-1)
        return
    } else {
        val arr = IntArray(N)
        var idx = 0
        repeat(N){
            val num = nextInt()
            if(num == null){
                println(-1)
                return@run
            } else {
                while(arr[idx] != 0){
                    idx = (idx + 1) % N
                }
                arr[idx] = num
                idx = (idx + num) % N
            }
        }
        println(N)
        for(i in 0 until N){
            print("${arr[i]} ")
        }
    }
}