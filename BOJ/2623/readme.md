# 백준 2623번: 음악프로그램

> 문제: https://www.acmicpc.net/problem/2623

### 문제 풀이

위상 정렬

$prev[i] = i$ 번 가수 전에 출연해야 하는 가수의 수

$next[i] = i$ 번 가수 출연 이후에 출연할 수 있는 가수의 리스트

보조 PD의 순서에 맞게 $prev, next$를 채우고 $prev$가 $0$인 가수들을 큐에 집어넣고 큐가 빌때까지 다음 순서를 따름

1. 큐에서 가수 $c$를 꺼내 정답 리스트에 넣음

2. $c$가 출연해야 출연할 수 있는 가수들의 $prev$값을 $1$ 감소시키고 $0$이 되면 큐에 넣음

정답 리스트에 N개가 있으면 리스트 순서대로 출력, 아니면 0을 출력하면 정답

### 풀이 설명

보조 PD가 만든 순서에 따라 가수들을 순서대로 출연시키는 것을 순서의 앞에서부터 한 가수씩 본다고 하면 현재 순서에 출연할 수 있는 가수는 먼저 출연해야 할 가수가 없거나 먼저 출연해야 할 가수들이 이미 출연한 경우다.

따라서 순서를 정하기 위해 $prev[i]$를 $i$ 번 가수 전에 출연해야 하는 가수의 수, $next[i]$를 $i$ 번 가수 출연 이후에 출연할 수 있는 가수의 리스트라고 정의하고 이를 보조 PD들이 가져온 순서대로 채운다.

맨 처음 출연할 수 있는 가수들은 먼저 출연해야 할 가수들이 없는 가수이므로 $prev$값이 $0$인 가수들만 큐에 집어넣는다. 그 후에 큐에서 현재 출연할 수 있는 가수 $c$를 꺼내 순서에 배치하고 $c$가 먼저 출연해야 출연할 수 있는 가수 $n$에 대해 $c$가 출연했으므로 $prev[n]$의 값을 1 줄이고 이 값이 $0$이 되면 $n$이 출연하기 위해 먼저 출연해야 하는 모든 가수가 출연한 것이 되므로 $n$을 큐에 넣는다.

이 과정을 통해 $answer$에 순서대로 출연할 수 있는 가수들을 리스트에 넣었으므로 이 리스트에 모든 가수들이 들어있다면 순서를 정할 수 있는것이므로 리스트 순서대로 출력을 하고 아니라면 순서를 정할 수 없는 것이기 때문에 0을 출력한다.

### 소스 코드
```kotlin
import java.util.*

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val prev = IntArray(N + 1){0}
    val next = Array(N + 1){ArrayList<Int>()}
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val m = st.nextToken().toInt()
        var p = st.nextToken().toInt()
        var c = 0
        for(j in 1 until m){
            c = st.nextToken().toInt()
            if(!next[p].contains(c)){
                next[p].add(c)
                prev[c]++
            }
            p = c
        }
    }
    val queue = ArrayDeque<Int>()
    for(i in 1..N){
        if(prev[i] == 0){
            queue.add(i)
        }
    }
    val answer = ArrayList<Int>()
    while(queue.isNotEmpty()){
        val c = queue.removeFirst()
        answer.add(c)
        for(n in next[c]){
            prev[n]--
            if(prev[n] == 0){
                queue.add(n)
            }
        }
    }
    if(answer.size == N){
        for(a in answer){
            println(a)
        }
    } else {
        println(0)
    }
}
```