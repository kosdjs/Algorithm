package BOJ_1914

import java.io.StreamTokenizer
import java.math.BigInteger

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    val bw = System.out.bufferedWriter()
    bw.append("${BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE)}\n")
    if(N <= 20){
        fun printTower(level: Int, start: Int, empty: Int, target: Int){
            if(level == 0) return
            printTower(level - 1, start, target, empty)
            bw.append("$start $target\n")
            printTower(level - 1, empty, start, target)
        }
        printTower(N, 1, 2, 3)
    }
    bw.flush()
    bw.close()
}