package BOJ_12904

import java.io.*

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val S = br.readLine()
    var T = br.readLine()
    while(T.length > S.length){
        if(T.last() == 'A'){
            T = T.dropLast(1)
        } else {
            T = T.dropLast(1)
            T = T.reversed()
        }
    }
    if(S.equals(T)){
        println(1)
    } else {
        println(0)
    }
}