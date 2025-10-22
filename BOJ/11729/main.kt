package BOJ_11729

var answer = 0
val sb = StringBuilder()

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    hanoi(N, 1, 2, 3)
    println(answer)
    print(sb)
}

fun hanoi(level: Int, start: Int, empty: Int, end: Int){
    if(level == 0){
        return
    }
    answer++
    //목적지에 따라 현재 옮겨야 되는것보다 작은거를 재귀로 불러서 옆 칸으로 옮김
    hanoi(level - 1, start, end, empty)
    //현재 옮겨야 하는 가장 큰거를 옮겨야 하는 칸으로 옮김
    sb.append("$start $end\n")
    //처음에 옮겨놨던 작은거를 현재 칸으로 옮기는 재귀 호출
    hanoi(level - 1, empty, start, end)
}