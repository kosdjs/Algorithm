# 백준 16719번: ZOAC

> 문제: https://www.acmicpc.net/problem/16719

### 문제 풀이

문자열, 재귀

s = 원래 문자열

isPicked[i] = s의 i번 인덱스의 문자가 선택되었는지 여부

pick(left, right) = s의 인덱스 left와 right 사이의 가장 순서가 빠른 알파벳을 선택하고 선택된 문자를 출력하는 함수

가장 빠른 알파벳을 먼저 뽑으면 그 왼쪽에 있는 알파벳은 처음 뽑은 알파벳보다 사전순으로 느리기 때문에 오른쪽에 있는 알파벳을 뽑는 것보다 사전 순으로 느리게 되어 있음

그러므로 뽑은 알파벳의 오른쪽 부분에서 다시 가장 빠른 순서의 알파벳을 뽑아야 함

문자열 s를 입력으로 받고 그 문자열의 i번 인덱스를 뽑았는지 여부를 isPicked[i]에 저장하기 위해 정의함

가장 빠른 알파벳을 뽑기 위해 범위의 시작 인덱스 left, 끝 인덱스를 right로 입력받는 pick(left, right) 함수를 다음과 같이 정의함

left가 right보다 크다면 뽑을 알파벳이 없는 것이므로 함수 반환

범위 내의 가장 순서가 빠른 알파벳을 뽑기 위해 min에 'Z' + 1, minIdx에 0으로 초기화

left부터 right까지의 범위에서 가장 빠른 알파벳을 찾아 min, 인덱스를 minIdx에 저장

뽑았다는 것을 나타내기 위해 isPicked[minIdx]에 true 대입하고 현재까지 뽑은 문자열 출력

오른쪽 범위에서 다시 가장 빠른 알파벳을 뽑기 위해 pick(minIdx + 1, right)을 먼저 호출하고 끝나면 왼쪽 범위를 처리하기 위해 pick(left, minIdx - 1)을 호출함

이러면 문제 상황에 맞게 문자열을 처리할 수 있는 함수를 정의한 것이므로 pick(0, s.length - 1)을 호출해 순서대로 문자열을 출력하면 정답

### 풀이 설명

문자열이 주어지면 아직 보여주지 않은 문자 중 추가했을 때의 문자열이 사전 순으로 가장 앞에 오도록 하는 문자를 보여주는 것이라고 문제에 쓰여있는데 원래 문자열에서 원래 문자열의 위치 순서대로 문자를 하나씩 추가했을 때 사전순으로 가장 빠르게 되는 문자를 선택해서 추가를 해야한다.

예를 들어 예제 2번의 BAC가 A -> AC -> BAC 순서로 출력이 되는 것 처럼 B가 C보다 사전순으로는 앞서지만 원래 문자열의 순서대로 하면 B가 A의 앞에 오기 때문에 이때 문자열이 BA가 되므로 C를 추가할 때의 문자열 AC보다 사전 순으로 뒤로 가게 된다.

즉, 사전 순으로 가장 빠른 알파벳을 먼저 고르기 때문에 그 문자보다 왼쪽에 있는 문자를 고르면 항상 사전 순으로 느리기 때문에 처음 고른 문자보다 오른쪽에 있는 문자를 먼저 다 처리하고 나서 선택해야 한다는 점이다.

그러므로 이를 분할 정복식으로 생각해보면 문자열을 가장 빠른 문자보다 왼쪽에 있는 부분, 가장 빠른 문자, 가장 빠른 문자보다 오른쪽에 있는 부분의 세가지로 나눌 수 있다. 이렇게 나누는 부분을 재귀 함수를 이용해 처리할 수 있다.

따라서 문자열의 인덱스 시작 left, 끝 right 사이의 가장 빠른 순서의 알파벳을 뽑고 오른쪽, 왼쪽 부분으로 나누어 다시 가장 빠른 순서의 알파벳을 뽑는 재귀 함수 pick(left, right)를 정의하고 이를 이용해 순서대로 알파벳을 뽑아 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    val isPicked = BooleanArray(s.length)
    val bw = System.out.bufferedWriter()
    fun pick(left: Int, right: Int){
        if(left > right) return
        var min = 'Z' + 1
        var minIdx = 0
        for(i in left..right){
            if(min > s[i]){
                min = s[i]
                minIdx = i
            }
        }
        isPicked[minIdx] = true
        for(i in 0 until s.length){
            if(isPicked[i]) bw.append(s[i])
        }
        bw.newLine()
        pick(minIdx + 1, right)
        pick(left, minIdx - 1)
    }
    pick(0, s.length - 1)
    bw.flush()
    bw.close()
}
```