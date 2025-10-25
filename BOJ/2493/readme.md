# 백준 2493번: 탑

> 문제: https://www.acmicpc.net/problem/2493

### 문제 풀이

스택

stack = 현재 탑보다 왼쪽에 있는 탑 중 높이가 더 높은 탑들

answer = 신호를 받는 탑의 번호

가장 왼쪽 탑부터 다음 단계를 반복함

1. 스택이 비어있지 않다면 스택의 상단부터 확인해 현재 탑보다 높이가 낮거나 같은 탑들을 전부 스택에서 뺌

2. 스택에 탑이 남아있다면 그 탑이 현재 탑의 신호를 받는 탑이므로 탑의 번호를 answer에 저장

3. 현재 탑을 스택에 넣음

answer를 출력하면 정답

### 풀이 설명

일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다.

탑의 높이가 모두 다르기 때문에 현재 탑의 신호를 받는 탑은 현재 탑보다 왼쪽에 있는 탑 중에서 현재 탑보다 높이가 높고 가장 가까운 탑이 된다.

스택이 먼저 들어간 원소가 가장 나중에 나온다는 점을 이용해 가장 왼쪽 탑부터 넣으면서 현재 탑보다 높이가 낮거나 같은 탑을 위에서부터 꺼내면 스택에 남은 탑이 현재 탑보다 왼쪽에 있는 탑 중에서 현재 탑보다 높이가 높고 가장 가까운 탑이 된다. 따라서 다음 단계를 가장 왼쪽 탑부터 반복하면 답을 구할 수 있다.

1. 스택이 비어있지 않다면 스택의 상단부터 확인해 현재 탑보다 높이가 낮거나 같은 탑들을 전부 스택에서 뺌

2. 스택에 탑이 남아있다면 그 탑이 현재 탑의 신호를 받는 탑이므로 탑의 번호를 answer에 저장

3. 현재 탑을 스택에 넣음

이렇게 신호를 받는 탑의 번호를 저장한 answer 배열을 출력 형식에 맞게 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val answer = IntArray(N)
    val st = StringTokenizer(br.readLine())
    val stack = Array(N){ IntArray(2) }
    var top = -1
    for(i in 0 until N){
        val tower = st.nextToken().toInt()
        while(top >= 0 && stack[top][0] <= tower){
            top--
        }
        if(top >= 0){
            answer[i] = stack[top][1] + 1
        }
        top++
        stack[top][0] = tower
        stack[top][1] = i
    }
    val bw = System.out.bufferedWriter()
    for(i in 0 until N){
        bw.write("${answer[i]} ")
    }
    bw.flush()
    bw.close()
}
```