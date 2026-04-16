# 백준 1117번: 색칠 1

> 문제: https://www.acmicpc.net/problem/1117

### 문제 풀이

수학

answer = 페인트가 칠해지지 않은 종이의 영역

doubleX = 가로로 접었을 때 종이가 두 장 겹쳐져있는 좌표

세로로는 기본적으로 c + 1장이 겹쳐져 있음

가로로는 f를 기준으로 종이를 접기 때문에 f와 W - f중 짧은 길이까지만 2장이 겹쳐져 있음

따라서 이 기준을 잡기 위해 doubleX를 만들어 f와 W - f중 더 작은 값을 저장함

문제 자체는 전체 영역에서 색칠된 영역을 빼야 색칠되지 않은 영역을 구할 수 있으므로 answer를 전체 영역 W * H로 초기화함

그 이후에 doubleX와 x1을 비교해 가로로 두 장이 겹쳐져 있는 영역이 직사각형 내에 있는지 먼저 비교함

doubleX가 x1보다 작으면 가로로 두 장이 겹쳐져 있는 영역이 색칠되지 않으므로 직사각형의 크기 (x2 - x1) * (y2 - y1)에 세로로 겹쳐져 있는 장 수 (c + 1)을 곱한 영역을 answer에서 빼주면 됨

doubleX가 x1보다 크거나 같으면 doubleX를 x2와 비교해 가로로 2장 겹친 영역이 직사각형의 중간에서 끝나는지 확인해야 함

doubleX가 x2보다 작다면 가로로 두장으로 겹친 영역 (doubleX - x1) * (y2 - y1)에는 세로로 겹친 장 수 (c + 1)에 가로로 겹친 장 수 2를 곱한 크기를 answer에서 빼주어야 하고 가로로 종이가 겹치지 않은 영역 (x2 - doubleX) * (y2 - y1)에 세로로 겹친 장 수 (c + 1)을 곱한 영역을 answer에서 빼주어야 함

만약 doubleX가 x2보다 크거나 같다면 직사각형 전체 영역이 가로로 2장 겹쳐져 있는 것이므로 직사각형의 영역 (x2 - x1) * (y2 - y1)에 세로로 겹친 장 수 (c + 1)을 곱하고 가로로 겹친 장 수 2를 곱한 영역의 크기를 answer에서 빼주면 됨

그러면 answer에 전체 영역에서 색칠된 영역의 크기를 뺐으므로 색칠되지 않은 영역의 크기가 저장되어 있으므로 출력하면 정답

### 풀이 설명

W×H 크기의 직사각형 종이가 있다. 종이를 x가 f인 곳에서 왼쪽 종이를 오른쪽 종이의 위로 오게 접는다. 그 이후에 종이를 가로로 c + 1개의 크기가 동일한 구간으로 나누고 c번 가장 위의 구간부터 접는다.

접힌 상태에서 가장 왼쪽 아래를 (0, 0)으로 잡을 때 왼쪽 아래가 (x1, y1) 오른쪽 위가 (x2, y2)인 직사각형을 칠하면 겹쳐있는 모든 곳에 페인트가 스며든다. 이때 칠해지지 않은 면적을 구하는 문제이다.

아래 그림은 5x6 크기의 종이가 f가 2, c가 2일 때 (1, 1), (3, 2)의 직사각형을 칠하는 예이다.

