package BOJ_10830

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val N = nextInt()
    val B = nextLong()
    val A = Array(N){IntArray(N)}
    for(i in 0 until N){
        for(j in 0 until N){
            A[i][j] = nextInt()
        }
    }
    fun times(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray>{
        val result = Array(N){ IntArray(N) }
        for(i in 0 until N){
            for(j in 0 until N){
                for(k in 0 until N){
                    result[i][j] += A[i][k] * B[k][j]
                }
                result[i][j] %= 1000
            }
        }
        return result
    }
    //반복문으로 B승 구하기
    fun pow(base: Array<IntArray>, B: Long): Array<IntArray> {
        var result = Array(N){IntArray(N)}
        for(i in 0 until N){
            result[i][i] = 1
        }
        var mat = base
        var exp = B
        while (exp > 0) {
            if (exp % 2 == 1L) result = times(result, mat)
            mat = times(mat, mat)
            exp /= 2
        }
        return result
    }
    val result = pow(A, B)
    val sb = StringBuilder()
    for(i in 0 until N){
        for(j in 0 until N){
            sb.append(result[i][j]).append(" ")
        }
        sb.append("\n")
    }
    print(sb)
}