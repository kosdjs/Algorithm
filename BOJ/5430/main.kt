package BOJ_5430

import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()
    for (i in 1..T){
        val op = br.readLine()
        val n = br.readLine().toInt()
        var numberString = br.readLine()
        numberString = numberString.substring(1, numberString.length-1)
        val arr = ArrayList<Int>()
        if(numberString.isNotEmpty()){
            for(s in numberString.split(",")){
                arr.add(s.toInt())
            }
        }
        var cursor = 0 // 0이면 앞, 1이면 뒤
        var condition = true
        for(c in op){
            if(c == 'R'){
                cursor = if(cursor == 0) 1 else 0
            } else {
                if(arr.isNotEmpty()){
                    if(cursor == 0){
                        arr.removeFirst()
                    } else {
                        arr.removeLast()
                    }
                } else {
                    println("error")
                    condition = false
                    break
                }
            }
        }
        if (condition){
            if(arr.isNotEmpty()){
                if (cursor == 1){
                    arr.reverse()
                }
                print("[${arr[0]}")
                for(i in 1 until arr.size){
                    print(",${arr[i]}")
                }
                println("]")
            } else {
                println("[]")
            }
        }
    }
}