package BOJ_20207

import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val cal = IntArray(366){0}
    repeat(N){
        val st = StringTokenizer(readLine())
        for(i in st.nextToken().toInt()..st.nextToken().toInt()){
            cal[i]++
        }
    }
    var start = 1
    var end = 0
    var max = 0
    var answer = 0
    for(i in 1..365){
        if(cal[i] == 0){
            end = i - 1
            answer += max * (end - start + 1)
            max = 0
            start = i + 1
        } else {
            max = maxOf(max, cal[i])
        }
    }
    if(max != 0){
        answer += max * (365 - start + 1)
    }
    println(answer)
}