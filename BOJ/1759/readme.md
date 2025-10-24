# 백준 1759번: 암호 만들기

> 문제: https://www.acmicpc.net/problem/1759

### 문제 풀이

브루트포스, 정렬

arr = C개의 문자를 오름차순으로 정렬한 배열

idx = 현재 출력해야 하는 문자의 인덱스를 저장하는 배열

C개의 문자를 순열로 L개 뽑아서 모음과 자음의 개수를 확인해 암호의 조건을 만족할 때만 출력하면 정답

### 풀이 설명

암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다. 또한 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었다. 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.

새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.

암호가 증가하는 순서로 배열되었다고 했으므로 알파벳을 오름차순으로 정렬해놓고 순열로 뽑으면 항상 증가하는 순서로 뽑히므로 이렇게 뽑은 문자가 최소 한 개의 모음과 최소 두 개의 자음을 만족하는지만 확인하면 된다.

따라서 재귀 함수를 사용해 L개의 문자를 순열로 뽑으면 된다. 이 때 문자를 하나 뽑을 때마다 모음인지 확인해 모음의 개수와 자음의 개수를 나타내면 된다. 따라서 L개의 문자를 뽑았을 때 모음과 자음의 개수를 확인해 암호가 될 때만 출력하면 정답이 된다.

### 소스 코드
```kotlin
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
```