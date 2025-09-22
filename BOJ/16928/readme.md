# 백준 16928번: 뱀과 사다리 게임

> 문제: https://www.acmicpc.net/problem/16928

### 문제 풀이

BFS

map[i] = i번 칸에 도착했을 때 이동되는 칸

visit[i] = i번 칸에 도착한 적이 있는지

cur = 현재 플레이어가 있는 칸

count = 주사위를 굴린 횟수

1번 칸에서부터 주사위를 굴려 이동할 수 있는 칸을 BFS로 탐색해 100번 칸에 도착할 때의 주사위 사용 횟수를 출력하면 정답

### 풀이 설명

게임은 정육면체 주사위를 사용하며, 주사위의 각 면에는 1부터 6까지 수가 하나씩 적혀있다. 게임은 크기가 10×10이고, 총 100개의 칸으로 나누어져 있는 보드판에서 진행된다. 보드판에는 1부터 100까지 수가 하나씩 순서대로 적혀져 있다.

플레이어는 주사위를 굴려 나온 수만큼 이동해야 한다. 예를 들어, 플레이어가 i번 칸에 있고, 주사위를 굴려 나온 수가 4라면, i+4번 칸으로 이동해야 한다. 도착한 칸이 사다리면, 사다리를 타고 위로 올라간다. 뱀이 있는 칸에 도착하면, 뱀을 따라서 내려가게 된다.

게임판의 상태가 주어졌을 때, 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값은 게임판에 따라 플레이어가 위치한 칸에서 주사위를 사용해 이동할 수 있는 칸이 정해지므로 BFS를 이용해 현재 칸에서 주사위를 이용해 갈 수 있는 칸들을 확인하면 구할 수 있다.

먼저 현재 플레이어가 위치한 칸이 i라고 하면 이 플레이어가 주사위로 이동할 수 있는 칸은 i + 1부터 i + 6까지이다. 하지만 이 칸들 중에서 사다리나 뱀이 있을 수 있기 때문에 실질적으로 그 칸에 도착하면 이동하는 칸을 구해놓아야 한다.

따라서 map 배열을 만들어 특정 칸에 도착했을 때 이동되는 칸이 있는지를 저장해놓는다. 처음에는 인덱스와 동일한 숫자를 저장해 그 칸에 뱀 또는 사다리가 없다는 것을 표현하고, 사다리 정보를 받으면 map[x]에 y를 대입해 x칸에 도착하면 사다리를 통해 y칸에 도착한다는 것을 표현하고, 뱀 정보를 받으면 map[u]에 v를 대입해 u칸에 도착하면 뱀을 통해 v칸에 도착한다는 것을 표현한다.

주사위를 최소로 굴려서 이동해야 하기 때문에 특정 칸을 이미 도착한 적이 있다면 그 칸에서 다시 주사위를 굴려 도착할 수 있는 칸을 확인할 필요가 없다. 따라서 visit 배열을 만들어 그 칸에 도착한 적이 있는지를 저장한다.

이제 BFS를 사용하기 위해 큐를 만들고 그 안에 현재 칸과 주사위 사용 횟수를 배열에 담아 넣는다. 그 후에 큐에서 배열을 꺼내 현재 칸과 주사위 사용 횟수를 저장하고 그 칸에서 주사위를 사용해서 갈 수 있는 모든 칸을 확인한다.

이렇게 1번 칸에서부터 현재 칸에서 주사위를 굴려 이동할 수 있는 모든 칸을 확인하다 보면 100번 칸에 도착할 때의 주사위 사용 횟수를 구할 수 있다. 이때의 사용 횟수가 최솟값이므로 이 값을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = IntArray(101){it}
    repeat(N + M){
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        map[x] = y
    }
    val visit = BooleanArray(101)
    visit[1] = true
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(1, 0))
    var end = false
    while (queue.isNotEmpty()){
        val (cur, count) = queue.removeFirst()
        for(i in 1..6){
            if(cur + i < 100 && !visit[map[cur + i]]){
                visit[map[cur + i]] = true
                queue.add(intArrayOf(map[cur + i], count + 1))
            } else if(cur + i == 100){
                println(count + 1)
                end = true
                break
            }
        }
        if(end){
            break
        }
    }
}
```