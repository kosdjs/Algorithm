package BOJ_1987

import java.io.StreamTokenizer

lateinit var map: Array<CharArray>
lateinit var visitedBits: Array<IntArray>
var R = 0
var C = 0
val dy = intArrayOf(0, -1, 0, 1)
val dx = intArrayOf(-1, 0, 1, 0)
var answer = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextString(): String{
        nextToken()
        return sval.toString()
    }
    R = nextInt()
    C = nextInt()
    map = Array(R){ CharArray(C) }
    visitedBits = Array(R){ IntArray(C) }
    for(i in 0 until R){
        val s = nextString()
        for(j in 0 until C){
            map[i][j] = s[j]
        }
    }
    dfs(0, 0, 1, 1 shl (map[0][0] - 'A'))
    println(answer)
}

fun dfs(y: Int, x: Int, dist: Int, bits: Int){
    if(visitedBits[y][x] == bits) return
    visitedBits[y][x] = bits
    if(answer < dist){
        answer = dist
    }
    for(k in 0 until 4){
        val ny = y + dy[k]
        val nx = x + dx[k]
        if(ny < 0 || ny >= R || nx < 0 || nx >= C){
            continue
        }
        val curBit = 1 shl (map[ny][nx] - 'A')
        if(curBit and bits == 0){
            dfs(ny, nx, dist + 1, bits or curBit)
        }
    }
}