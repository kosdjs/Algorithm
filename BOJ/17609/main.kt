package BOJ_17609

fun main(){
    val br = System.`in`.bufferedReader()
    val T = br.readLine().toInt()
    val bw = System.out.bufferedWriter()
    repeat(T){
        val s = br.readLine()
        var left = 0
        var right = s.length - 1
        var answer = 0
        while(left < right){
            if(s[left] != s[right]){
                if(answer > 0){
                    answer++
                    break
                } else {
                    answer++
                    if(s[left] == s[right - 1] && s[left + 1] == s[right]){
                        var i = left + 1
                        var j = right
                        while(i < j){
                            if(s[i] == s[j]){
                                i++
                                j--
                            } else {
                                break
                            }
                        }
                        if(i >= j){
                            break
                        }
                        i = left
                        j = right - 1
                        while(i < j){
                            if(s[i] == s[j]){
                                i++
                                j--
                            } else {
                                break
                            }
                        }
                        if(i >= j){
                            break
                        }
                        answer = 2
                        break
                    } else if(s[left] == s[right - 1]){
                        right--
                    } else if(s[left + 1] == s[right]){
                        left++
                    } else {
                        answer = 2
                        break
                    }
                }
            }
            left++
            right--
        }
        bw.write("$answer")
        bw.newLine()
    }
    bw.flush()
    bw.close()
}