package BOJ_2671

fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    print(if (s.matches(Regex("(100+1+|01)+"))) "SUBMARINE" else "NOISE")
}

//fun main() = System.`in`.bufferedReader().run {
//    val s = readLine()
//    var isSubmarine = true
//    var idx = 0
//    while(idx < s.length){
//        if(s[idx] == '0'){//01
//            if(idx + 1 < s.length && s[idx + 1] == '1') idx += 2
//            else {
//                isSubmarine = false
//                break
//            }
//        }
//        else if (s[idx] == '1'){//100~1~
//            if(idx + 2 < s.length && s[idx + 1] == '0' && s[idx + 2] == '0'){
//                idx += 2
//                while(idx < s.length && s[idx] == '0'){
//                    idx++
//                }
//                if(idx < s.length && s[idx] == '1'){
//                    while (idx < s.length && s[idx] == '1'){
//                        idx++
//                    }
//                    if(idx + 1 < s.length && s[idx + 1] == '0' && s[idx - 2] == '1') idx--
//                } else {
//                    isSubmarine = false
//                    break
//                }
//            } else {
//                isSubmarine = false
//                break
//            }
//        }
//    }
//    print(if (isSubmarine) "SUBMARINE" else "NOISE")
//}