package BOJ_32358

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val trash = ArrayList<Int>()
    var current = 0
    var answer = 0L
    repeat(N){
        val st = StringTokenizer(br.readLine())
        val op = st.nextToken().toInt()
        if(op == 1){
            trash.add(st.nextToken().toInt())
        } else {
            if(trash.isNotEmpty()){
                trash.sort()
                var left = 0
                var right = trash.size - 1
                while(left <= right){
                    val mid = (left + right) / 2
                    if(trash[mid] < current){
                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                }
                if(left >= trash.size){
                    answer += current - trash[0]
                    current = trash[0]
                    trash.clear()
                } else if(right < 0){
                    answer += trash.last() - current
                    current = trash.last()
                    trash.clear()
                } else {
                    while(right >= 0 && left < trash.size){
                        if(current - trash[right] > trash[left] - current){
                            answer += trash[left] - current
                            current = trash[left]
                            left++
                        } else {
                            answer += current - trash[right]
                            current = trash[right]
                            right--
                        }
                    }
                    if(left >= trash.size){
                        answer += current - trash[0]
                        current = trash[0]
                        trash.clear()
                    } else {
                        answer += trash.last() - current
                        current = trash.last()
                        trash.clear()
                    }
                }
            }
        }
    }
    println(answer)
}