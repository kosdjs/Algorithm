package BOJ_2877

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val K = nextInt()
    val bin = (K + 1).toString(2)
    val sb = StringBuilder()
    for(i in 1 until bin.length){
        sb.append(if(bin[i] == '0') 4 else 7)
    }
    print(sb)
}