package BOJ_28706

fun main() = System.`in`.bufferedReader().run {
    val T = readLine().toInt()
    val sb = StringBuilder()
    for(t in 0 until T){
        val N = readLine().toInt()
        var curBits = 1 shl 1
        for(n in 0 until N){
            val s = readLine()
            val op1 = s[0]
            val v1 = s[2] - '0'
            val op2 = s[4]
            val v2 = s[6] -'0'
            var nextBits = 0
            for(i in 0 until 7){
                if(curBits and (1 shl i) != 0){
                    if(op1 == '+') nextBits = nextBits or (1 shl (i + v1) % 7)
                    else nextBits = nextBits or (1 shl (i * v1) % 7)
                    if(op2 == '+') nextBits = nextBits or (1 shl (i + v2) % 7)
                    else nextBits = nextBits or (1 shl (i * v2) % 7)
                }
            }
            curBits = nextBits
        }
        sb.append(if (curBits and 1 == 1) "LUCKY" else "UNLUCKY").append("\n")
    }
    print(sb)
}