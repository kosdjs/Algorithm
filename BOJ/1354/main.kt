package BOJ_1354

import java.util.StringTokenizer

val arr = LongArray(3500000)
var P = 0
var Q = 0
var X = 0
var Y = 0

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toLong()
    P = st.nextToken().toInt()
    Q = st.nextToken().toInt()
    X = st.nextToken().toInt()
    Y = st.nextToken().toInt()
    println(A(N))
}

fun A(N: Long): Long{
    if(N <= 0){
        return 1
    } else if(N >= 3500000){
        return A(N / P - X) + A(N / Q - Y)
    } else if(arr[N.toInt()] != 0L) {
        return arr[N.toInt()]
    } else {
        arr[N.toInt()] = A(N / P - X) + A(N / Q - Y)
        return arr[N.toInt()]
    }
}