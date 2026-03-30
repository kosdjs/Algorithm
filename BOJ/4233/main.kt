package BOJ_4233

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val sb = StringBuilder()
    fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        if (n == 2L || n == 3L) return true
        if (n % 2 == 0L || n % 3 == 0L) return false
        var i = 5L
        while (i * i <= n) {
            if (n % i == 0L || n % (i + 2) == 0L) return false
            i += 6
        }
        return true
    }
    while(true){
        val p = nextInt()
        val a = nextInt()
        if(a == 0 && p == 0) break
        if(isPrime(p.toLong())) sb.append("no").append("\n")
        else{
            var res = 1L
            var base = a.toLong()
            var e = p.toLong()
            while(e > 0){
                if(e % 2 == 1L){
                    res = (res * base) % p
                }
                base = (base * base) % p
                e /= 2
            }
            sb.append(if(res.toInt() == a) "yes" else "no").append("\n")
        }
    }
    print(sb)
}