# 백준 1911번: 흙길 보수하기

> 문제: https://www.acmicpc.net/problem/1911

### 문제 풀이

그리디

웅덩이를 시작 위치 기준으로 오름차순 정렬 후 웅덩이마다 시작 지점 또는 현재 널빤지가 덮인 위치를 비교해 더 큰 값에서 웅덩이가 덮일때까지 널빤지를 덮음

이를 모든 웅덩이에 대해 반복해 덮은 널빤지의 개수가 정답

### 풀이 설명

N개의 물웅덩이가 있고 덮을 수 있는 길이가 L인 널빤지를 충분히 가지고 있을 때 물웅덩이를 모두 덮기 위해 필요한 널빤지의 최소 갯수를 구하는 방법은 불필요하게 널빤지를 사용하지 않는 것과 같다.

불필요하게 널빤지를 더 사용하지 않는 것은 이미 덮여있는 곳에 널빤지를 다시 덮지 않는 것이다.

```
111222..333444555.... // 길이 3인 널빤지
.MMMMM..MMMM.MMMM.... // 웅덩이
012345678901234567890 // 좌표
```

이 예시와 같은 상황이 있고 맨 앞의 물웅덩이부터 널빤지를 덮는 상황이라고 생각하면 두 번째 물웅덩이를 덮기 위해 두 개의 널빤지를 사용했을 때 세번째 물웅덩이의 일부분을 덮는다. 이때 불필요하게 다시 세 번째 물웅덩이의 시작 위치부터 널빤지를 덮기 시작하면 세 번째 물웅덩이를 덮기 위해 두개의 널빤지를 사용해야 한다. 따라서 불필요하게 사용되는 널빤지를 없애려면 맨 앞의 물웅덩이부터 순차적으로 널빤지를 덮으면서 현재 널빤지가 덮인 위치가 물웅덩이와 겹치는지 확인하면서 널빤지를 덮으면 된다.

이를 구현하기 위해 물웅덩이를 시작 위치를 기준으로 오름차순 정렬하고, 현재까지 덮인 널빤지의 끝 위치를 cur로 정의하고 이때 사용한 널빤지의 갯수를 answer로 정의한다. 맨 앞의 물웅덩이부터 현재까지 덮인 널빤지의 끝 위치와 물웅덩이의 시작 위치를 비교해 널빤지의 끝 위치가 시작 위치보다 작다면 시작 위치부터 널빤지를 덮어 널빤지를 낭비하지 않기 위해 cur 값을 물웅덩이의 시작 위치로 변경한다.

그 후에 cur 값에서 물 웅덩이의 끝 위치까지 덮어야 하는 널빤지의 갯수를 구하고 answer 값에 덮은 널빤지의 갯수를 더하고 그만큼 널빤지를 덮었을 때의 위치로 cur 값을 갱신한다. 이를 모든 물웅덩이에 대해 반복하면 answer에 모든 물웅덩이를 덮기 위한 널빤지의 최소 갯수가 저장되어 있으므로 이를 출력하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val L = st.nextToken().toInt()
    val arr = Array(N){ IntArray(2) }
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        arr[i] = intArrayOf(start, end)
    }
    arr.sortWith { o1, o2 -> o1[0] - o2[0] }
    var answer = 0
    var cur = 0
    for(i in 0 until N){
        if(cur < arr[i][0]){
            cur = arr[i][0]
        }
        if(cur >= arr[i][1]) continue
        val count = (arr[i][1] - cur) / L + if((arr[i][1] - cur) % L > 0) 1 else 0
        answer += count
        cur += count * L
    }
    println(answer)
}
```