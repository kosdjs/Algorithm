package BOJ_14712

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val map = Array(N){ BooleanArray(M) }
    var answer = 0
    fun check(count: Int){
        if(count == N * M){
            answer++
            return
        }
        val i = count / M
        val j = count % M
        map[i][j] = false
        check(count + 1)
        if(i == 0 || j == 0 || !(map[i - 1][j - 1] && map[i - 1][j] && map[i][j - 1])){
            map[i][j] = true
            check(count + 1)
            map[i][j] = false
        }
    }
    check(0)
    println(answer)
}