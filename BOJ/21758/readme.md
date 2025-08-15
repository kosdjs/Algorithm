# 백준 21758번: 꿀 따기

> 문제: https://www.acmicpc.net/problem/21758

### 문제 풀이

그리디

다음 세가지 경우를 계산해서 최댓값을 찾음

1. 맨 왼쪽 장소에 벌이 시작하는 장소, 맨 오른쪽 장소에 벌이 시작하는 장소, 그 사이에 벌집

2. 맨 왼쪽 장소가 벌이 시작하는 장소, 맨 오른쪽 장소에 벌집, 그 사이에 벌이 시작하는 장소

3. 맨 왼쪽 장소에 벌집, 맨 오른쪽 장소에 벌이 시작하는 장소, 그 사이에 벌이 시작하는 장소

찾은 최댓값이 정답

### 풀이 설명

1부터 N까지 번호가 붙은 장소에서 세 곳을 정해 두 곳에 벌, 한 곳에 벌집을 놓았을 때 벌이 딸 수 있는 꿀의 양을 최대로 하려면 두 벌이 벌집까지 가는 경로에 있는 장소에서 처음 벌이 있었던 장소를 제외한 장소마다 꿀을 딴다고 했으므로 전체 장소에서 벌이 벌집까지 가는 경로에 있는 장소에서 처음 벌이 있던 장소를 제외한 장소들의 꿀의 합이 된다.

이를 다시 생각해보면 장소에 있는 꿀의 양이 고정되어있고, 벌집에 가는 경로에 있는 장소에 있는 꿀을 더한 값들의 최댓값을 구하는 것이므로 최대한 모든 장소가 벌집에 가능 경로가 되어야 한다.

따라서 최대한 모든 장소가 벌이 꿀을 딸 수 있는 장소가 되게 하려면 장소가 벌이 시작하는 장소, 벌집인 장소, 벌이 벌집까지 가는 경로에 있는 장소만 있어야 한다. 따라서 모든 장소가 이런 장소가 되게 하려면 양쪽 맨 끝의 장소가 항상 벌이 시작하는 장소이거나 벌집인 장소이어야 한다.

정확히 경우의 수를 나누면 다음과 같은 세가지 경우가 나온다.

1. 맨 왼쪽 장소에 벌이 시작하는 장소, 맨 오른쪽 장소에 벌이 시작하는 장소, 그 사이에 벌집

2. 맨 왼쪽 장소가 벌이 시작하는 장소, 맨 오른쪽 장소에 벌집, 그 사이에 벌이 시작하는 장소

3. 맨 왼쪽 장소에 벌집, 맨 오른쪽 장소에 벌이 시작하는 장소, 그 사이에 벌이 시작하는 장소

![](https://velog.velcdn.com/images/kosdjs/post/bc956a6f-cc5d-4e23-bb5e-f759aeaa029b/image.png)

첫 번째 경우를 그림으로 나타내면 위와 같이 나타낼 수 있다. 위에 나타낸 만큼이 왼쪽 벌이 따는 꿀, 아래에 나타낸 만큼이 오른쪽 벌이 따는 꿀이라고 하면 알 수 있듯이 벌이 시작한 장소의 꿀은 따지 않고, 벌집인 장소의 꿀을 두 번 따기 때문에 이때의 값은 모든 장소의 꿀의 합에서 두 벌이 시작한 장소의 꿀의 양을 빼고 벌집인 장소의 꿀의 양을 더한 값이 된다.

![](https://velog.velcdn.com/images/kosdjs/post/b9775422-e56b-408f-ac0d-0d005f1f233e/image.png)

두 번째 경우를 그림으로 나타내면 위와 같이 나타낼 수 있다. 위에 나타낸 만큼이 왼쪽 벌이 따는 꿀, 아래에 나타낸 만큼이 오른쪽 벌이 따는 꿀이라고 하면 왼쪽 벌이 따는 꿀은 모든 장소에서 양쪽 벌이 시작한 장소를 제외한 꿀의 합이고, 오른쪽 벌은 오른쪽 벌이 시작한 장소의 오른쪽에 있는 모든 장소의 꿀의 합이다. 이를 구하기 쉽게 하기 위해 오른쪽 벌을 왼쪽 벌의 바로 옆에서 벌집 옆까지의 순서로 확인하면 왼쪽 벌이 따는 꿀은 항상 모든 장소의 합에서 두 벌이 시작하는 장소의 꿀만 빼면 구할 수 있고, 오른쪽 벌은 모든 장소의 합에서 왼쪽 벌이 시작하는 장소의 꿀의 양을 빼놓은 값에서 오른쪽 벌이 시작하는 장소의 값만 빼주면 오른쪽 벌이 따는 꿀을 쉽게 구할 수 있다. 이에 따라 값을 구하면 된다.

![](https://velog.velcdn.com/images/kosdjs/post/f02145e6-69bd-4614-89c5-1ae418e64b8e/image.png)

세 번째 경우 두 번째 경우에서 좌우 반전된 상황이므로 왼쪽 벌을 오른쪽 벌의 옆에서 시작해 벌집 옆까지 반복해서 값을 구해주면 된다.

이렇게 구한 모든 값의 최댓값을 구하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = IntArray(N)
    val st = StringTokenizer(br.readLine())
    var sum = 0L
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
        sum += arr[i]
    }
    var answer = 0L
    var left = 0
    var right = N - 1
    var pot = 1
    for(pot in left + 1 until right){
        answer = maxOf(answer, sum - arr[left] - arr[right] + arr[pot])
    }
    right = 1
    pot = N - 1
    var rightSum = sum - arr[left]
    for(right in left + 1 until pot){
        rightSum -= arr[right]
        answer = maxOf(answer, sum - arr[left] - arr[right] + rightSum)
    }
    pot = 0
    right = N - 1
    var leftSum = sum - arr[right]
    for(left in right - 1 downTo pot + 1){
        leftSum -= arr[left]
        answer = maxOf(answer, sum - arr[left] - arr[right] + leftSum)
    }
    println(answer)
}
```