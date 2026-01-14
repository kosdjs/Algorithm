package BOJ_16719

fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    val isPicked = BooleanArray(s.length)
    val bw = System.out.bufferedWriter()
    fun pick(left: Int, right: Int){
        if(left > right) return
        var min = 'Z' + 1
        var minIdx = 0
        for(i in left..right){
            if(min > s[i]){
                min = s[i]
                minIdx = i
            }
        }
        isPicked[minIdx] = true
        for(i in 0 until s.length){
            if(isPicked[i]) bw.append(s[i])
        }
        bw.newLine()
        pick(minIdx + 1, right)
        pick(left, minIdx - 1)
    }
    pick(0, s.length - 1)
    bw.flush()
    bw.close()
}