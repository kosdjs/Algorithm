> 문제: https://www.acmicpc.net/problem/12904

### 문제 풀이

문자열 + 구현 문제

문자열 T를 확인해 마지막 문자가 A라면 문자를 지우고, B라면 마지막 문자를 지운 후 뒤집으면 됨

이를 S의 길이와 같아질 때까지 반복 후, S와 같다면 S로 T를 만들기 가능한 것이고 아니라면 불가능한 것

### 풀이 설명

문자열을 바꾸는 두 가지 연산에는 문자열의 마지막에 문자를 추가한다는 공통점이 있음

따라서 이 연산들을 통해 바꾼 문자열의 마지막 문자를 확인하면 어떤 연산을 한 것인지 확인이 가능하고 연산이 중첩될수록 문자열의 길이가 하나씩 늘어남

그러므로 T를 어떤 연산을 통해 바뀌었는지 확인 후, 연산을 되돌리는 작업을 반복하면 연산을 통해 T를 만들 수 있는 문자열들이 나오게 되고 반복할때마다 문자열의 길이가 하나씩 줄어듦으로 이 중에서 S와 길이가 같은 문자열은 하나만 나오기 때문에 이 문자열이 S인지 확인하면 된다.

### 소스 코드
```kotlin
import java.io.*

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val S = br.readLine()
    var T = br.readLine()
    while(T.length > S.length){
        if(T.last() == 'A'){
            T = T.dropLast(1)
        } else {
            T = T.dropLast(1)
            T = T.reversed()
        }
    }
    if(S.equals(T)){
        println(1)
    } else {
        println(0)
    }
}
```