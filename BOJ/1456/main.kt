package BOJ_1456

import java.util.StringTokenizer
import kotlin.math.sqrt

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val A = st.nextToken().toLong()
    val B = st.nextToken().toLong()
    val rootB = sqrt(B.toDouble()).toInt()
    val isPrime = BooleanArray(rootB + 1){true}
    var count = 0L
    for(i in 2..rootB){
        if(!isPrime[i]) continue
        var num = i.toLong() * i.toLong()
        if(num <= rootB){
            for(j in i.toLong() * i.toLong()..rootB step i.toLong()){
                isPrime[j.toInt()] = false
            }
        }
        while(num <= B){
            if(num >= A){
                count++
            }
            if(Long.MAX_VALUE / i < num) break
            num *= i
        }
    }
    println(count)
}