# 백준 22252번: 정보 상인 호석

> 문제: https://www.acmicpc.net/problem/22252

### 문제 풀이

집합과 맵, 우선 순위 큐

map[name] = 이름이 name인 정보 고릴라의 정보가 담긴 우선 순위 큐

answer = 호석이가 정보를 사는 데 든 총 비용의 합

정보 고릴라가 이름으로 구분되고 정보를 살 때 항상 가장 비싼 정보부터 산다고 했으므로 정보 고릴라마다 정보들을 내림차순으로 꺼낼 수 있는 우선 순위 큐가 필요하고 이를 맵을 통해 이름으로 우선 순위 큐를 접근할 수 있도록 만들면 됨

그렇게 map[name]을 이름이 name인 정보 고릴라의 우선 순위 큐라고 하면 입력으로 정보 고릴라가 입수한 정보들에 대해 해당 우선 순위 큐에 정보들을 추가해주면 됨

호석이가 정보 고릴라에게 정보를 사는 것은 map을 확인해 해당 정보 고릴라의 우선 순위 큐를 확인해 b개 또는 b개 이하의 해당 정보 고릴라가 가지고 있는 모든 정보를 꺼내 해당 비용을 answer에 더해주면 됨

모든 쿼리에 대해 처리하면 answer가 호석이가 정보를 사는 데 든 비용의 총 합이므로 출력하면 정답

### 풀이 설명

정보 고릴라에게 정보가 여러 개 존재할 수 있고, 호석이가 정보를 살 때는 정보 고릴라가 가지고 있는 가장 비싼 정보부터 사야 한다. 정보 고릴라는 호석이에게 정보를 팔면 해당 정보가 더 이상 가치가 없으므로 폐기한다고 한다.

정보 고릴라가 여러 명이라 이름으로 구분되고, 어떤 이름의 정보 고릴라가 몇 개의 정보를 얻었는지와 호석이가 어떤 고릴라에게 몇 개의 정보를 샀는지 정보가 주어지면 호석이가 총 얼마를 썼는지 구하는 문제이다.

여기서 호석이가 정보를 살 때 가장 비싼 정보부터 산다고 했으므로 정보를 살 때 가장 비싼 정보가 어떤 것인지 효율적으로 찾을 수 있는 자료 구조가 필요하다. 이는 우선 순위 큐를 사용해 비용이 비싼 정보가 가장 먼저 나올 수 있도록 하면 구현 가능하다.

또한 정보 고릴라가 여러 명이 있고 이름으로 구분된다고 했으므로 여러 개의 우선 순위 큐가 어떤 정보 고릴라의 우선 순위 큐인지 구분이 필요하다. 이는 맵을 사용해 Key에 정보 고릴라의 이름, Value를 해당 정보 고릴라의 정보를 담은 우선 순위 큐가 되도록 만들면 구현이 가능하다.

이를 구현하기 위해 Key로 문자열, Value로 Int를 받는 PriorityQueue를 받는 HashMap인 map을 정의하고 호석이가 정보를 사는 데 든 총 비용의 합을 저장할 answer 변수를 정의한다. 이 때 b가 최대 100,000이고 정보의 가치 C가 최대 100,000이므로 이 둘을 곱한 값이 Int의 범위를 넘기 때문에 answer 변수를 Long으로 정의해야 한다.

그 이후에 입력으로 들어오는 값을 확인해 이름이 Name인 정보 고릴라가 k개의 정보를 입수했다면 map[Name]에 우선 순위 큐가 있는지 확인해 없다면 `PriorityQueue<Int>(reverseOrder())`를 대입해 내림 차순으로 정리되는 우선 순위 큐를 먼저 만들어주고 해당 우선 순위 큐에 k개의 정보를 사는 비용을 넣으면 된다.

또한 호석이가 이름이 Name인 정보 고릴라에게 b개의 정보를 사도록 입력을 받았다면 map[Name]에 우선 순위 큐가 있는지 확인해 큐가 비거나 b개의 정보를 꺼낼때까지 정보를 꺼내 해당 정보의 비용을 answer에 더해주면 된다.

그렇게 모든 입력을 처리하고 나면 호석이가 정보를 사는 데 드는 비용의 총 합이 answer에 저장되므로 answer를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    val Q = readLine().toInt()
    val map = HashMap<String, PriorityQueue<Int>>()
    var answer = 0L
    for(i in 0 until Q){
        val st = StringTokenizer(readLine())
        val op = st.nextToken().toInt()
        if(op == 1){
            val name = st.nextToken()
            val k = st.nextToken().toInt()
            if(!map.contains(name)){
                map[name] = PriorityQueue<Int>(reverseOrder())
            }
            for(j in 0 until k){
                map[name]!!.add(st.nextToken().toInt())
            }
        } else {
            val name = st.nextToken()
            val b = st.nextToken().toInt()
            val pq = map[name]
            if(pq != null){
                for(j in 0 until b){
                    if(pq.isEmpty()) break
                    answer += pq.poll()
                }
            }
        }
    }
    println(answer)
}
```