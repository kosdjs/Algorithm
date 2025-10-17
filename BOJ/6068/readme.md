# 백준 6068번: 시간 관리하기

> 문제: https://www.acmicpc.net/problem/6068

### 문제 풀이

그리디

work = 일을 끝내야 하는 시간 s를 기준으로 내림차순으로 정렬하는 큐

answer = 앞서 확인한 일을 최대한 미뤄서 끝내면 쉴 수 있는 시간

내림차순으로 정렬된 일들의 끝내야 하는 시간 s와 answer를 비교해 더 작은 값에서 일을 하는데 걸리는 시간 t를 빼면 그 시간까지 쉴 수 있는 시간임

모든 일에 대해 반복해 answer를 구하면 그 값이 0보다 작다면 일을 끝내는 것이 불가능 한 것이고 아니라면 그 값만큼 쉴 수 있는 시간이므로 -1 또는 값을 출력해주면 정답

### 풀이 설명

존의 시간을 효율적으로 관리하기 위해, 그는 끝내야만 하는 일 목록을 만들었다. 완성될 때 필요한 시간을 T_i(1<=T_i<=1,000) 라고 하며, 끝내야하는 시간을 S_i(1<=S_i<=1,000,000) 이라 한다. 농부 존은 하루의 시작을 t = 0으로 정했다. 그리고 일 할 때는 그 일을 마칠 때 까지 그 일만 한다. 

존은 늦잠 자는 걸 좋아한다. 따라서 제 시간에 끝낼 수 있게 결정할 수 있는 한도에서 존이 가장 늦게 일어나도 되는 시간을 출력하라.

[내일 할거야](https://velog.io/@kosdjs/%EB%B0%B1%EC%A4%80-7983%EB%B2%88-%EB%82%B4%EC%9D%BC-%ED%95%A0%EA%B1%B0%EC%95%BC) 문제와 같은 문제다. 즉, 일을 다 끝내면서 최대한 미루려면 얼마나 쉴 수 있는지를 구하는 문제고 이는 일을 끝내야 하는 시간을 기준으로 내림차순으로 정렬하고 일을 하나하나 확인하면서 끝내야 하는 시간과 앞서 확인한 일을 최대한 미뤄서 끝내면 쉴 수 있는 시간을 비교해 더 작은 값을 저장하고 그 값에서 일을 끝내는 데 걸리는 시간을 빼주면 된다.

따라서 일의 끝내야 하는 시간을 기준으로 내림차순 정렬해주는 우선순위 큐 work를 만들고, 일을 입력받아서 큐에 넣는다. 그리고 answer를 앞서 확인한 일을 최대한 미뤄서 끝내면 쉴 수 있는 시간으로 정의하고 Int.MAX_VALUE로 초기화 한다.

큐에서 일을 하나씩 꺼내서 answer와 일을 끝내야 하는 시간 s를 비교해 최솟값을 내고 거기서 일을 하는데 걸리는 시간 t를 뺀 값을 answer에 저장한다. 이를 모든 일에 대해 반복하면 answer에 저장되는 값이 모든 일을 최대한 미뤄서 하면 쉴 수 있는 시간이 저장되고 이 값이 0보다 작다면 모든 일을 끝내는 것이 불가능 한 것이므로 -1 출력, 아니라면 그 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val work = PriorityQueue<IntArray>(compareByDescending { it[1] })
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        work.add(intArrayOf(st.nextToken().toInt(), st.nextToken().toInt()))
    }
    var answer = Int.MAX_VALUE
    while (work.isNotEmpty()){
        val (t, s) = work.poll()
        answer = minOf(answer, s) - t
    }
    println(if(answer < 0) -1 else answer)
}
```