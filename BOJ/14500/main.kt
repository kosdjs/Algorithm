package BOJ_14500

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val map = Array(N){IntArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
        }
    }
    var answer = 0
    for(i in 0 until N ){
        for(j in 0 until M){
            if(i <= N - 2 && j <= M - 2){
                val box = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1]
                answer = maxOf(answer, box)
            }
            if(i <= N - 4){
                val longVertical = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 3][j]
                answer = maxOf(answer, longVertical)
            }
            if(j <= M - 4){
                val longHorizontal = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3]
                answer = maxOf(answer, longHorizontal)
            }
            if(i <= N - 3 && j <= M - 2){
                val tVertical1 = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j]
                answer = maxOf(answer, tVertical1)
                val tVertical2 = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1]
                answer = maxOf(answer, tVertical2)
                val sVertical1 = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1]
                answer = maxOf(answer, sVertical1)
                val sVertical2 = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j]
                answer = maxOf(answer, sVertical2)
                val lVertical1 = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 2][j]
                answer = maxOf(answer, lVertical1)
                val lVertical2 = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1]
                answer = maxOf(answer, lVertical2)
                val lVertical3 = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j + 1]
                answer = maxOf(answer, lVertical3)
                val lVertical4 = map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j] + map[i + 2][j + 1]
                answer = maxOf(answer, lVertical4)
            }
            if(i <= N - 2 && j <= M - 3){
                val tHorizontal1 = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]
                answer = maxOf(answer, tHorizontal1)
                val tHorizontal2 = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, tHorizontal2)
                val sHorizontal1 = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, sHorizontal1)
                val sHorizontal2 = map[i][j + 1] + map[i][j + 2] + map[i + 1][j] + map[i + 1][j + 1]
                answer = maxOf(answer, sHorizontal2)
                val lHorizontal1 = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j]
                answer = maxOf(answer, lHorizontal1)
                val lHorizontal2 = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 2]
                answer = maxOf(answer, lHorizontal2)
                val lHorizontal3 = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, lHorizontal3)
                val lHorizontal4 = map[i][j + 2] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, lHorizontal4)
            }
        }
    }
    println(answer)
}