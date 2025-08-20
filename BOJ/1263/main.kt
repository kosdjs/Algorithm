package BOJ_1263

import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val arr = ArrayList<IntArray>()
    repeat(N){
        val st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()
        val s = st.nextToken().toInt()
        arr.add(intArrayOf(t, s))
    }
    arr.sortWith{o1, o2 -> o2[1] - o1[1]}
    var answer = arr[0][1]
    for(work in arr){
        if(answer > work[1]){
            answer = work[1]
        }
        answer -= work[0]
    }
    if(answer < 0){
        println(-1)
    } else {
        println(answer)
    }
}