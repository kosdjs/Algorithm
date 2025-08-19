package BOJ_19539

import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    var sum = 0
    val st = StringTokenizer(readLine())
    var twoCount = 0
    repeat(N){
        val height = st.nextToken().toInt()
        sum += height
        if(height > 1){
            twoCount += height / 2
        }
    }
    if(sum % 3 == 0){
        var count = sum / 3
        if(count > twoCount){
            println("NO")
        } else {
            println("YES")
        }
    } else {
        println("NO")
    }
}