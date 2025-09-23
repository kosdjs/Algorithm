package BOJ_12852

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val count = IntArray(N + 1){Int.MAX_VALUE}
    count[N] = 0
    val before = IntArray(N + 1)
    for(i in N downTo 2){
        if(count[i] == Int.MAX_VALUE){
            continue
        }
        if(i % 3 == 0){
            if(count[i / 3] > count[i] + 1){
                count[i / 3] = count[i] + 1
                before[i / 3] = i
                if(i / 3 == 1) {
                    break
                }
            }
        }
        if(i % 2 == 0){
            if(count[i / 2] > count[i] + 1){
                count[i / 2] = count[i] + 1
                before[i / 2] = i
                if(i / 2 == 1){
                    break
                }
            }
        }
        if(count[i - 1] > count[i] + 1){
            count[i - 1] = count[i] + 1
            before[i - 1] = i
        }
    }
    println(count[1])
    val list = mutableListOf(1)
    var idx = 1
    while(count[idx] > 0){
        list.add(before[idx])
        idx = before[idx]
    }
    list.reverse()
    for(num in list){
        print("$num ")
    }
}