package BOJ_5549

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextString(): String{
        nextToken()
        return sval
    }
    val M = nextInt()
    val N = nextInt()
    val K = nextInt()
    val jungle = Array(M + 1){IntArray(N + 1)}
    val ocean = Array(M + 1){IntArray(N + 1)}
    val ice = Array(M + 1){IntArray(N + 1)}
    for(i in 1..M){
        val s = nextString()
        for(j in 1..N){
            jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1]
            ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1]
            ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1]
            when(s[j - 1]){
                'J' -> jungle[i][j]++
                'O' -> ocean[i][j]++
                'I' -> ice[i][j]++
            }
        }
    }
    val sb = StringBuilder()
    repeat(K){
        val a = nextInt()
        val b = nextInt()
        val c = nextInt()
        val d = nextInt()
        sb.append(jungle[c][d] - jungle[c][b - 1] - jungle[a - 1][d] + jungle[a - 1][b - 1])
        sb.append(' ')
        sb.append(ocean[c][d] - ocean[c][b - 1] - ocean[a - 1][d] + ocean[a - 1][b - 1])
        sb.append(' ')
        sb.append(ice[c][d] - ice[c][b - 1] - ice[a - 1][d] + ice[a - 1][b - 1])
        sb.append("\n")
    }
    print(sb)
}