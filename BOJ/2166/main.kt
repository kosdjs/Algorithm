package BOJ_2166

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    var area = 0.0
    var curX = st.nextToken().toDouble()
    var curY = st.nextToken().toDouble()
    var prevX = 0.0
    var prevY = 0.0
    val firstX = curX
    val firstY = curY
    for(i in 1 until N){
        prevX = curX
        prevY = curY
        st = StringTokenizer(br.readLine())
        curX = st.nextToken().toDouble()
        curY = st.nextToken().toDouble()
        area += (prevX * curY) - (prevY * curX)
    }
    area += (curX * firstY) - (curY * firstX)
    println(String.format("%.1f", kotlin.math.abs(area) / 2))
}