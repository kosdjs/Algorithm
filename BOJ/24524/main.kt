package BOJ_24524

fun main() = System.`in`.bufferedReader().run {
    val S = readLine()
    val T = readLine()
    val Tpos = IntArray(26){-1}
    for(j in 0 until T.length){
        Tpos[T[j] - 'a'] = j
    }
    val count = IntArray(T.length)
    for(i in 0 until S.length){
        val idx = Tpos[S[i] - 'a']
        if(idx != -1){
            if(idx == 0){
                count[idx]++
            } else if(count[idx - 1] > count[idx]){
                count[idx]++
            }
        }
    }
    println(count.last())
}