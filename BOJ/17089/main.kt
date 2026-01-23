package BOJ_17089

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val friends = Array(N + 1){mutableListOf<Int>()}
    repeat(M){
        val A = nextInt()
        val B = nextInt()
        friends[A].add(B)
        friends[B].add(A)
    }
    var answer = Int.MAX_VALUE
    for(a in 1..N){
        for(b in friends[a].indices){
            for(c in b + 1 until friends[a].size){
                if(friends[a][c] in friends[friends[a][b]]){
                    answer = minOf(answer, friends[a].size + friends[friends[a][b]].size + friends[friends[a][c]].size - 6)
                }
            }
        }
    }
    println(if(answer != Int.MAX_VALUE) answer else -1)
}