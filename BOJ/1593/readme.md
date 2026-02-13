# 백준 1593번: 문자 해독

> 문제: https://www.acmicpc.net/problem/1593

### 문제 풀이

슬라이딩 윈도우

left, right = 문자열 S에서 현재 확인하는 g개의 연속된 문자열이 시작, 끝나는 인덱스

answer = 단어 W일 수 있는 문자 g개의 연속된 문자열의 갯수

WCount = 단어 W를 이루고 있는 문자의 갯수

curCount = 현재 확인하는 g개의 문자의 갯수

convertToIndex(c) = 문자 c의 인덱스를 구하는 함수

단어 W에서 모든 문자 c에 대해 WCount[convertToIndex(c)]를 1 증가시켜 문자 c의 갯수를 반영해줌

그 이후에 문자열 s에서 g개의 문자가 연속되는 문자열을 확인해야 하므로 먼저 left부터 right 직전까지 g - 1개의 문자의 갯수를 curCount에 저장함

right 인덱스의 문자의 갯수를 적용해주면 g개 문자의 갯수가 curCount에 저장되므로 이를 WCount와 비교해 단어 W의 문자 갯수와 현재 문자열의 문자 갯수가 일치하면 answer를 1 증가시킴

그 이후에 다음 문자열을 확인하기 위해 left 인덱스의 문자 갯수를 curCount에서 1 빼준 이후 left와 right를 1씩 증가시킴

이 단계들을 right가 문자열의 길이 S가 될때까지 반복하면 문자열에서 확인해야 하는 모든 부분 문자열을 확인한 것이므로 answer에 구해야 하는 단어 W가 될 수 있는 경우의 수가 저장되므로 출력하면 정답

### 풀이 설명

문자열 s에서 단어 W를 찾아야 하는 문제이다. 단, 단어를 구성하는 글자를 순서에 상관 없이 적어놓았기 때문에 단어 W가 될 수 있는 모든 경우의 수를 찾아야 한다.

그러면 단어 W를 알아낼 수 있는 수단이 무엇인지 살펴보아야 한다. W를 구성하는 g개의 문자를 순서 상관 없이 연속으로 썼으면 단어 W를 쓴 것이라고 생각한다고 했으므로 문자열 S에서 연속으로 이어진 g개의 문자열을 모두 확인해 단어 W에서 쓴 g개의 문자를 모두 썼는지 확인해야 한다.

문자열이 알파벳 대소문자를 구분해서 주어진다고 했으므로 g개의 문자가 각각 어떤 알파벳이 몇개 쓰였는지 먼저 세어야 한다. 그 뒤에는 문자열 s에서 연속된 g개의 문자로 이루어진 모든 문자열을 확인해야 한다.

먼저 맨 앞의 g개의 문자를 센 다음에 다음 연속된 g개의 문자는 앞에서 확인한 g개의 문자열에서 맨 앞 문자를 제외하고 그 뒤에 이어져야 하는 문자 하나를 붙인 형식이기 때문에 슬라이딩 윈도우를 사용하면 s에서 확인해야 하는 모든 부분 문자열을 확인할 수 있다.

단어가 알파벳 대소문자로 구성되므로 총 52개의 문자가 사용된다. 따라서 문자의 갯수는 배열을 이용해 저장할 수 있다.

이에 따라 단어 W의 문자의 갯수를 세어 WCount에 저장하고 인덱스 left, right를 사용해 슬라이딩 윈도우 기법을 이용해 연속된 문자열의 문자 갯수를 curCount에 반영하면서 WCount와 비교해 문자 갯수가 같을때의 수를 세어 answer에 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val (g, S) = readLine().split(" ").map { it.toInt() }
    val W = readLine()
    val s = readLine()
    var left = 0
    var right = g - 1
    var answer = 0
    val WCount = IntArray(52)
    val curCount = IntArray(52)
    fun convertToIndex(c: Char): Int{
        if(c.isUpperCase()){
            return 26 + c.code - 'A'.code
        } else {
            return c.code - 'a'.code
        }
    }
    for(c in W){
        WCount[convertToIndex(c)]++
    }
    for(i in left until right){
        val c = s[i]
        curCount[convertToIndex(c)]++
    }
    while(right < S){
        val rightChar = s[right]
        curCount[convertToIndex(rightChar)]++
        if (WCount.contentEquals(curCount)){
            answer++
        }
        val leftChar = s[left]
        curCount[convertToIndex(leftChar)]--
        left++
        right++
    }
    println(answer)
}
```