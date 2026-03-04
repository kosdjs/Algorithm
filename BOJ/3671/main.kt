package BOJ_3671

import kotlin.math.sqrt

fun main() = System.`in`.bufferedReader().run {
    val c = readLine().toInt()
    val isPrime = BooleanArray(10000000){true}
    isPrime[0] = false
    isPrime[1] = false
    for(i in 2..sqrt(isPrime.size.toDouble()).toInt()){
        if(isPrime[i]){
            var idx = i * 2
            while(idx < isPrime.size){
                isPrime[idx] = false
                idx += i
            }
        }
    }
    val sb = StringBuilder()
    repeat(c){
        val s = readLine()
        val numCount = IntArray(10)
        for(i in 0 until s.length){
            numCount[s[i].code - '0'.code]++
        }
        var answer = 0
        fun makeNum(cur: Int, length: Int){
            if(isPrime[cur]) answer++
            if(length < s.length){
                for(i in 0..9){
                    if(numCount[i] > 0){
                        numCount[i]--
                        makeNum(cur * 10 + i, 1)
                        numCount[i]++
                    }
                }
            }
        }
        for(i in 1..9){
            if(numCount[i] > 0){
                numCount[i]--
                makeNum(i, 1)
                numCount[i]++
            }
        }
        sb.append(answer).append("\n")
    }
    print(sb)
}