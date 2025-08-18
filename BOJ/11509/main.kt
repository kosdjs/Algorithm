package BOJ_11509

import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val st = StringTokenizer(readLine())
    val arrows = IntArray(1000001){0}
    var count = 0
    repeat(N){
        val balloon = st.nextToken().toInt()
        if(arrows[balloon] > 0){
            arrows[balloon]--
            arrows[balloon - 1]++
        } else {
            arrows[balloon - 1]++
            count++
        }
    }
    println(count)
}