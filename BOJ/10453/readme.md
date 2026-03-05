# 백준 10453번: 문자열 변환

> 문제: https://www.acmicpc.net/problem/10453

### 문제 풀이

문자열

APos, BPos = 문자열 A, B 내에서 a의 위치(인덱스)

answer = 연산해야 하는 횟수

A에서 B로 연산을 통해 변경하는 것은 A, B가 둘다 같은 수의 a, b로 이루어져 있고 연산이 두 인접한 문자를 바꾸는 것이므로 A에서의 문자 a, B에서의 문자 a의 위치를 맞추는 과정이 됨

연산 한번에 a의 위치를 1 바꿀 수 있으므로 두 문자열 A, B에서의 a의 위치(인덱스)를 APos, BPos에 저장해놓고 각각 a마다 위치 차이를 연산 횟수로 세면 됨

이에 따라 모든 a의 위치마다 차이를 answer에 더해서 출력하면 정답

### 풀이 설명

좋은 문자열 A, B가 주어질 때 인접한 두 문자를 서로 바꾸는 연산으로 A를 B로 바꾸려고 할 때 필요한 연산의 횟수를 구하는 문제이다. 이때 연산 중에도 문자열은 좋은 문자열의 조건을 만족해야 한다.

좋은 문자열의 조건을 요약하면 a와 b의 총 갯수가 같아야 하고, a와 b를 쌍으로 묶는다면 항상 a가 앞으로 와야한다는 점이다. 좀 더 익숙한 표현으로 바꾸려면 a와 b를 (, )와 같이 열린 괄호와 닫힌 괄호의 조건으로 바꾸어 보면 더 명확히 알 수 있다.

그리고 연산의 조건을 살펴본다면 인접한 두 문자를 서로 바꾸는 것이고 좋은 문자열은 항상 a와 b로 이루어져있으므로 연산을 통해 문자열을 변경하려면 항상 인접한 a와 b를 바꾸는 연산이 된다.

즉, 연산이 인접한 a와 b의 순서를 바꾸는 것이고 좋은 문자열의 정의에 의해 문자열의 길이가 같은 A, B는 동일한 갯수의 a, b를 가지고 있으므로 A에 존재하는 a의 위치를 B에 존재하는 a의 위치에 맞도록 위치를 바꾸는 연산 횟수를 구하는 것이다.

여기서 연산을 했을 때 좋은 문자열의 조건이 깨지지 않는지 확인해야 하는데 A가 이미 좋은 문자열이고 연산이 인접한 a와 b사이에서만 일어나기 때문에 a를 뒤로 미루는 연산을 통해 b를 앞으로 당기더라도 이미 그 b와 쌍을 이루는 a가 앞에 있어서 좋은 문자열을 이루고 있는 A이기 때문에 위치를 바꾸는 연산으로는 좋은 문자열의 조건을 깰 수 없습니다.

연산 한 번에 위치가 하나가 바뀌기 때문에 a의 위치 차이가 나는 만큼 연산을 해야 하므로 위치의 차이만큼 연산의 횟수가 나오게 된다. 따라서 모든 a의 위치 차이를 더해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val T = nval.toInt()
    fun nextString(): String{
        nextToken()
        return sval
    }
    val sb = StringBuilder()
    repeat(T){
        val A = nextString()
        val B = nextString()
        val APos = IntArray(A.length / 2)
        val BPos = IntArray(B.length / 2)
        var Aidx = 0
        var Bidx = 0
        for(i in 0 until A.length){
            if(A[i] == 'a'){
                APos[Aidx] = i
                Aidx++
            }
            if(B[i] == 'a'){
                BPos[Bidx] = i
                Bidx++
            }
        }
        var answer = 0
        for(i in 0 until APos.size){
            answer += abs(APos[i] - BPos[i])
        }
        sb.append(answer).append("\n")
    }
    print(sb)
}
```