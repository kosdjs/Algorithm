# 백준 2877번: 4와 7

> 문제: https://www.acmicpc.net/problem/2877

### 문제 풀이

구현

bin = K + 1을 이진수의 형태로 변환한 문자열

K에 1을 더하고 이진수의 형태로 문자열로 변환해 bin에 저장함

bin의 맨 앞자리를 제외한 나머지 문자마다 '0'이면 4, '1'이면 7로 출력하면 정답

### 풀이 설명

4와 7로 이루어진 수라고 했으므로 이진수가 0과 1로 이루어지는 수라는 것을 이용해 변환을 한다는 생각이 들었다.

여기서 주의해야 하는 것은 4, 7, 44, 47의 순서로 되기 때문에 4를 0, 7을 1로 그대로 치환하고 이진수로 나타내면 0, 1, 00, 01이 되기 때문에 제대로 된 숫자로 나오지 않는다는 점이다. 그러므로 이진수로 나타낸 수의 맨 앞에 1을 붙여주면 순서대로 10, 11, 100, 101이 되고 이를 다시 십진수로 나타내면 2, 3, 4, 5의 순서가 되게 된다.

그러므로 원래 순서인 K에 1을 더한 수를 이진수로 변환한 후에 두번째 숫자부터 0이면 4, 1이면 7을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val K = nextInt()
    val bin = (K + 1).toString(2)
    val sb = StringBuilder()
    for(i in 1 until bin.length){
        sb.append(if(bin[i] == '0') 4 else 7)
    }
    print(sb)
}
```