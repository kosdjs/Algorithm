package BOJ_21315

import java.io.StreamTokenizer
import kotlin.math.floor
import kotlin.math.log2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val target = IntArray(N)
    for(i in 0 until N){
        target[i] = nextInt()
    }
    val maxK = floor(log2(N.toDouble())).toInt()
    val cards = IntArray(N){it + 1}
    fun shuffle(K: Int, cards: IntArray, result: IntArray){
        var cardsIdx = 0
        for(i in 1 shl K until result.size){
            result[i] = cards[cardsIdx++]
        }
        for(i in K - 1 downTo 0){
            var resultIdx = 1 shl i
            repeat((1 shl i)){
                result[resultIdx++] = cards[cardsIdx++]
            }
        }
        result[0] = cards.last()
    }
    val first = IntArray(N)
    val second = IntArray(N)
    for(K1 in 1..maxK){
        shuffle(K1, cards, first)
        for(K2 in 1..maxK){
            shuffle(K2, first, second)
            if(second.contentEquals(target)){
                println("$K1 $K2")
                return@run
            }
        }
    }
}