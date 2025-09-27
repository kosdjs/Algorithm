# 백준 2660번: 회장뽑기

> 문제: https://www.acmicpc.net/problem/2660

### 문제 풀이

BFS

회원들의 친구 관계를 무방향 그래프로 정의함

모든 회원에 대해 다른 모든 회원들을 BFS로 탐색해 최대 거리를 구하면 이 최대 거리가 점수가 됨

이 점수들을 score 배열에 저장하고 최소 점수와 인원수를 구하고 출력함

배열을 다시 반복해 최소 점수인 인원들을 출력하면 정답

### 풀이 설명

월드컵 축구의 응원을 위한 모임에서 회장을 선출하려고 한다. 이 모임은 만들어진지 얼마 되지 않았기 때문에 회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다. 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.

예를 들어 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다. 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말한다. 또한 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말한다.

각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이라고 본다. 회장은 회원들 중에서 점수가 가장 작은 사람이 된다. 이때 회장의 점수와 회장이 될 수 있는 모든 사람을 찾으려면 회원의 친구 관계를 그래프로 나타내 BFS를 사용해 구할 수 있다.

회원의 친구 관계를 그래프로 나타내면 회원이 정점이 되고, 그 회원과 친구 관계인 다른 회원들이 간선으로 연결된다. 이렇게 그래프로 친구 관계를 나타내면 한 회원의 점수가 다른 모든 정점까지의 최단 거리중 최댓값이 된다.

따라서 BFS를 통해 모든 정점을 최단 거리로 탐색하며 가장 먼 정점까지의 거리를 구하면 이 거리가 그 회원의 점수가 된다. 이를 모든 회원에 대해 반복해 모든 회원의 점수를 구하고 배열에 저장하면 최소 점수와 그 최소 점수인 인원의 수를 구할 수 있다. 이를 구해 출력하고, 다시 배열을 확인해 최소 점수인 인원들을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val graph = Array(n + 1){ArrayList<Int>()}
    val score = IntArray(n + 1){n}
    val queue = ArrayDeque<IntArray>()
    while(true){
        val st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        if(u == -1 && v == -1){
            break
        }
        graph[u].add(v)
        graph[v].add(u)
    }
    for(i in 1..n){
        val visit = BooleanArray(n + 1)
        var max = 0
        queue.add(intArrayOf(i, 0))
        visit[i] = true
        while(queue.isNotEmpty()){
            val (u, score) = queue.removeFirst()
            if(score > max){
                max = score
            }
            for(v in graph[u]){
                if(!visit[v]){
                    visit[v] = true
                    queue.add(intArrayOf(v, score + 1))
                }
            }
        }
        score[i] = max
    }
    var minScore = n
    var count = 0
    for(i in 1..n){
        if(minScore > score[i]){
            minScore = score[i]
            count = 1
        } else if(minScore == score[i]){
            count++
        }
    }
    println("$minScore $count")
    for(i in 1..n){
        if(score[i] == minScore){
            print("$i ")
        }
    }
}
```