package BOJ_2469

fun main(){
    val k = readln().toInt()
    val n = readln().toInt()
    val before = CharArray(k){'A' + it}
    val after = readln().toCharArray()
    val ladder = Array(n){readln().toCharArray()}
    var unknownRow = -1
    fun CharArray.swap(i: Int, j: Int){
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
    for(i in 0 until n){
        for(j in 0 until k - 1){
            val c = ladder[i][j]
            if(c == '-'){
                before.swap(j, j + 1)
            }
            if(c == '?'){
                unknownRow = i
                break
            }
        }
        if(unknownRow != -1) break
    }
    for(i in n - 1 downTo unknownRow + 1){
        for(j in 0 until k - 1){
            if(ladder[i][j] == '-') after.swap(j, j + 1)
        }
    }
    val sb = StringBuilder()
    for(i in 0 until k - 1){
        if(before[i] == after[i]){
            sb.append('*')
        } else if(before[i] == after[i + 1] && before[i + 1] == after[i]){
            sb.append('-')
            before.swap(i, i + 1)
        } else {
            sb.clear()
            repeat(k - 1){
                sb.append('x')
            }
            break
        }
    }
    print(sb)
}