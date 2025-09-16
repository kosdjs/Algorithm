# 백준 16929번: 배열 돌리기 1

> 문제: https://www.acmicpc.net/problem/16926

### 문제 풀이

구현

배열을 각 층마다 데크로 변환하고 회전 횟수만큼 데크의 앞에서 원소를 빼 다시 뒤에 넣음

회전한 데크를 다시 배열로 변환해서 출력하면 정답

### 풀이 설명

```
A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
   ↓                                       ↑
A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
   ↓         ↓                   ↑         ↑
A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
   ↓                                       ↑
A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]
```

배열이 반시계방향으로 회전할 때 예시처럼 사이클이 발생하고 사이클이 테두리에서 중심층까지 층을 이루는 것을 알 수 있다. 배열이 4x5 배열이므로 층이 2개로 나뉘는 것을 알 수 있다. 즉, NxM 배열이 있으면 min(N, M)을 2로 나눈 층만큼의 사이클이 생긴다는 것을 알 수 있다.

배열이 회전할 때 사이클에 있는 원소끼리 회전하기 때문에 이를 구현하기 위해서 사이클을 데크로 변환하고 회전 횟수 R만큼 데크의 앞에서 원소를 빼서 데크의 뒤에 넣어 회전을 처리한다. 여기서 데크에 들어간 원소의 크기 deque.size 만큼 회전을 하면 원래 상태로 돌아오기 때문에 R을 이 값으로 나눈 나머지만큼만 회전처리를 하면 된다.

이렇게 회전 처리를 한 데크에 있는 원소들을 다시 배열에 배치하면 회전 처리를 할 수 있다. 이렇게 처리를 한 배열을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val R = st.nextToken().toInt()
    val arr = Array(N){ IntArray(M) }
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            arr[i][j] = st.nextToken().toInt()
        }
    }
    val deque = ArrayDeque<Int>()
    for(i in 0 until minOf(N, M) / 2){
        arrayToDeque(i, arr, deque)//배열을 데크로 변환
        repeat(R % deque.size){//데크 회전 처리
            deque.addLast(deque.removeFirst())
        }
        dequeToArray(i, arr, deque)//데크를 다시 배열로 변환
    }
    val sb = StringBuilder()
    for(i in 0 until N){
        for(j in 0 until M){
            sb.append("${arr[i][j]} ")
        }
        sb.append("\n")
    }
    print(sb.toString())
}

fun arrayToDeque(layer: Int, arr: Array<IntArray>, deque: ArrayDeque<Int>){
    var x = layer
    var y = layer
    while(x < arr[y].size - layer){
        deque.addLast(arr[y][x])
        x++
    }
    x--
    y++
    while(y < arr.size - layer){
        deque.addLast(arr[y][x])
        y++
    }
    y--
    x--
    while(x >= layer){
        deque.addLast(arr[y][x])
        x--
    }
    x++
    y--
    while(y > layer){
        deque.addLast(arr[y][x])
        y--
    }
}

fun dequeToArray(layer: Int, arr: Array<IntArray>, deque: ArrayDeque<Int>){
    var x = layer
    var y = layer
    while(x < arr[y].size - layer){
        arr[y][x] = deque.removeFirst()
        x++
    }
    x--
    y++
    while(y < arr.size - layer){
        arr[y][x] = deque.removeFirst()
        y++
    }
    y--
    x--
    while(x >= layer){
        arr[y][x] = deque.removeFirst()
        x--
    }
    x++
    y--
    while(y > layer){
        arr[y][x] = deque.removeFirst()
        y--
    }
}
```