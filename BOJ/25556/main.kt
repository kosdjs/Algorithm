package BOJ_25556

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    var s1 = 0
    var s2 = 0
    var s3 = 0
    var s4 = 0
    repeat(N){
        val i = st.nextToken().toInt()
        if(i > s1){
            s1 = i
        } else if(i > s2){
            s2 = i
        } else if(i > s3){
            s3 = i
        } else if(i > s4){
            s4 = i
        } else {
            println("NO")
            return
        }
    }
    println("YES")
}