package BOJ_17265

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val map = Array(N){ CharArray(N) }
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until N){
            map[i][j] = st.nextToken()[0]
        }
    }
    val max = Array(N){ IntArray(N){Int.MIN_VALUE} }
    val min = Array(N){ IntArray(N){Int.MAX_VALUE} }
    max[0][0] = map[0][0].digitToInt()
    min[0][0] = map[0][0].digitToInt()
    for(j in 2 until N step 2){
        max[0][j] = op(max[0][j - 2], map[0][j - 1], map[0][j])
        min[0][j] = op(min[0][j - 2], map[0][j - 1], map[0][j])
    }
    for(i in 2 until N step 2){
        max[i][0] = op(max[i - 2][0], map[i - 1][0], map[i][0])
        min[i][0] = op(min[i - 2][0], map[i - 1][0], map[i][0])
    }
    for(i in 1 until N){
        var startIdx = if(i % 2 == 0) 2 else 1
        for(j in startIdx until N step 2){
            var maxResult = Int.MIN_VALUE
            var minResult = Int.MAX_VALUE
            if(i > 1){
                maxResult = maxOf(maxResult, op(max[i - 2][j], map[i - 1][j], map[i][j]))
                minResult = minOf(minResult, op(min[i - 2][j], map[i - 1][j], map[i][j]))
            }
            if(j > 1){
                maxResult = maxOf(maxResult, op(max[i][j - 2], map[i][j - 1], map[i][j]))
                minResult = minOf(minResult, op(min[i][j - 2], map[i][j - 1], map[i][j]))
            }
            maxResult = maxOf(maxResult, op(max[i - 1][j - 1], map[i][j - 1], map[i][j]))
            minResult = minOf(minResult, op(min[i - 1][j - 1], map[i][j - 1], map[i][j]))
            maxResult = maxOf(maxResult, op(max[i - 1][j - 1], map[i - 1][j], map[i][j]))
            minResult = minOf(minResult, op(min[i - 1][j - 1], map[i - 1][j], map[i][j]))
            max[i][j] = maxResult
            min[i][j] = minResult
        }
    }
    println("${max[N - 1][N - 1]} ${min[N - 1][N - 1]}")
}

fun op(num1: Int, op: Char, num2: Char): Int{
    if(op == '+'){
        return num1 + num2.digitToInt()
    } else if(op == '-'){
        return num1 - num2.digitToInt()
    } else {
        return num1 * num2.digitToInt()
    }
}