package BOJ_10472

fun main() = System.`in`.bufferedReader().run {
    val P = readLine().toInt()
    val sb = StringBuilder()
    val clicks = intArrayOf(
        0b110100000,
        0b111010000,
        0b011001000,
        0b100110100,
        0b010111010,
        0b001011001,
        0b000100110,
        0b000010111,
        0b000001011
    )
    for(t in 0 until P){
        var res = 0
        for(i in 0 until 3){
            val s = readLine()
            for(j in 0 until 3){
                if(s[j] == '*') res = res or 1
                res = res shl 1
            }
        }
        res = res shr 1
        for(i in 0 until 512){
            var board = 0
            var bitCount = 0
            for(j in 0 until 9){
                if((1 shl j) and i != 0){
                    bitCount++
                    board = board xor clicks[j]
                }
            }
            if(board == res){
                sb.append(bitCount).append("\n")
                break
            }
        }
    }
    print(sb)
}