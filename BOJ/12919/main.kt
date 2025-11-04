package BOJ_12919

var S = ""
var T = ""
var answer = false

fun main(){
    val br = System.`in`.bufferedReader()
    S = br.readLine()
    T = br.readLine()
    solve(T)
    println("${if(answer) 1 else 0}")
}

fun solve(s: String){
    if(s.length == S.length){
        if(s == S){
            answer = true
        }
        return
    }
    if(s.last() == 'A'){
        solve(s.dropLast(1))
    }

    if(s.first() == 'B'){
        solve(s.drop(1).reversed())
    }
}