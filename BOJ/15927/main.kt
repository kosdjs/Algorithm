package BOJ_15927

fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    if(s.length == 1){
        println(-1)
        return@run
    }
    var isPalindrome = true
    var isSameAlphabet = true
    var left = 0
    var right = s.length - 1
    while(left <= right){
        if(s[left] != s[right]){
            isPalindrome = false
            break
        }
        if(isSameAlphabet){
            if(s[left] != s[left + 1] || s[right] != s[right - 1]) isSameAlphabet = false
        }
        left++
        right--
    }
    println(if(!isPalindrome) s.length else if(!isSameAlphabet) s.length - 1 else -1)
}