package BOJ_3019

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val C = nextInt()
    val P = nextInt()
    var answer = 0
    val map = IntArray(C)
    for(i in 0 until C){
        map[i] = nextInt()
    }
    when(P){
        1 -> {
            answer += C
            for(i in 0 until C - 3){
                if(map[i] == map[i + 1] && map[i + 1] == map[i + 2] && map[i + 2] == map[i + 3]) answer++
            }
        }
        2 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1]) answer++
            }
        }
        3 -> {
            for(i in 0 until C - 1){
                if(map[i] - 1 == map[i + 1]) answer++
                if(i < C - 2 && map[i] == map[i + 1] && map[i + 1] == map[i + 2] - 1) answer++
            }
        }
        4 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1] - 1) answer++
                if(i < C - 2 && map[i] - 1 == map[i + 1] && map[i + 1] == map[i + 2]) answer++
            }
        }
        5 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1] - 1) answer++
                if(map[i] - 1 == map[i + 1]) answer++
                if(i < C - 2){
                    if(map[i] - 1 == map[i + 1] && map[i + 1] == map[i + 2] - 1) answer++
                    if(map[i] == map[i + 1] && map[i + 1] == map[i + 2]) answer++
                }
            }
        }
        6 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1]) answer++
                if(map[i] - 2 == map[i + 1]) answer++
                if(i < C - 2){
                    if(map[i] == map[i + 1] && map[i + 1] == map[i + 2]) answer++
                    if(map[i] == map[i + 1] - 1 && map[i + 1] == map[i + 2]) answer++
                }
            }
        }
        7 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1]) answer++
                if(map[i] == map[i + 1] - 2) answer++
                if(i < C - 2){
                    if(map[i] == map[i + 1] && map[i + 1] == map[i + 2]) answer++
                    if(map[i] == map[i + 1] && map[i + 1] - 1 == map[i + 2]) answer++
                }
            }
        }
    }
    println(answer)
}