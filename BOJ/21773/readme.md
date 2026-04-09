# 백준 21773번: 가희와 프로세스 1

> 문제: https://www.acmicpc.net/problem/21773

### 문제 풀이

우선 순위 큐

Process(id, time, priority) = 남은 실행 시간이 time, 우선 순위가 priority인 프로세스

queue = 프로세스를 우선 순위 및 id 순서로 꺼낼 수 있는 우선 순위 큐

1초마다 프로세스를 스케쥴러가 우선 순위가 가장 높은 프로세스 중 id가 가장 작은 프로세스를 1초 실행시키고 다른 모든 프로세스의 우선 순위를 1 증가시켜야 함

다른 모든 프로세스의 우선 순위를 1 증가시킨다는 것은 실행 중인 프로세스의 우선 순위가 상대적으로 1 낮아진다는 것이므로 실행할 프로세스를 1초 실행시킨 이후 그 프로세스의 우선 순위를 1 낮추는 것으로 구현 가능함

프로세스가 id, 남은 실행 시간, 우선 순위를 가지고 있으므로 이를 클래스로 만들어 Comparable을 상속시키도록 하면 어떤 기준으로 비교해야 할 지를 미리 지정할 수 있음

따라서 Process(id, time, priority) 클래스를 만들고 우선 순위를 내림차순으로, 우선 순위가 같다면 id를 오름차순으로 정렬하도록 만듬

그 이후에 Process를 담는 우선 순위 큐를 queue로 만들어 모든 프로세스를 넣은 이후 꺼낸 프로세스 마다 time과 priority를 1씩 감소시킨 이후에 time이 남아있을 경우에만 큐에 다시 넣음

이를 T번 또는 큐가 빌 때까지 반복해서 꺼내면 꺼낸 프로세스가 스케쥴러가 처리할 프로세스이므로 꺼낼때마다 해당 프로세스의 id를 출력하면 정답

### 풀이 설명

스케쥴러가 T초 동안 1초마다 우선 순위가 가장 높은 프로세스 중 id가 가장 작은 프로세스를 골라 1초를 실행하고 나머지 프로세스의 우선 순위를 1씩 올린다고 할 때 n개의 프로세스에 대한 id, 남은 실행 시간, 우선 순위가 주어지면 1초부터 T초까지 실행중인 프로세스의 id를 출력해야 하는 문제이다.

먼저 매 초마다 우선 순위가 가장 높은 프로세스 중 id가 가장 작은 프로세스를 선택한다는 점에서 매번 정렬해 한 개체를 뽑기 때문에 우선 순위 큐를 사용할 수 있다는 점을 알 수 있다.

하지만 문제 조건 중에 실행 중인 프로세스 외의 나머지 프로세스의 우선 순위를 1씩 올린다는 점이 구현하기 어려운데 이는 사실 현재 실행중인 프로세스의 우선 순위를 1만큼 줄이면 상대적으로 나머지 프로세스의 우선 순위가 1 올라간다는 사실을 이용해 실행하는 프로세스의 우선 순위를 내리는 방식으로 구현할 수 있다.

우선 순위 큐에 프로세스를 넣을 수 있도록 id, 남은 실행 시간, 우선 순위를 담을 수 있는 Process(id, time, priority) 클래스를 만들고 스케쥴러가 우선 순위가 가장 높은 프로세스 중 id가 가장 작은 프로세스를 선택해야 하므로 해당 기준인 우선 순위에 대해 내림차순, 우선 순위가 같다면 id에 대해 오름차순으로 정렬할 수 있도록 Comparable을 상속해 구현했다.

그 이후에 스케쥴러가 프로세스를 T초 동안 어떤 id의 프로세스를 실행하는 지 출력해야 하므로 우선 순위 큐 queue에 모든 프로세스를 넣으면 매번 큐에서 꺼내는 프로세스가 스케쥴러가 선택하는 조건의 프로세스가 된다.

따라서 T번 또는 큐가 빌 때까지 프로세스 p를 꺼내 남은 실행 시간 p.time과 p.priority를 1씩 감소시키고 해당 프로세스를 스케쥴러가 실행한 것이므로 p.id를 출력해 해당 프로세스의 id를 출력해주고 p.time이 0보다 크다면 아직 실행 시간이 남아있는 것이므로 큐에 다시 집어넣으면 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    class Process(val id: Int, var time: Int, var priority: Int): Comparable<Process>{
        override fun compareTo(other: Process): Int {
            return if (this.priority != other.priority) {
                other.priority - this.priority
            } else {
                this.id - other.id
            }
        }
    }
    var T = nextInt()
    val n = nextInt()
    val queue = PriorityQueue<Process>()
    for(i in 0 until n){
        queue.add(Process(nextInt(), nextInt(), nextInt()))
    }
    val sb = StringBuilder()
    while(queue.isNotEmpty() && T > 0){
        val p = queue.poll()
        sb.append(p.id).append("\
")
        p.time--
        p.priority--
        if(p.time > 0) queue.add(p)
        T--
    }
    print(sb)
}
```