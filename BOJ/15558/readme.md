# 백준 15558번: 점프 게임

> 문제: https://www.acmicpc.net/problem/15558

### 문제 풀이

BFS

map[i][j] = i줄 j칸이 안전한 칸인지 여부

visit[i][j] = i줄 j칸을 방문했는지 여부

clear = 게임을 클리어 가능한지 여부

queue = 현재 줄과 칸, 이동 횟수를 저장할 큐

왼쪽 줄 1번 칸에서 시작하지만 배열의 인덱스는 0부터 시작하므로 i가 0일 때 왼쪽 줄, 1일 때 오른쪽 줄, j가 0부터 N - 1까지 1번 칸부터 N번째 칸까지를 나타냄

먼저 왼쪽 줄 1번 칸에서 시작한다는 것을 나타내기 위해 큐에 (0, 0)과 이동횟수 0을 넣고 첫 칸을 이동처리 하기 위해 visit[0][0]에 true를 대입함

큐에서 현재 줄과 칸인 i, j와 이동 횟수 time을 꺼내 앞으로 이동하는 경우 부터 확인함

앞으로 이동하는 경우는 마지막 칸에서 앞으로 가면 클리어 조건이므로 이를 확인해서 해당하는 경우만 clear에 true를 대입하고 반복문을 나감

그게 아니라면 앞으로 한 칸 이동이 가능한지 확인해야 하므로 앞 칸이 안전한지를 map[i][j + 1]로 확인하고 방문 여부를 visit[i][j]로 확인함

방문이 가능하다면 visit[i][j + 1]에 true를 대입하고 앞으로 가능 이동을 한 것이므로 그 때의 칸 (i, j + 1)과 이동 횟수 time + 1을 큐에 넣어줌

뒤로 이동하는 경우는 이동 할때마다 한 칸씩 사라지므로 다음 이동 횟수에 사라지는 칸으로 이동하면 안되니 j - 1이 time + 1보다 클때만 뒤로 이동이 가능함

그 이후엔 map[i][j - 1], visit[i][j - 1]을 확인해 방문하지 않고 이동 가능한 칸일때만 방문처리 및 큐에 넣음

옆 줄의 k칸 앞으로 가는 것도 N칸을 넘어가면 클리어 조건이므로 j + k가 N보다 크거나 같으면 clear에 true 대입하고 반복문 탈출함

아니라면 옆 줄의 k칸 앞이 이동 가능하고 방문한 적이 없는지 확인해 방문처리를 하고 큐에 넣으면 됨

반복문이 끝났을 때 clear가 true라면 클리어가 가능한 것이므로 1, false라면 클리어가 불가능한 것이므로 0을 출력하면 정답

### 풀이 설명

같은 줄의 앞, 뒤칸으로 이동이 가능하고 반대편 줄의 k칸 앞으로 이동하는 것이 가능할 때 N번 칸을 넘어갈 수 있는지 확인하는 문제이므로 BFS를 이용해 풀 수 있다.

단, 위험한 칸은 이동이 불가능하고, 한 번 이동할 때마다 한 칸씩 사라지므로 이동 횟수를 저장해야 한다. 그리고 같은 줄의 앞, 뒤칸을 이동하므로 같은 칸을 여러번 이동할 가능성이 있으므로 이를 막기 위해 방문 여부도 따로 저장을 해야 한다.

따라서 현재 칸과 이동 횟수를 저장할 큐를 정의하고 그 큐에서 칸과 이동 횟수에 따라 같은 줄의 앞, 뒤 칸과 옆 줄의 k 칸 앞을 확인해 이동할 수 있고 방문하지 않은 칸만 큐에 다시 집어넣는다.

앞으로 이동하는 것과 옆 줄의 k 칸 앞으로 이동하는 경우에 N칸을 넘어가는 경우가 있을 수 있고 이 때는 게임을 클리어 할 수 있는 것이므로 더 이상 이동하는 것을 확인할 필요가 없다. 그러므로 이 때는 클리어 가능 여부만 저장하고 반복문을 탈출한다.

이렇게 반복하다보면 클리어가 가능해서 반복문을 조기 탈출하는 경우가 있고, 이동 가능한 모든 칸에서 이동을 해도 클리어가 불가능한 경우가 있으므로 이에 따라 1, 0을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val(N, k) = readLine().split(" ").map { it.toInt() }
    val map = Array(2){BooleanArray(N)}
    val visit = Array(2){BooleanArray(N)}
    for(i in 0 until 2){
        val s = readLine()
        for(j in 0 until N){
            map[i][j] = s[j] == '1'
        }
    }
    var clear = false
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(0, 0, 0))
    visit[0][0] = true
    while(queue.isNotEmpty()){
        val (i, j, time) = queue.removeFirst()
        if(j + 1 >= N){
            clear = true
            break
        } else if(map[i][j + 1] && !visit[i][j + 1]) {
            visit[i][j + 1] = true
            queue.add(intArrayOf(i, j + 1, time + 1))
        }
        if(j - 1 >= time + 1 && map[i][j - 1] && !visit[i][j - 1]){
            visit[i][j - 1] = true
            queue.add(intArrayOf(i, j - 1, time + 1))
        }
        if(j + k >= N){
            clear = true
            break
        } else if(map[(i + 1) % 2][j + k] && !visit[(i + 1) % 2][j + k]){
            visit[(i + 1) % 2][j + k] = true
            queue.add(intArrayOf((i + 1) % 2, j + k, time + 1))
        }
    }
    println(if(clear) 1 else 0)
}
```