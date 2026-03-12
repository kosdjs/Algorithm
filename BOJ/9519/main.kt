package BOJ_9519

fun main() = System.`in`.bufferedReader().run {
    val X = readLine().toInt()
    val s = readLine().toCharArray()
    var arr = s.copyOf()
    fun rewind(arr: CharArray): CharArray{
        val result = CharArray(arr.size)
        var i = 0
        var j = 0
        while(j < arr.size){
            result[i] = arr[j]
            j += 2
            i++
        }
        j = arr.size - if(arr.size % 2 == 0) 1 else 2
        while(j >= 0){
            result[i] = arr[j]
            j -= 2
            i++
        }
        return result
    }
    var count = 0
    var isCycle = false
    for(i in 0 until X){
        arr = rewind(arr)
        count++
        if(s.contentEquals(arr)){
            isCycle = true
            break
        }
    }
    if(isCycle){
        repeat(X % count){
            arr = rewind(arr)
        }
    }
    println(arr)
}