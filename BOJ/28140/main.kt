package BOJ_28140

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val Q = st.nextToken().toInt()
    val s = br.readLine()
    val red = IntArray(N + 4){N}
    val blue = IntArray(N + 4){N}
    for(i in N - 1 downTo 0){
        val c = s[i]
        red[i] = if(c == 'R') i else red[i + 1]
        blue[i] = if(c == 'B') i else blue[i + 1]
    }
    for(i in 0 until Q){
        st = StringTokenizer(br.readLine())
        val l = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        val a = red[l]
        val b = red[a + 1]
        val c = blue[b + 1]
        val d = blue[c + 1]
        if(a > r || b > r || c > r || d > r){
            bw.write("-1\n")
        } else {
            bw.write("$a $b $c $d\n")
        }
    }
    bw.flush()
    bw.close()
}