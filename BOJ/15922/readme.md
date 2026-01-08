# 백준 15922번: 아우으 우아으이야!!

> 문제: https://www.acmicpc.net/problem/15922

### 문제 풀이

스위핑

answer = 선분의 총 길이

start = 이전 선분의 시작 좌표

end = 이전 선분의 끝 좌표

첫 선분의 좌표 x, y를 start, end에 저장함

다음 선분부터 x, y를 받은 후에 x를 end와 비교함

end보다 작거나 같다면 선분이 겹치는 것이므로 end와 y를 비교해 더 큰 값을 end에 저장함

end보다 크다면 선분이 겹치지 않으므로 이전 선분의 길이 end - start를 answer에 저장하고 현재 x, y를 start, end에 저장함

이를 모든 선분에 대해 반복하면 마지막 선분의 길이를 반영하지 않았으므로 end - start를 answer에 더해줌

그러면 answer에 모든 선분의 총 길이가 저장되므로 출력하면 정답

### 풀이 설명

수직선 상에 선분의 시작, 끝 좌표가 주어질 때 수직선 위에 그어진 선분 길이의 총합을 구하는 문제이다.

입력에서 선분의 시작 좌표 x, 끝 좌표 y가 주어지는데 이때 순서가 x가 증가하는 순서로 주어지고 x가 같다면 y가 증가하는 순서대로 주어진다고 했으므로 선분을 주어지는 순서대로 확인하며 선분이 겹치는지 확인하면 된다.

현재 선분이 이전 선분과 겹친다면 좌표 x는 당연히 이전에 주어진 x보다 크기 때문에 신경쓸 필요가 없고 y가 이전 선분의 y보다 큰지만 확인해 끝 좌표를 갱신하면 되고, 겹치지 않는다면 이전에 살펴본 선분의 길이만 저장하면 된다.

따라서 총 길이를 answer로 정의하고 첫 선분의 x, y를 start, end에 저장한 이후에 다음 선분의 x, y에 대해 x가 end보다 작거나 같다면 이전 선분과 겹치는 것이므로 end에 end와 y중 더 큰 값을 저장하면 된다.

x가 end보다 크다면 이전 선분과 겹치지 않는 것이므로 end - start를 answer에 더해주고 이 선분부터 다시 겹치는지 확인해야 하므로 start, end에 x, y를 대입해준다.

이 과정을 모든 선분에 대해 확인하면 마지막 선분의 길이를 더하지 않은 것이므로 end - start를 answer에 더해주면 구하는 선분의 총 길이가 answer에 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var answer = 0
    var start = nextInt()
    var end = nextInt()
    repeat(N - 1){
        val x = nextInt()
        val y = nextInt()
        if(x <= end){
            end = maxOf(end, y)
        } else {
            answer += end - start
            start = x
            end = y
        }
    }
    answer += end - start
    println(answer)
}
```