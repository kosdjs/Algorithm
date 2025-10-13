# 백준 7983번: 내일 할거야

> 문제: https://www.acmicpc.net/problem/7983

### 문제 풀이

그리디

homework = 과제를 t를 기준으로 내림차순 정렬하는 우선순위 큐

answer = 과제를 마감시간 전에 끝낼 수 있도록 최대한 쉴 수 있는 날

homework에 정렬된 과제마다 d, t에 대해 answer가 t보다 크면 answer에 t 대입

과제를 하는데 d일 걸리므로 answer에서 d를 빼줌

모든 과제에 대해 계산을 하면 answer에 1일부터 몇일까지 쉴 수 있는지 저장되어 있으므로 출력하면 정답

### 풀이 설명

각각의 과제 i는 d일이 걸리고, 오늘로부터 t일 안에 끝내야 한다. 과제는 총 n개가 있다. 오늘은 0일이고 오늘 아무 것도 안 해도 과제를 마무리 할 수 있는 방법이 존재함이 보장된다. 과제는 한번 시작하면 쉬지 않고 계속해야 한다.

따라서 과제를 끝내는데 d일이 걸리고 t일 안에 끝내야 하는 과제가 n개 있을 때 과제를 모두 끝낼 수 있도록 미뤘을 때 최대 몇일 쉴 수 있는지를 구해야 하는 문제다.

이는 과제를 끝내야 하는 날짜 t를 기준으로 정렬한다면 끝내야 하는 날짜가 더 늦는 과제에 의해 현재 과제를 끝내야 하는 날짜보다 빠르게 끝내야 할 수 있다. 예를 들어 7일까지 끝내야 하는 과제가 끝내는 데 4일 걸리고, 5일까지 끝내야 하는 과제가 2일 걸린다고 했을 때 5일까지 끝내야 하는 과제를 4일부터 시작해 5일에 끝낸다고 하면 7일까지 끝내야 하는 과제를 7일까지 끝내지 못할 수 있기 때문이다.

따라서 과제를 끝내야 하는 날짜 t를 기준으로 내림차순으로 정렬한 후에 과제를 끝내려면 과제를 언제까지 미룰 수 있는지를 구하면 된다. answer를 과제를 하지 않고 쉴수 있는 날짜로 정의하고 초기값을 Int.MAX_VALUE로 초기화한다.

그 후에 과제를 t를 기준으로 내림차순으로 정렬하도록 우선순위 큐를 사용해 정렬한다. 과제를 하나씩 꺼내서 d, t 값을 확인하고 answer가 t보다 크다면 과제가 t일에 끝나도록 t를 대입해준다. 그 이후에 과제가 d일 걸린다고 했으므로 answer에서 d를 빼 answer일까지는 과제를 하지 않는다는 것을 저장한다.

이를 모든 과제에 대해 반복하면 최종적으로 answer에 몇 일까지 쉴 수 있는지가 저장된다. 따라서 answer를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val homework = PriorityQueue<IntArray>(compareByDescending { it[1] })
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        homework.add(intArrayOf(st.nextToken().toInt(), st.nextToken().toInt()))
    }
    var answer = Int.MAX_VALUE
    while(homework.isNotEmpty()){
        val (d, t) = homework.poll()
        if(answer > t){
            answer = t
        }
        answer -= d
    }
    println(answer)
}
```