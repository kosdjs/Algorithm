# 백준 1599번: 민식어

> 문제: https://www.acmicpc.net/problem/1599

### 문제 풀이

문자열

Minsik(s) = 민식어 문자열 s를 순서대로 정렬하기 위해 만든 클래스

민식어가 k, ng만 특별히 영어 알파벳 순서가 다르기 때문에 이 문자들만 영어 알파벳에 해당하는 순서에 들어가도록 바꾸면 됨

k는 c로 바꾸면 민식어의 순서와 영어 알파벳의 순서가 같게 됨

ng가 문제인데 영어 알파벳은 n과 o가 이어져 있으므로 사이에 문자가 없어서 바꿀게 없음

민식어가 o부터 y까지의 알파벳만 쓰므로 이 알파벳들만 다음 알파벳을 쓰도록 바꾸고 ng를 o로 바꾸면 영어 알파벳 순서와 민식어 알파벳 순서를 맞출 수 있음

이렇게 하도록 민식어를 다루는 클래스 Minsik(s)를 만들고 클래스 내에 StringBuilder sb에 영어 알파벳으로 변형하는 문자열을 들고 있도록 함

그리고 인터페이스 Comparable을 상속해 비교할때 영어 알파벳 순서를 가지는 sb끼리의 비교를 하도록 만듬

N개의 단어를 Minsik 클래스로 만들고 이를 배열에 담아 배열을 정렬하고 출력하면 정답

### 풀이 설명

민식어 단어가 주어졌을 때, 그것을 민식어의 순서대로 정렬하는 문제이다.

영어는 a b c d e f g h i j k l m n o p q r s t u v w x y z의 순서이지만, 민식어는 a b k d e g h i l m n ng o p r s t u w y의 순서이다.

민식어는 영어의 순서를 따르기는 하지만, 약간 변형시켜서 따른다. 그리고 ng는 n과 o사이에 오는 하나의 알파벳이다. ng는 무조건 이 알파벳으로 생각한다.

민식어가 영어의 변형이고 몇 개의 특별한 알파벳 외에는 영어의 순서를 그대로 따라가기 때문에 문자열을 조금 변형해서 정렬할 때 사용하면 된다.

민식어의 알파벳을 살펴보면 원래 c대신 k를 쓰고 n과 o사이에 특별한 알파벳 ng 외에는 전부 영어의 알파벳과 순서가 같다. 따라서 k는 c로 바꾸면 되는데 ng는 n과 o사이의 알파벳이기 때문에 딱히 바꿀 수가 없다.

하지만 o부터 y까지만 쓰기때문에 민식어의 o부터 y까지의 알파벳을 영어의 다음 알파벳으로 사용하고 ng를 o로 바꾸면 민식어의 순서대로 영어의 알파벳 순서로 바꿀 수 있다.

따라서 이렇게 바꾼 영어를 순서를 정렬할 때 쓸 수 있도록 민식어를 저장하는 클래스 Minsik를  민식어 문자열 s를 받아서 생성하도록 만든다.

정렬 기준을 바꾸기 위해 이 클래스는 생성될때 민식어 문자열 s를 토대로 StringBuilder sb에 영어의 알파벳 순서로 바꾼 문자열을 저장하도록 한다.

그리고 Comparable 인터페이스를 상속받고 비교할 때 영어 알파벳으로 변경한 문자열을 들고있는 sb끼리의 비교 결과를 반환하게 만들면 민식어 단어를 순서대로 정렬하도록 만들 수 있다.

이제 이렇게 만든 Minsik 클래스를 가지는 배열을 사용해 N개의 단어를 정렬해서 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val N = readLine().toInt()
    class Minsik(val s: String): Comparable<Minsik>{
        override fun toString(): String {
            return s
        }
        val sb = StringBuilder()
        init {
            var idx = 0
            while(idx < s.length){
                if(s[idx] == 'k'){
                    sb.append('c')
                } else if(idx + 1 < s.length && s[idx] == 'n' && s[idx + 1] == 'g'){
                    sb.append('o')
                    idx++
                } else if(s[idx] >= 'o'){
                    sb.append(s[idx] + 1)
                } else {
                    sb.append(s[idx])
                }
                idx++
            }
        }
        override fun compareTo(other: Minsik): Int {
            return this.sb.toString().compareTo(other.sb.toString())
        }
    }
    val arr = Array(N){Minsik(readLine())}
    arr.sort()
    val sb = StringBuilder()
    for(i in 0 until N){
        sb.appendLine(arr[i])
    }
    print(sb)
}
```