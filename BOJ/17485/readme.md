# 백준 17485번: 진우의 달 여행 (Large)

> 문제: https://www.acmicpc.net/problem/17485

### 문제 풀이

DP

prev[k][j] = 이전 행의 k 방향으로 j칸에 도착했을 때의 최소 비용

curr[k][j] = 현재 행의 k 방향으로 j칸에 도착했을 때의 최소 비용

k가 0일 때 왼쪽 위 칸에서 현재 칸으로, 1일 때 위 칸에서 현재 칸으로, 2일 때 오른쪽 위 칸에서 현재 칸으로 이동한 것

첫째 행부터 N번째 행까지 현재 방향을 제외한 방향으로 이동했을때의 최소 비용 + 현재 칸의 비용을 계산해 curr에 저장하면 배열에 마지막 칸까지 도착하는 최소 비용이 저장됨

이 값들 중 최솟값을 출력하면 정답

### 풀이 설명

지구와 우주사이는 N X M 행렬로 나타낼 수 있으며 각 원소의 값은 우주선이 그 공간을 지날 때 소모되는 연료의 양이고, 우주선은 왼쪽 아래, 아래, 오른쪽 아래 방향으로 움직일 수 있으며 연속으로 같은 방향으로 움직일 수 없다고 했을 때 달에 도달할 수 있는 연료의 최소값을 구하려면 우주선이 매번 아래칸으로 움직이기 때문에 이전 칸까지의 비용에 현재 칸의 비용을 더해서 현재 칸까지의 연료 양을 구하면 구할 수 있다.

따라서 이전 칸까지의 방향에 따라 드는 최소 연료양을 저장하기 위해 prev 배열을 선언하고, 이 이전 칸에서 현재 칸까지의 최소 비용을 구하기 위해 curr 배열을 만든다.

처음에는 이전 칸이 없으므로 prev 배열을 0으로 초기화 해놓고 curr 배열도 0으로 초기화 해놓는다. 한 줄씩 입력 받으면서 curr 배열에 저장된 값을 prev에 복사해 이전 칸까지의 최소 비용을 저장하고 칸에 따라 어떤 칸에서 이동해야 최소 비용으로 올 수 있는지 계산한다.

j가 0이라면 맨 왼쪽 칸이므로 왼쪽 위 칸이 없기 때문에 위쪽 칸과 오른쪽 위 칸에서 올 수밖에 없다. 따라서 현재 방향과 일치하지 않도록 prev 배열에 저장된 값을 확인해 최솟값을 가져오고 거기에 현재 칸의 비용을 더해 현재 칸의 방향에 따른 최소 비용을 저장한다.

j가 N - 1이라면 맨 오른쪽 칸이므로 오른쪽 위 칸이 없기 때문에 왼쪽 위 칸과 위쪽 칸에서만 이동할 수 있다. 나머지의 경우는 왼쪽 위, 위, 오른쪽 위 칸에서 모두 이동 가능하기 때문에 현재 방향과 일치하지 않도록 prev 배열에서 값을 가져와 현재 칸의 비용을 더해 현재 칸의 방향에 따른 최소 비용을 저장해준다.

이를 모든 줄에 대해 반복하면 curr 배열에 마지막 줄에 도착하는 최소 비용들이 저장된다. 이 중 최솟값이 지구에서 달까지 드는 최소 연료 양이므로 이를 출력하면 정답이다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val prev = Array(3){ IntArray(M) }
    val curr = Array(3){ IntArray(M) }
    for(i in 0 until N){
        for(k in 0 until 3){
            curr[k].copyInto(prev[k])
        }
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            val cost = st.nextToken().toInt()
            if(j == 0){
                curr[0][j] = minOf(prev[1][j], prev[2][j + 1]) + cost
                curr[1][j] = prev[2][j + 1] + cost
                curr[2][j] = prev[1][j] + cost
            } else if(j == M - 1){
                curr[0][j] = prev[1][j] + cost
                curr[1][j] = prev[0][j - 1] + cost
                curr[2][j] = minOf(prev[0][j - 1], prev[1][j]) + cost
            } else {
                curr[0][j] = minOf(prev[1][j], prev[2][j + 1]) + cost
                curr[1][j] = minOf(prev[0][j - 1], prev[2][j + 1]) + cost
                curr[2][j] = minOf(prev[0][j - 1], prev[1][j]) + cost
            }
        }
    }
    var answer = Int.MAX_VALUE
    for(k in 0 until 3) {
        for (j in 0 until M) {
            answer = minOf(answer, curr[k][j])
        }
    }
    println(answer)
}
```