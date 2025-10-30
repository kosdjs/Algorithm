package BOJ_6198

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val stack = IntArray(N)
    var top = -1
    var answer = 0L
    for(i in 0 until N){
        val height = br.readLine().toInt()
        while(top >= 0 && stack[top] <= height){
            top--
        }
        answer += top + 1
        top++
        stack[top] = height
    }
    println(answer)
}