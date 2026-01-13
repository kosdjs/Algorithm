package BOJ_5588

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val m = nextInt()
    val stars = mutableListOf<Pair<Int, Int>>()
    repeat(m){
        stars.add(Pair(nextInt(), nextInt()))
    }
    val n = nextInt()
    val picture = mutableSetOf<Pair<Int, Int>>()
    repeat(n){
        picture.add(Pair(nextInt(), nextInt()))
    }
    for((x, y) in picture){
        val diffX = x - stars[0].first
        val diffY = y - stars[0].second
        var match = true
        for((nx, ny) in stars){
            if(Pair(nx + diffX, ny + diffY) !in picture){
                match = false
                break
            }
        }
        if(match){
            println("$diffX $diffY")
        }
    }
}