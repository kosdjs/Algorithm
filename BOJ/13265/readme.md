# 백준 13265번: 색칠하기

> 문제: https://www.acmicpc.net/problem/13265

### 문제 풀이

BFS

graph[x] = x번 동그라미와 연결된 동그라미들

color[x] = x번 동그라미의 색

isPossible = 두 가지 색으로 색칠이 가능한지 여부

1번부터 n번까지 색이 칠해지지 않은 동그라미에 대해 1번 색을 칠함

그리고 연결된 동그라미들에 대해 현재 동그라미와 다른 색을 칠하면서 만약 연결된 동그라미 중 현재 동그라미와 같은 색의 동그라미가 나온다면 isPossible에 false를 대입하고 반복문을 탈출함

반복문이 끝났을 때 isPossible이 true라면 두 가지 색으로 연결된 동그라미가 색이 겹치지 않도록 칠한 것이므로 possible, false라면 불가능한 것이므로 impossible을 출력하면 정답

### 풀이 설명

두 가지 색으로 모든 동그라미를 색칠해야 할 때 연결된 두 동그라미를 다른 색으로 칠할 수 있는지 판별하는 문제이다. 여기서 동그라미가 연결된 구조를 그래프로 생각할 수 있다.

만약 한 동그라미와 연결된 두 동그라미가 서로 연결되어 있다면 첫 동그라미에 색칠한 다른 색으로 서로 연결된 두 동그라미를 색칠해야 하는데 이러면 연결된 두 동그라미가 같은 색으로 칠해지는 것이므로 조건에 맞지 않게 된다.

따라서 모든 동그라미들에 색을 칠하면서 인접한 동그라미의 색들을 확인하면서 조건에 맞게 색이 칠해졌는지 검사하기 위해 BFS를 이용할 수 있다.

BFS를 이용하기 위해 그래프를 인접 리스트 형태로 저장하기 위해 ArrayList의 배열 graph를 정의한다. 또한 동그라미에 색이 칠해졌는지, 어떤 색이 칠해졌는지를 저장해야 하므로 color라는 배열을 만들어 색이 칠해져있지 않다면 0, 두 가지 색을 1, 2로 저장한다. 그리고 두 가지 색으로 모든 동그라미를 칠할 수 있는지 여부를 isPossible로 정의를 하고 문제 특성상 불가능한 경우를 감지하므로 true로 초기화한다.

이제 1번부터 n번 동그라미를 확인하면서 색이 칠해지지 않은 동그라미들에 대해 색을 칠하며 연결된 동그라미가 있다면 색이 칠해져있지 않다면 현재 동그라미와 다른 색으로 칠하면서 연결된 동그라미의 색이 같게 되는지를 확인한다.

연결된 동그라미의 색이 같다면 두 가지 색으로 모든 동그라미를 칠할 수 없는 것이므로 isPossible에 false를 대입해 두 가지 색으로 모든 동그라미를 칠할 수 없다는 것을 나타내고 동그라미를 칠하는 과정을 멈춘다.

반복문이 끝난 이후에 isPossible을 검사해 true라면 조건에 맞게 모든 동그라미를 색칠한 것이므로 possible을 출력하고, false라면 모든 동그라미를 두 가지 색으로 칠하지 못한 것이므로 impossible을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val sb = StringBuilder()
    repeat(T){
        val n = nextInt()
        val m = nextInt()
        val graph = Array(n + 1){ArrayList<Int>()}
        val color = IntArray(n + 1)
        var isPossible = true
        repeat(m){
            val x = nextInt()
            val y = nextInt()
            graph[x].add(y)
            graph[y].add(x)
        }
        val queue = ArrayDeque<Int>()
        for(i in 1..n){
            if(!isPossible) break
            if(color[i] == 0){
                color[i] = 1
                queue.add(i)
                while(queue.isNotEmpty()){
                    val x = queue.removeFirst()
                    for(y in graph[x]){
                        if(color[x] == color[y]){
                            isPossible = false
                            break
                        }
                        if(color[y] == 0){
                            color[y] = if(color[x] == 1) 2 else 1
                            queue.add(y)
                        }
                    }
                    if(!isPossible) break
                }
            }
        }
        sb.append(if(isPossible) "possible" else "impossible").append("\n")
    }
    print(sb)
}
```