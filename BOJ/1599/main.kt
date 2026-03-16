package BOJ_1599

fun main() = System.`in`.bufferedReader().run {
    val N = readLine().toInt()
    class Minsik(val s: String): Comparable<Minsik>{
        override fun toString(): String {
            return s
        }
        val sb = StringBuilder()
        init {
            var idx = 0
            while(idx < s.length){
                if(s[idx] == 'k'){
                    sb.append('c')
                } else if(idx + 1 < s.length && s[idx] == 'n' && s[idx + 1] == 'g'){
                    sb.append('o')
                    idx++
                } else if(s[idx] >= 'o'){
                    sb.append(s[idx] + 1)
                } else {
                    sb.append(s[idx])
                }
                idx++
            }
        }
        override fun compareTo(other: Minsik): Int {
            return this.sb.toString().compareTo(other.sb.toString())
        }
    }
    val arr = Array(N){Minsik(readLine())}
    arr.sort()
    val sb = StringBuilder()
    for(i in 0 until N){
        sb.appendLine(arr[i])
    }
    print(sb)
}