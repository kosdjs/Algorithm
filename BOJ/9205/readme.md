# 백준 9205번: 맥주 마시면서 걸어가기

> 문제: https://www.acmicpc.net/problem/9205

### 문제 풀이

BFS

map[0] = 상근이네 집 좌표

map[n + 1] 펜타포트 락 페스티벌 좌표

한번 이동할 때 1000미터 이동 가능

BFS로 상근이네 집 좌표에서 한번 이동할 때 1000미터 이하의 거리만 이동해서 펜타포트 락 페스티벌 좌표로 이동할 수 있는지 판별해 가능하면 happy, 불가능하면 sad 출력하면 정답

### 풀이 설명

한 박스에 맥주가 20병 들어갈 수 있고, 편의점에서 맥주를 다시 구매해도 20병을 넘게 들 수 없고 50미터에 한 병씩 마시기 때문에 최대 이동 거리는 1000미터이다.

따라서 상근이네 집 좌표에서 최대 1000미터 떨어진 거리까지만 이동하면서 펜타포트 락 페스티벌 좌표로 이동할 수 있는지 확인하면 된다.

이를 위해서 좌표를 저장하기 위해 map 배열을 만들어 입력 순서대로 상근이네 집, 편의점, 펜타포트 락 페스티벌의 좌표를 입력받는다.

그러면 상근이네 집 좌표가 map[0]에, map[1] 부터 map[n]까지에 편의점, map[n + 1]에 펜타포트 락 페스티벌의 좌표가 들어간다.

상근이네 집에서 펜타포트 락 페스티벌까지 가야 하는 것이므로 갔던 좌표는 다시 돌아갈 필요가 없다. 따라서 visit 배열을 만들어 이 장소를 이미 도착했었는지 표시한다.

이제 BFS를 하기 위해 queue를 선언하고 상근이네 집 좌표인 map[0]을 넣고 visit[0]에 true를 대입해 장소 도착 처리를 한다. queue가 빌때까지 좌표를 꺼내 이미 방문한적이 없고 거리가 1000미터 이내인 장소들을 방문처리하고 큐에 집어넣는다.

이를 통해 상근이네 집 좌표에서 갈 수 있는 모든 좌표를 확인하면 펜타포트 락 페스티벌을 도착할 수 있는지가 visit[n + 1]에 저장되게 된다. 따라서 이 값을 확인해 true라면 편의점을 들리면서 도착할 수 있다는 것이므로 happy를 출력하고 false라면 불가능 한 것이므로 sad를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    repeat(t){
        val n = br.readLine().toInt()
        val map = Array(n + 2){ IntArray(2) }
        for(i in 0 until n + 2){
            val st = StringTokenizer(br.readLine())
            for(j in 0..1){
                map[i][j] = st.nextToken().toInt()
            }
        }
        val visit = BooleanArray(n + 2)
        visit[0] = true
        val queue = ArrayDeque<IntArray>()
        queue.add(map[0])
        while (queue.isNotEmpty()){
            val (x, y) = queue.removeFirst()
            for(i in 0 until n + 2){
                if(!visit[i]){
                    if(abs(map[i][0] - x) + abs(map[i][1] - y) <= 1000){
                        visit[i] = true
                        queue.add(map[i])
                    }
                }
            }
        }
        if(visit[n + 1]){
            println("happy")
        } else {
            println("sad")
        }
    }
}
```