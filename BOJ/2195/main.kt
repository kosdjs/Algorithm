package BOJ_2195

fun main(){
    val br = System.`in`.bufferedReader()
    val S = br.readLine()
    val P = br.readLine()
    var p = 0
    var answer = 0
    while(p < P.length){
        var max = p
        for(i in 0 until S.length){
            if(S[i] == P[p]){
                var s = i
                var t = p
                while(s < S.length && t < P.length && S[s] == P[t]){
                    s++
                    t++
                }
                max = maxOf(max, t)
            }
        }
        answer++
        p = max
    }
    println(answer)
}