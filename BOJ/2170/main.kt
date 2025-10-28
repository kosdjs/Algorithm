package BOJ_2170

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val lines = Array(N){ IntArray(2) }
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        for(j in 0..1){
            lines[i][j] = st.nextToken().toInt()
        }
    }
    lines.sortWith{o1, o2 -> if(o1[0] - o2[0] != 0) o1[0] - o2[0] else o1[1] - o2[1]}
    var start = lines[0][0]
    var end = lines[0][1]
    var answer = 0
    for(i in 1 until N){
        val x = lines[i][0]
        val y = lines[i][1]
        if(x >= start && x <= end){
            end = maxOf(end, y)
        } else {
            answer += end - start
            start = x
            end = y
        }
    }
    answer += end - start
    println(answer)
}