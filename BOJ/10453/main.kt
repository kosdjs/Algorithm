package BOJ_10453

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val T = nval.toInt()
    fun nextString(): String{
        nextToken()
        return sval
    }
    val sb = StringBuilder()
    repeat(T){
        val A = nextString()
        val B = nextString()
        val APos = IntArray(A.length / 2)
        val BPos = IntArray(B.length / 2)
        var Aidx = 0
        var Bidx = 0
        for(i in 0 until A.length){
            if(A[i] == 'a'){
                APos[Aidx] = i
                Aidx++
            }
            if(B[i] == 'a'){
                BPos[Bidx] = i
                Bidx++
            }
        }
        var answer = 0
        for(i in 0 until APos.size){
            answer += abs(APos[i] - BPos[i])
        }
        sb.append(answer).append("\n")
    }
    print(sb)
}