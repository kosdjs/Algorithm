package BOJ_1484

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val G = br.readLine().toInt()
    var before = 1L
    var after = 2L
    var find = false
    while(after <= 50001){
        val diff = after * after - before * before
        if(diff < G){
            after++
        } else if(diff > G){
            before++
        } else {
            find = true
            bw.append("$after")
            bw.newLine()
            after++
        }
    }
    if(!find){
        bw.append("-1")
    }
    bw.flush()
    bw.close()
}