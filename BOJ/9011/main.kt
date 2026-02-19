package BOJ_9011

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val sb = StringBuilder()
    repeat(T){
        val n = nextInt()
        var isPossible = true
        val R = IntArray(n)
        val S = IntArray(n)
        val num = ArrayList<Int>()
        for(i in 0 until n){
            R[i] = nextInt()
            num.add(i + 1)
        }
        for(i in n - 1 downTo 0){
            if(R[i] + 1 > num.size){
                isPossible = false
                break
            }
            S[i] = num[R[i]]
            num.removeAt(R[i])
        }
        if(isPossible){
            for(i in 0 until n){
                sb.append(S[i]).append(" ")
            }
        } else {
            sb.append("IMPOSSIBLE")
        }
        sb.append("\n")
    }
    print(sb)
}