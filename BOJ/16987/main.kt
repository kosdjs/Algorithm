package BOJ_16987

import java.util.StringTokenizer

var N = 0
lateinit var egg: Array<IntArray>
var answer = 0

fun main(){
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    egg = Array(N){IntArray(2)}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        egg[i] = intArrayOf(st.nextToken().toInt(), st.nextToken().toInt())
    }
    solve(0)
    println(answer)
}

fun solve(level: Int){
    if(level == N){
        var sum = 0
        for(i in 0 until N){
            if(egg[i][0] <= 0){
                sum++
            }
        }
        answer = maxOf(answer, sum)
    } else {
        if(egg[level][0] <= 0){
            solve(level + 1)
        } else {
            var count = 0
            for(i in 0 until N){
                if(i != level && egg[i][0] > 0){
                    count++
                    egg[i][0] -= egg[level][1]
                    egg[level][0] -= egg[i][1]
                    solve(level + 1)
                    egg[i][0] += egg[level][1]
                    egg[level][0] += egg[i][1]
                }
            }
            if(count == 0){
                solve(level + 1)
            }
        }
    }
}