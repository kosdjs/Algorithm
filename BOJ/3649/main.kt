package BOJ_3649

fun main(){
    val br = System.`in`.bufferedReader()
    while(true){
        val t = br.readLine() ?: break
        val x = t.toInt() * 10000000
        val n = br.readLine().toInt()
        val arr = IntArray(n)
        for(i in arr.indices){
            arr[i] = br.readLine().toInt()
        }
        arr.sort()
        var find = false
        var left = 0
        var right = n - 1
        while(left < right){
            val sum = arr[left] + arr[right]
            if(sum < x){
                left++
            } else if(sum > x){
                right--
            } else {
                println("yes ${arr[left]} ${arr[right]}")
                find = true
                break
            }
        }
        if(!find){
            println("danger")
        }
    }
}