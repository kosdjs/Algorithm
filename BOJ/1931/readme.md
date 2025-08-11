# 백준 1931번: 회의실 배정

> 문제: https://www.acmicpc.net/problem/1931

### 문제 풀이

그리디

회의를 끝나는 시간 기준으로 오름차순 정렬, 끝나는 시간이 같다면 시작 시간을 기준으로 오름차순 정렬

정렬 이후 회의를 순서대로 확인하며 시작 가능한 회의를 세면 정답

### 풀이 설명

회의의 시작 시간과 끝나는 시간이 주어져 있고 회의가 진행되는 동안 다른 회의가 진행될 수 없을 때 회의를 최대한 많이 하려면 끝나는 시간이 빠른 회의부터 진행을 해야 한다.

예를 들어 시작 시간과 끝나는 시간이 각각 (1, 5), (4, 6), (5, 10) 인 회의들이 있다고 했을 때 단순히 회의 시간이 짧은 (4, 6)을 선택하면 회의가 끝난 시간이 6이기 때문에 다음에 시작할 수 있는 회의가 없어진다. 하지만 끝나는 시간이 제일 빠른 (1, 5)를 선택해 회의를 시작하면 끝난 시간이 5이기 때문에 (5, 10)인 회의를 시작할 수 있다.

다시 말해 회의를 시작할 수 있는지를 회의의 시작 시간으로 구분하고 이는 앞서 진행한 회의의 끝나는 시간을 기준으로 판별하기 때문에 선택할 수 있는 가짓수를 늘리려면 끝나는 시간이 최솟값이 되어야 한다는 뜻이다.

끝나는 시간이 같은 회의일 때 시작 시간으로 오름차순 정렬을 해야 하는 이유는 이 문제에서 회의의 시작 시간과 끝나는 시간이 같은 회의가 있을 수 있다고 했기 때문이다.

예시로 (2, 2), (1, 2)인 회의가 있을 때 시작 시간으로 정렬을 하지 않는다면 (2, 2)인 회의를 먼저 진행해 (1, 2)인 회의를 진행할 수 없지만, 정렬하면 (1, 2)인 회의를 먼저 진행 후 (2, 2)인 회의를 진행할 수 있기 때문이다.

따라서 끝나는 시간과 시작 시간을 기준으로 오름차순 정렬하고 정렬된 순서대로 회의를 진행할 수 있는 개수를 세면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val list = ArrayList<IntArray>()
    repeat(N){
        val st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        list.add(intArrayOf(start, end))
    }
    var time = 0
    var answer = 0
    list.sortWith { o1, o2 -> if (o1[1] == o2[1]) o1[0] - o2[0] else o1[1] - o2[1] }
    for((start, end) in list){
        if(start >= time){
            time = end
            answer++
        }
    }
    println(answer)
}
```