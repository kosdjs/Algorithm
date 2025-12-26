# 백준 11286번: 절댓값 힙

> 문제: https://www.acmicpc.net/problem/11286

### 문제 풀이

우선 순위 큐

queue = 절댓값을 기준으로 오름차순으로 정렬하는 우선 순위 큐

입력이 0이 아니라면 queue에 수를 넣음

입력이 0이라면 queue에 수가 있는지 isNotEmpty() 함수를 이용해 확인함

수가 있다면 있는 수를 출력하거나 없다면 0을 출력하면 정답

### 풀이 설명

절댓값 힙은 다음과 같은 연산을 지원하는 자료구조이다.

1. 배열에 정수 x (x ≠ 0)를 넣는다.
2. 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.

프로그램은 처음에 비어있는 배열에서 시작하게 된다.

즉, 정렬 기준이 절댓값이 되는 우선 순위 큐를 사용하면 된다. 다만 절댓값이 같다면 더 작은 수를 출력해야 하므로 비교 기준을 절댓값을 기준으로 하되 절댓값이 같으면 수 자체를 비교해야 한다.

따라서 우선 순위 큐에 Comparator 람다로 두 수의 절댓값이 다를 때만 절댓값을 기준으로 비교를 하고 같을 때는 값 자체로 비교하도록 넘겨준다. 문제 조건에 따라 수를 넣거나 큐에서 수를 꺼내 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.absoluteValue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val queue = PriorityQueue<Int>{ o1, o2 ->
        if(o1.absoluteValue != o2.absoluteValue) o1.absoluteValue - o2.absoluteValue
        else o1 - o2
    }
    val sb = StringBuilder()
    repeat(N){
        val num = nextInt()
        if(num != 0) queue.add(num)
        else{
            if(queue.isNotEmpty()) sb.append(queue.poll())
            else sb.append(0)
            sb.append("\n")
        }
    }
    print(sb)
}
```