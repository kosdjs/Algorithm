# 백준 19598번: 최소 회의실 개수

> 문제: https://www.acmicpc.net/problem/19598

### 문제 풀이

그리디

회의를 시작 시간 기준으로 오름차순으로 정렬 후 회의의 종료 시간 기준으로 오름차순 정렬하는 우선순위 큐에 하나씩 넣으면서 기존에 있었던 회의의 종료 시간이 현재 넣는 회의의 시작 시간보다 빠르면 기존 회의를 큐에서 뺌, 이 방식으로 모든 회의를 큐에 넣었을 때 남아있는 회의의 수가 필요한 회의실의 최소 갯수이므로 출력하면 정답

### 풀이 설명

N개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하는 것은 회의를 진행할 때 회의실을 최소한으로 사용하는 것이므로 회의를 진행할 때 최대한 회의실을 재사용해야 한다는 것을 뜻한다. 

이때 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다고 했으므로 회의실을 재사용한다는 것은 다른 회의가 끝난 회의실에서 회의를 시작하는 것을 뜻한다.

회의실을 재사용할 수 있는지 여부는 새로운 회의가 시작할 때 알 수 있으므로 회의를 시작 시간을 기준으로 정렬하여 순서대로 진행하며 확인한다. 또한 회의실에서 현재까지 진행되었던 마지막 회의의 종료 시간을 알아야 재사용 가능 여부를 알 수 있으므로 이를 위해 회의의 종료 시간기준으로 정렬되는 우선 순위 큐를 만든다.

이제 회의를 순서대로 확인하며 현재 진행해야 하는 회의의 시작 시간과 큐에 있는 이전에 시작된 회의의 종료 시간을 비교한다. 이때 큐에 있는 회의의 종료 시간이 현재 진행해야 하는 회의의 시작 시간보다 크다면 현재 회의는 회의실을 재사용하지 못하므로 새로운 회의실에서 진행되어야 한다. 그러면 현재 회의가 새로운 회의실에서 진행되는 마지막 회의가 되기 때문에 큐에 회의를 넣는다.

반대로 큐에 있는 회의의 종료 시간이 현재 진행해야 하는 회의의 시작 시간보다 작거나 같다면 큐에 있는 회의가 진행되었던 회의실을 재사용할 수 있으므로 이 회의실에서 진행되는 마지막 회의를 남기기 위해 큐에 있는 회의를 큐에서 빼주고 현재 회의를 큐에 넣는다.

이렇게 모든 회의를 큐에 넣으면 회의실을 최대한 재사용할 때 회의실에서 마지막에 진행된 회의만 남기 때문에 큐에 남은 회의의 수가 필요한 회의실의 최소 갯수가 된다. 따라서 이 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val meetings = Array(N){ IntArray(2) }
    for(i in 0 until N){
        val st = StringTokenizer(readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        meetings[i] = intArrayOf(start, end)
    }
    meetings.sortWith { o1, o2 -> o1[0] - o2[0] }
    val meetingRoom = PriorityQueue<IntArray>{ o1, o2 -> o1[1] - o2[1]}
    meetingRoom.add(meetings[0])
    for(i in 1 until N){
        val prevEnd = meetingRoom.peek()[1]
        val curStart = meetings[i][0]
        if(curStart >= prevEnd){
            meetingRoom.poll()
        }
        meetingRoom.add(meetings[i])
    }
    println(meetingRoom.size)
}
```