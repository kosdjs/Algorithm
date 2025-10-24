package BOJ_1759

import java.util.StringTokenizer

var L = 0
lateinit var arr: CharArray
lateinit var idx: IntArray
val bw = System.out.bufferedWriter()

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    L = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    arr = CharArray(C)
    idx = IntArray(L)
    st = StringTokenizer(br.readLine())
    for(i in 0 until C){
        arr[i] = st.nextToken()[0]
    }
    arr.sort()
    for(i in 0..C - L){
        if(isVowel(arr[i])){
            solve(1, i, 1, 0)
        } else {
            solve(1, i, 0, 1)
        }
    }
    bw.flush()
    bw.close()
}

fun solve(level: Int, cur: Int, vowel: Int, consonant: Int){
    idx[level - 1] = cur
    if(level == L){
        if(vowel > 0 && consonant > 1){
            for(i in 0 until L){
                bw.write(arr[idx[i]].toString())
            }
            bw.newLine()
        }
    } else {
        for(i in cur + 1 until arr.size){
            if (isVowel(arr[i])){
                solve(level + 1, i, vowel + 1, consonant)
            } else {
                solve(level + 1, i, vowel, consonant + 1)
            }
        }
    }
}

fun isVowel(c: Char): Boolean{
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
}