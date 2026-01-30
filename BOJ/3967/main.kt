package BOJ_3967

fun main() = System.`in`.bufferedReader().run {
    val arr = IntArray(12)
    val picked = BooleanArray(13)
    var count = 0
    var find = false
    fun convertToNum(c: Char): Int = if(c == 'x') 0 else c - 'A' + 1
    readLine().run {
        arr[0] = convertToNum(this[4])
    }
    readLine().run {
        arr[1] = convertToNum(this[1])
        arr[2] = convertToNum(this[3])
        arr[3] = convertToNum(this[5])
        arr[4] = convertToNum(this[7])
    }
    readLine().run {
        arr[5] = convertToNum(this[2])
        arr[6] = convertToNum(this[6])
    }
    readLine().run {
        arr[7] = convertToNum(this[1])
        arr[8] = convertToNum(this[3])
        arr[9] = convertToNum(this[5])
        arr[10] = convertToNum(this[7])
    }
    readLine().run {
        arr[11] = convertToNum(this[4])
    }
    for(num in arr){
        if(num != 0) count++
        picked[num] = true
    }
    fun checkLine(indices: IntArray): Boolean{
        var sum = 0
        for(idx in indices){
            if(arr[idx] == 0) return true
            else sum += arr[idx]
        }
        return if(sum == 26) true else false
    }
    fun checkLines(): Boolean = checkLine(intArrayOf(0, 2, 5, 7))
            && checkLine(intArrayOf(0, 3, 6, 10))
            && checkLine(intArrayOf(1, 2, 3, 4))
            && checkLine(intArrayOf(1, 5, 8, 11))
            && checkLine(intArrayOf(4, 6, 9, 11))
            && checkLine(intArrayOf(7, 8, 9, 10))
    fun solve(idx: Int, count: Int){
        if(find) return
        if(!checkLines()) return
        if(count == 12){
            find = true
            val sb = StringBuilder()
            fun convertToChar(num: Int): Char = 'A' + num - 1
            sb.append("....${convertToChar(arr[0])}....").append("\n")
            sb.append(".${convertToChar(arr[1])}.${convertToChar(arr[2])}.${convertToChar(arr[3])}.${convertToChar(arr[4])}.").append("\n")
            sb.append("..${convertToChar(arr[5])}...${convertToChar(arr[6])}..").append("\n")
            sb.append(".${convertToChar(arr[7])}.${convertToChar(arr[8])}.${convertToChar(arr[9])}.${convertToChar(arr[10])}.").append("\n")
            sb.append("....${convertToChar(arr[11])}....").append("\n")
            print(sb)
            return
        }
        for(i in idx until 12){
            if(arr[i] != 0) continue
            for(num in 1..12){
                if(picked[num]) continue
                arr[i] = num
                picked[num] = true
                solve(i + 1, count + 1)
                arr[i] = 0
                picked[num] = false
            }
            break
        }
    }
    for(i in arr.indices){
        if(arr[i] == 0){
            solve(i, count)
            break
        }
    }
}