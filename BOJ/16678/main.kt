package BOJ_16678

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val a = IntArray(N)
    for(i in 0 until N){
        a[i] = br.readLine().toInt()
    }
    a.sort()
    var target = 1
    var answer = 0L
    for(x in a){
        if(x >= target){
            answer += x - target
            target++
        }
    }
    println(answer)
}