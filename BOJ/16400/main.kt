package BOJ_16400

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)
    val isPrime = BooleanArray(n + 1){true}
    for(i in 2..n){
        if(isPrime[i]){
            for(j in i * i..n step i){
                isPrime[j] = false
            }
            dp[i]++
            for(j in i + 1..n){
                dp[j] = (dp[j] + dp[j - i]) % 123456789
            }
        }
    }
    println(dp[n])
}