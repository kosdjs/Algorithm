package BOJ_2116

import java.util.StringTokenizer

lateinit var dices: Array<IntArray>
val opposite = intArrayOf(5, 3, 4, 1, 2, 0)
var answer = 0
var n = 0

fun main(){
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    dices = Array(n){ IntArray(6) }
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until 6){
            dices[i][j] = st.nextToken().toInt()
        }
    }
    for(i in 0 until 6){
        solve(0, i, 0)
    }
    println(answer)
}

fun solve(level: Int, bottom: Int, sum: Int){
    if(level == n){
        answer = maxOf(answer, sum)
        return
    }
    var max = 0
    var nextBottom = 0
    for(i in 0 until 6){
        if(level + 1 < n && dices[level + 1][i] == dices[level][opposite[bottom]]){
            nextBottom = i
        }
        if(i == bottom || i == opposite[bottom]){
            continue
        }
        max = maxOf(max, dices[level][i])
    }
    solve(level + 1, nextBottom, sum + max)
}