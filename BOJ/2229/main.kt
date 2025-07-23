package BOJ_2229

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val students = br.readLine().split(" ").map{it.toInt()}
    val dp = IntArray(N){ 0 }
    for(i in 0 until N){
        var max = students[i]
        var min = students[i]
        for(j in i downTo 0){
            if(min > students[j]) min = students[j]
            if(max < students[j]) max = students[j]
            val sum = (if(j != 0) dp[j - 1] else 0) + max - min
            if(dp[i] < sum) dp[i] = sum
        }
    }
    println(dp[N-1])
}