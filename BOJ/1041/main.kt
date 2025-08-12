package BOJ_1041

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val dice = IntArray(6)
    var min1 = Long.MAX_VALUE
    var min2 = Long.MAX_VALUE
    var min3 = Long.MAX_VALUE
    for(i in 0 until 6){
        dice[i] = st.nextToken().toInt()
        min1 = minOf(min1, dice[i].toLong())
    }
    val twoSides = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, 2),
        intArrayOf(0, 3),
        intArrayOf(0, 4),
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 5),
        intArrayOf(2, 4),
        intArrayOf(2, 5),
        intArrayOf(3, 4),
        intArrayOf(3, 5),
        intArrayOf(4, 5)
    )
    val threeSides = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(0, 1, 3),
        intArrayOf(0, 2, 4),
        intArrayOf(0, 3, 4),
        intArrayOf(5, 1, 2),
        intArrayOf(5, 1, 3),
        intArrayOf(5, 2, 4),
        intArrayOf(5, 3, 4)
    )
    for(arr in twoSides){
        var sum = 0
        for(i in arr){
            sum += dice[i]
        }
        min2 = minOf(min2, sum.toLong())
    }
    for(arr in threeSides){
        var sum = 0
        for(i in arr){
            sum += dice[i]
        }
        min3 = minOf(min3, sum.toLong())
    }
    if(N > 1){
        val answer = (min3 * 4) + (min2 * 4) + min2 * (N - 2) * 8 + min1 * (N - 2) * (N - 2) * 5 + min1 * (N - 2) * 4
        println(answer)
    } else {
        var max = 0
        var answer = 0
        for(i in 0 until 6){
            answer += dice[i]
            max = maxOf(max, dice[i])
        }
        answer -= max
        println(answer)
    }
}