package BOJ_2666

import kotlin.math.abs
import kotlin.math.min

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val doors = br.readLine().split(" ").map { it -> it.toInt() }
    val t = br.readLine().toInt()
    val arr = IntArray(t)
    for(i in 0 until t){
        arr[i] = br.readLine().toInt()
    }
//    val dp = Array(t + 1){IntArray(2){Int.MAX_VALUE} }
//    dp[0] = IntArray(2){0}
//    solve(dp, doors[0], doors[1], 0, arr, 0, t)
//    println(min(dp[t][0], dp[t][1]))
    val dp = Array(t + 1){Array(n + 1){ IntArray(n + 1){Int.MAX_VALUE} } }
    dp[0][doors[0]][doors[1]] = 0
    for(level in 0 until t){
        for(i in 1..n){
            for(j in 1..n){
                if(i == j || dp[level][i][j] == Int.MAX_VALUE) continue
                val countI = dp[level][i][j] + abs(i - arr[level])
                if(dp[level + 1][arr[level]][j] > countI){
                    dp[level + 1][arr[level]][j] = countI
                }
                val countJ = dp[level][i][j] + abs(j - arr[level])
                if(dp[level + 1][i][arr[level]] > countJ)
                    dp[level + 1][i][arr[level]] = countJ
            }
        }
    }
    var answer = Int.MAX_VALUE
    for(i in 1..n){
        for(j in 1..n){
            if(answer > dp[t][i][j]) answer = dp[t][i][j]
        }
    }
    println(answer)
}

//fun solve(dp: Array<IntArray>, door1: Int, door2: Int, level: Int, arr: IntArray, cur: Int, t: Int){
//    if(level == t){
//        return
//    } else {
//        dp[level + 1][0] = min(dp[level + 1][0], cur + abs(door1 - arr[level]))
//        solve(dp, arr[level], door2, level + 1, arr, cur + abs(door1 - arr[level]), t)
//        dp[level + 1][1] = min(dp[level + 1][1], cur + abs(door2 - arr[level]))
//        solve(dp, door1, arr[level], level + 1, arr, cur + abs(door2 - arr[level]), t)
//    }
//}