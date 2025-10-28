# 백준 2170번: 선 긋기

> 문제: https://www.acmicpc.net/problem/2170

### 문제 풀이

정렬

선의 두 좌표 x, y가 있을 때 x, y를 기준으로 오름차순으로 정렬함

그리고 현재 선을 start, end라고 생각했을 때 x가 start와 end 사이에 있으면 선 x, y가 현재 선에 겹치는 것이므로 end와 y를 비교해 더 큰 값을 end에 저장해 겹친 선의 끝 좌표를 저장함

선이 겹치지 않을 때 선의 길이를 answer에 저장하고 새로운 좌표를 start, end에 저장함

모든 선에 대해서 반복하고 마지막에 start, end까지의 선의 길이를 answer에 저장하고 출력하면 정답

### 풀이 설명

선을 그을 때에는 자의 한 점에서 다른 한 점까지 긋게 된다. 선을 그을 때에는 이미 선이 있는 위치에 겹쳐서 그릴 수도 있는데, 여러 번 그은 곳과 한 번 그은 곳의 차이를 구별할 수 없다고 하자.

이와 같은 식으로 선을 그었을 때, 그려진 선(들)의 총 길이를 구하는 프로그램을 작성하시오. 선이 여러 번 그려진 곳은 한 번씩만 계산한다.

첫째 줄에 선을 그은 횟수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 다음 N개의 줄에는 선을 그을 때 선택한 두 점의 위치 x, y (-1,000,000,000 ≤ x < y ≤ 1,000,000,000)가 주어진다.

문제에서 매우 큰 도화지에 자를 대고 선을 긋는다고 해서 좌표를 보는게 이상하다고 생각했는데, 이를 무시하고 일직선 상에서 두 좌표 사이의 선을 긋는다고 생각하면 된다.

입력에서 항상 x가 y보다 작다고 했으므로 x가 선이 시작하는 좌표이고 y가 선이 끝나는 좌표라고 생각할 수 있다. 좌표 start, end 사이의 선과 좌표 x, y 사이의 선이 겹치는지 확인하는 방법은 x가 start, end 사이에 있는지 확인하면 된다.

그리고 길이를 재려면 겹치는 선은 한 번만 계산한다고 했으므로 현재 선과 겹치는 모든 선을 찾아서 재야 한다. 겹치는 선을 선의 시작좌표 x로 확인한다고 했으므로 선을 x를 기준으로 정렬해 현재 확인하는 선과 겹치지 않을때 까지 확인하면 최종적으로 그려지는 선의 길이를 구할 수 있다.

따라서 선을 x, y 기준으로 오름차순으로 정렬하고 현재까지 겹친 전체 선의 시작 좌표, 끝 좌표를 start, end에 저장하고 현재 확인하는 선의 좌표를 x, y라고 할 때 x가 start와 end 사이인지 확인한다. 사이에 있다면 겹치는 선이므로 end를 y와 비교해 더 큰 값을 end에 저장해 현재 확인한 선까지를 전체 선에 반영을 한다.

start, end 사이에 없다면 겹친 전체 선을 찾은 것이므로 그 길이인 end - start를 answer에 저장한다. 이를 모든 선에 대해 반복하면 마지막까지 확인한 길이를 아직 반영하지 않았으므로 이 길이도 answer에 더해 모든 선의 길이를 저장한다. 이 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val lines = Array(N){ IntArray(2) }
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        for(j in 0..1){
            lines[i][j] = st.nextToken().toInt()
        }
    }
    lines.sortWith{o1, o2 -> if(o1[0] - o2[0] != 0) o1[0] - o2[0] else o1[1] - o2[1]}
    var start = lines[0][0]
    var end = lines[0][1]
    var answer = 0
    for(i in 1 until N){
        val x = lines[i][0]
        val y = lines[i][1]
        if(x >= start && x <= end){
            end = maxOf(end, y)
        } else {
            answer += end - start
            start = x
            end = y
        }
    }
    answer += end - start
    println(answer)
}
```