package BOJ_11758

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val x = IntArray(3)
    val y = IntArray(3)
    for(i in 0 until 3){
        val st = StringTokenizer(br.readLine())
        x[i] = st.nextToken().toInt()
        y[i] = st.nextToken().toInt()
    }
    val ccw = (x[1] - x[0]) * (y[2] - y[0]) - (y[1] - y[0]) * (x[2] - x[0])
    println(if(ccw > 0) 1 else if(ccw < 0) -1 else 0)
}