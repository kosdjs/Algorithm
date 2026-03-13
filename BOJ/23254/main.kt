package BOJ_23254

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    var hour = nextInt() * 24
    val M = nextInt()
    val aArr = IntArray(M)
    val bArr = IntArray(M)
    for(i in 0 until M){
        aArr[i] = nextInt()
    }
    for(i in 0 until M){
        bArr[i] = nextInt()
    }
    val count = IntArray(101)
    var totalScore = 0L

    for (i in 0 until M) {
        totalScore += aArr[i] // 기본 점수 합산
        val diff = 100 - aArr[i]

        // b만큼씩 몇 번 더할 수 있는지
        count[bArr[i]] += (diff / bArr[i])
        // b보다 작은 나머지 점수 처리
        if (diff % bArr[i] > 0) {
            count[diff % bArr[i]] += 1
        }
    }

    // 큰 상승분(100)부터 차례대로 공부 시간 할당
    for (i in 100 downTo 1) {
        if (hour <= 0) break
        val study = minOf(hour, count[i])
        totalScore += study * i
        hour -= study
    }

    println(totalScore)
}