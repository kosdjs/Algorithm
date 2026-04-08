# 백준 30678번: 별 안에 별 안에 별 찍기

> 문제: https://www.acmicpc.net/problem/30678

### 문제 풀이

재귀

size[i] = i 단계 별의 가로 크기

result[i][j] = 출력을 줄과 칸으로 나눴을 때 좌표 (i, j)에 출력해야 하는 문자

star(i, j, count) = count 단계의 별을 좌표 (i, j)에 그리는 재귀 함수

N을 입력받으면 배열 size를 정의하고 size[0]에 1을 저장한 이후 size[1]부터 size[N]까지 배열의 이전 값에 5를 곱한 값을 저장해 해당 단계의 별의 가로 크기를 저장함

이후 별의 패턴에 맞게 하위 단계의 재귀 함수를 호출하도록 star(i, j, count)를 정의함

이때 0단계는 result[i][j]에 \* 을 저장하고 반환하게 만듬

그러면 문제에서 요구하는 N 단계 별은 star(0, 0, N)을 호출하면 result에 결과가 저장되므로 이를 출력하면 정답

### 풀이 설명

![](https://velog.velcdn.com/images/kosdjs/post/6f219eb2-01fe-4242-9cf5-2bad471fe993/image.png)

위는 $Star_i$ 의 패턴이고 $Star_0$은 \* 이 하나 찍힌다고 했을 때 주어지는 N에 대해 $Star_N$을 출력해야 하는 문제이다.

패턴에서 볼 수 있듯이 현재 단계의 별은 이전 단계의 별로 나뉘게 되므로 분할 정복으로 문제를 풀 수 있다. 이에 따라 별을 찍는 재귀 함수를 만들어 분할 정복을 구현할 수 있다.

이를 재귀 함수로 구하기 위해 인수로 현재 단계와, 별이 그려질 위치 정보를 나타내야 하는데 이를 해결하기 위해 전체 출력 영역을 2차원 배열로 선언하고 영역의 맨 왼쪽 위 칸의 좌표가 (0, 0)인 좌표계에서의 현재 별의 좌상단 좌표를 위치 정보로 넘기게 처리했다.

또한 전체 출력 영역을 2차원 배열로 정의한다고 했는데 이 문제에서 출력을 할 때 \* 이 찍히는 위치 외에는 모두 공백이므로 배열의 초깃값으로 공백을 저장하도록 했다.

재귀 함수의 인수로 현재 별의 좌상단 좌표를 넘긴다고 했으므로 안에 들어가는 한 단계 작은 별의 좌상단 좌표 위치를 구하는 방법이 필요하다. 이는 패턴이 현재 별을 가로, 세로로 5등분씩 하기 때문에 단계별 별의 크기를 미리 구해놓고 현재 별의 좌상단 좌표에서 별의 크기만큼 더해서 구하면 해결된다.

단계별 별의 크기는 size라는 배열을 만들고 이 배열의 인덱스가 별의 단계이고 값을 해당 단계의 별의 가로 크기로 지정해 size[0]에 1을 저장하고 size[N]까지 배열의 이전 값에 5를 곱한 값을 저장하도록 반복문을 돌리면 별의 크기를 미리 구해놓을 수 있다.

이렇게 패턴에 맞게 하위 단계의 재귀 함수를 계속 호출하다보면 문제에서 \* 을 찍어야 하는 0단계의 재귀 함수가 호출되게 되는데 이 때 좌상단 좌표 (i, j)가 들어오므로 출력 배열의 해당 위치인 result[i][j]에 \* 을 저장하고 함수를 반환하면 된다.

그렇게 count 단계의 별의 좌상단 좌표가 (i, j)일 때 별을 그리는 재귀함수 star(i, j, count)를 완성하고 star(0, 0, N)을 호출해 N 단계의 별을 result에 그리면 문제에서 요구하는 N 단계의 별이 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    val size = IntArray(N + 1)
    size[0] = 1
    for(i in 1..N){
        size[i] = size[i - 1] * 5
    }
    val result = Array(size[N]){CharArray(size[N]){' '}}
    fun star(i: Int, j: Int, count: Int){
        if(count == 0){
            result[i][j] = '*'
            return
        }
        val s = size[count - 1]
        star(i, j + s * 2, count - 1)
        star(i + s, j + s * 2, count - 1)
        for(t in 0 until 5) star(i + s * 2, j + s * t, count - 1)
        for(t in 1 until 4) star(i + s * 3, j + s * t, count - 1)
        for(t in 1 until 4 step 2) star(i + s * 4, j + s * t, count - 1)
    }
    star(0, 0, N)
    val sb = StringBuilder()
    for(i in 0 until result.size){
        sb.append(result[i]).append("\
")
    }
    print(sb)
}
```