package BOJ_16498

import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val a = IntArray(A)
    val b = IntArray(B)
    val c = IntArray(C)
    st = StringTokenizer(br.readLine())
    for(i in 0 until A){
        a[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 0 until B){
        b[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 0 until C){
        c[i] = st.nextToken().toInt()
    }
    a.sort()
    b.sort()
    c.sort()
    var answer = Int.MAX_VALUE
    for(i in 0 until A){
        var max = a[i]
        var min = a[i]
        var left = 0
        var right = B - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(b[mid] < min){
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        if(left == B){
            min = b[right]
        } else if(right == -1){
            max = b[left]
        } else {
            if(abs(b[left] - min) > abs(max - b[right])){
                min = b[right]
            } else {
                max = b[left]
            }
        }
        left = 0
        right = C - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(c[mid] < min){
                left = mid + 1
            } else if(c[mid] > max){
                right = mid - 1
            } else {
                break
            }
        }
        if(left <= right){
            answer = minOf(answer, abs(max - min))
        } else {
            if(left == C){
                min = c[right]
            } else if(right == -1){
                max = c[left]
            } else {
                if(abs(c[left] - min) > abs(max - c[right])){
                    min = c[right]
                } else {
                    max = c[left]
                }
            }
            answer = minOf(answer, abs(max - min))
        }
    }
    println(answer)
}