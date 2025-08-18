# 백준 11509번: 풍선 맞추기

> 문제: https://www.acmicpc.net/problem/11509

### 문제 풀이

그리디

$arrows[i] =$ 높이가 $i$인 화살의 개수

풍선을 순서대로 확인해 해당 높이의 화살이 있으면 그 화살을 사용하고 없으면 화살 추가

추가한 화살의 개수가 필요한 총 화살의 개수이므로 출력하면 정답

### 풀이 설명

$N$개의 풍선을 터트리는 데 필요한 화살의 최소 개수를 구해야 한다. 화살을 왼쪽에서 오른쪽으로 쏘고, 풍선 하나를 터트리면 화살의 높이가 $1$ 줄어들지만 계속해서 가던 길을 간다고 했으므로 풍선을 터트릴 때 화살의 개수를 최소한으로 줄이려면 왼쪽에서 오른쪽으로 풍선을 살펴볼 때 이미 왼쪽의 풍선을 터트리기 위해 발사된 화살이 이 풍선을 터트릴 수 없을때만 화살을 새로 발사해야 한다는 뜻이다.

따라서 풍선을 왼쪽에서 오른쪽 순서로 살펴보면서 현재 풍선을 터트릴 수 있는 화살이 있는지 확인하기 위해 현재 높이에 있는 화살의 개수를 저장하는 배열 $arrows$를 만들고, 새로 발사하는 화살의 개수를 세기 위해 변수 $count$를 만든다. 풍선을 순서대로 확인할 때 풍선의 높이를 $balloon$이라고 하면 $arrows[balloon]$이 1 이상이라면 현재 풍선을 터트릴 수 있는 화살이 있는 것이므로 $arrows[balloon]$의 값을 1 줄이고 $arrows[balloon - 1]$의 값을 1 늘려 화살이 풍선을 터트리고 높이가 낮아진 것을 나타낸다.

만약 $arrows[balloon]$이 0이라면 이전 풍선을 터트리기 위해 발사되었던 화살이 이 풍선을 터트리지 못한다는 것이므로 새로운 화살을 발사하는 것이므로 $count$ 값을 1 증가시키고 이 풍선을 터트릴 화살을 새로 발사해 풍선을 터트린 것이므로 $arrows[balloon - 1]$의 값을 1 증가시켜 풍선을 터트리고 높이가 낮아진 화살을 배열에 반영한다.

이에 따라 모든 풍선을 확인하면 $count$에 새로 발사된 화살의 개수가 저장되어 있으므로 이 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val st = StringTokenizer(readLine())
    val arrows = IntArray(1000001){0}
    var count = 0
    repeat(N){
        val balloon = st.nextToken().toInt()
        if(arrows[balloon] > 0){
            arrows[balloon]--
            arrows[balloon - 1]++
        } else {
            arrows[balloon - 1]++
            count++
        }
    }
    println(count)
}
```