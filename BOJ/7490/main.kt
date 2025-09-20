package BOJ_7490

val sb = StringBuilder()

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    repeat(t){
        sb.append("1")
        val N = br.readLine().toInt()
        calc(N, 2, 0, 1, '+', '+')
        sb.setLength(0)
        println()
    }
}

fun calc(N: Int, step: Int, left: Int, right: Int, lastOp: Char, calcOp: Char){
    if(step > N){
        var sum = 0
        if (lastOp == '+'){
            sum = left + right
        } else if(lastOp == '-'){
            sum = left - right
        } else {
            if(calcOp == '+'){
                sum = left + right
            } else {
                sum = left - right
            }
        }
        if(sum == 0){
            println(sb)
        }
    } else {
        sb.append(" $step")
        calc(N, step + 1, left, right * 10 + step, ' ', calcOp)
        sb.setLength(sb.length - 2)
        sb.append("+$step")
        calc(N, step + 1, if(calcOp == '+') left + right else left - right, step, '+', '+')
        sb.setLength(sb.length - 2)
        sb.append("-$step")
        calc(N, step + 1, if(calcOp == '+') left + right else left - right, step, '-', '-')
        sb.setLength(sb.length - 2)
    }
}