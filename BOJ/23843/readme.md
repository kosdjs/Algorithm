# 백준 23843번: 콘센트

> 문제: https://www.acmicpc.net/problem/23843

### 문제 풀이

그리디

time = 전자기기 충전에 필요한 시간

queue = 각 콘센트가 충전하는데 걸리는 시간

time을 내림차순으로 정렬하고 값 하나마다 큐를 확인함

큐에 원소가 M개보다 적으면 충전기가 빈것이므로 time[i]를 큐에 넣음

그게 아니라면 충전기를 모두 사용중인것이므로 가장 빨리 끝나는 충전기인 큐에서 가장 작은 값을 가지는 원소를 꺼내고 time[i]를 더해서 다시 큐에 넣음

이를 time의 모든 원소에 대해 반복하면 큐에 남은 최댓값이 모든 전자기기를 충전하기 위한 최소 시간이므로 이를 출력하면 정답

### 풀이 설명

N개의 전자기기를 충전하려 한다. 사용 가능한 콘센트는 M개가 있고, 성능은 모두 동일하다.

전자기기들은 한 번에 하나의 콘센트에서만 충전이 가능하고, 충전에 필요한 시간은 2k(0 ≤ k ≤ 15, k는 정수) 형태이다.

모든 전자기기를 충전하기 위한 최소 시간이 얼마인지 구해야 한다. 이는 충전해야 하는 전자기기를 M개의 콘센트로 분배해 충전이 가장 늦게 끝나는 콘센트의 충전 완료 시간의 최소 시간을 구하는 문제가 된다.

이때 충전이 오래 걸리는 전자기기가 한 콘센트에 몰리게 된다면 이 콘센트의 충전 완료 시간이 오래 걸리게 되므로 전자기기를 모두 충전하는 최소 시간을 구할 수 없게 된다. 즉, 충전이 오래 걸리는 전자기기가 한 콘센트에 몰리지 않도록 다른 콘센트로 분배해야 한다는 뜻이다.

그러므로 충전 시간을 내림차순으로 정렬하고 충전이 가장 빨리 끝나는 콘센트에 충전이 오래 걸리는 전자기기를 충전시키도록 하면 충전이 오래 걸리는 전자기기가 한 콘센트에 몰리지 않으므로 모든 전자기기를 충전하는 최소 시간을 구할 수 있다.

이를 구현하도록 충전 시간을 time이라는 배열에 입력받고 내림차순으로 정렬을 한다. 그리고 i를 0부터 time 배열의 끝까지 반복하며 전자기기 하나를 확인했을 때 그 전자기기까지의 콘센트 충전 완료시간을 우선 순위 큐에 저장한다.

이렇게 모든 전자기기에 대해 반복하면 큐에 콘센트마다의 충전 완료 시간이 저장되고 이때의 최댓값이 모든 전자기기를 충전하기 위한 최소 시간이 되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val time = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        time[i] = st.nextToken().toInt()
    }
    time.sortDescending()
    val queue = PriorityQueue<Int>()
    for(i in 0 until N){
        if(queue.size < M){
            queue.add(time[i])
        } else {
            queue.add(queue.poll() + time[i])
        }
    }
    println(queue.max())
}
```