![](https://velog.velcdn.com/images/kosdjs/post/77506564-fa65-47e0-bbcf-0725aeccaa9a/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/77a66b46-484e-4af9-bced-03ec579f8d18/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/993503d9-b037-4ab3-8483-abbb401c0ebe/image.png)
-|-|-
![](https://velog.velcdn.com/images/kosdjs/post/8af1d630-ccff-413a-b9eb-73126998ef1c/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/81853da5-5812-4251-970b-760fdafae50f/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/b890161d-57fc-4800-be4d-d3ccf06eeb29/image.png)

일단 다시 그림을 보면서 설명을 하자면 x = f인 직선을 기준으로 종이를 왼쪽, 오른쪽으로 나눈 후 왼쪽 종이가 오른쪽 종이 위로 오도록 접는다.

그 이후에 접힌 종이를 문제에서는 가로로 c + 1개의 크기가 동일한 구간이라고 설명하지만 더 일상적인 표현으로 생각하면 세로로 c + 1등분하는 것으로 보면 된다. 이에 따라 c + 1등분만큼 맨 위에서부터 c번 접어 종이를 4번째 그림처럼 만든다.

5번째 그림에서 하얀 면적이 페인트를 칠하는 직사각형이고 6번째 그림에서 하얀 부분이 페인트가 칠해진 부분이고, 파란 면적이 우리가 구해야 하는 칠해지지 않은 면적이다.

즉, 이 문제는 전체 영역에서 페인트가 칠해진 영역을 뺀 영역을 구하는 문제이다. 그러므로 (x1, y1)과 (x2, y2) 사이의 칠해지는 영역이 정확히 몇 칸이 겹쳐있는지를 구해야 한다.

칸이 겹쳐있는 것은 x = f인 직선을 기준으로 가로로 접은 점, 세로로 c번 접은 점에 영향을 받는다. 먼저 c번 접은 점은 동일한 구간으로 나누어 c번 접은 것이므로 기본적으로 c + 1칸이 겹쳐져 있다고 볼 수 있다.

x = f인 직선을 기준으로 가로로 접었을 때는 위로 접히는 왼쪽 종이의 길이가 f, 아래에 깔리는 오른쪽 종이의 길이가 W - f이므로 둘 중 짧은 길이까지만 종이가 2장 겹치게 된다. 따라서 0부터 2장이 겹치는 짧은 길이를 나타내기 위해 doubleX라는 변수를 만들어 f와 W - f 중 최솟값을 저장하면 된다.

그러면 그 이후에는 색칠하는 직사각형의 좌표가 x1부터 시작하므로 이 x1이 doubleX보다 크다면 가로로 2장이 겹쳐져있는지 확인할 필요 없이 직사각형의 영역에 세로로 겹쳐있는 c + 1장만 생각해 영역을 계산하면 된다.

이럴때는 바로 직사각형의 영역 (x2 - x1) * (y2 - y1)에 세로로 겹쳐진 장 수인 (c + 1)만 곱해주면 색칠된 영역의 크기를 구할 수 있으므로 이를 종이의 전체 면적이 저장된 answer에서 뺀 값을 저장하면 된다.

만약 doubleX가 x1보다 크거나 같다면 doubleX가 x2보다 작은지 여부를 확인해 색칠하는 직사각형이 어디까지 가로로 2장이 겹쳐있는지 확인해야 한다.

doubleX가 x2보다 작다면 가로로 2장이 겹쳐있는 영역이 x1부터 doubleX까지이고, doubleX부터 x2까지는 가로로 겹쳐있는 종이가 없게 되는 것이므로 전체 영역 answer에서 가로로 2장 겹친 영역의 크기 (doubleX - x1) * (y2 - y1) * (c + 1) * 2와 가로로 겹쳐있지 않은 영역의 크기 (x2 - doubleX) * (y2 - y1) * (c + 1)를 따로 계산해서 빼주어야 한다.

만약 doubleX가 x2보다 크거나 같다면 직사각형 전체가 가로로 2장이 겹쳐있는 것이므로 직사각형 전체 영역 (x2 - x1) * (y2 - y1)에 세로로 겹쳐진 장수 (c + 1)를 곱하고 이에 추가로 가로로 겹쳐진 2장을 곱한 영역을 전체 영역 answer에서 빼주면 된다.

이렇게 색칠된 영역을 빼준 이후에 answer에는 색칠되지 않은 영역의 크기가 저장되어 있으므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val W = nextLong()
    val H = nextLong()
    val f = nextLong()
    val c = nextLong()
    val x1 = nextLong()
    val y1 = nextLong()
    val x2 = nextLong()
    val y2 = nextLong()
    var answer = W * H
    val doubleX = minOf(f, W - f)
    if(doubleX > x1){
        if(doubleX < x2){
            answer -= (doubleX - x1) * (y2 - y1) * (c + 1) * 2
            answer -= (x2 - doubleX) * (y2 - y1) * (c + 1)
        } else {
            answer -= (x2 - x1) * (y2 - y1) * (c + 1) * 2
        }
    } else {
        answer -= (x2 - x1) * (y2 - y1) * (c + 1)
    }
    println(answer)
}
```