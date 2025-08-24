package BOJ_19940

fun main(){
    val T = readLine()!!.toInt()
    repeat(T){
        var N = readLine()!!.toInt()
        val buttons = IntArray(5)
        if(N % 10 == 5){
            if(N % 60 > 40){
                buttons[4] += 10 - (N % 10)
                N += 10 - (N % 10)
            } else {
                buttons[3] += N % 10
                N -= N % 10
            }
        } else if(N % 10 > 5){
            buttons[4] += 10 - (N % 10)
            N += 10 - (N % 10)
        } else {
            buttons[3] += N % 10
            N -= N % 10
        }
        if(N % 60 > 30){
            buttons[2] += (60 - (N % 60)) / 10
            N += 60 - (N % 60)
        } else {
            buttons[1] += (N % 60) / 10
            N -= N % 60
        }
        buttons[0] = N / 60
        for(button in buttons){
            print("$button ")
        }
        println()
    }
}