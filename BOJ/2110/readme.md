> 문제: https://www.acmicpc.net/problem/2110

### 문제 풀이

이분 탐색 문제

집의 좌표를 오름차순으로 정렬해 저장

공유기 사이의 거리에 따라 놓을 수 있는 공유기의 갯수가 정해짐

공유기 사이의 거리를 이분 탐색함

좌표에 따라 정렬한 첫 집에 공유기를 놓고 그 뒤에 집을 살펴보며 이전에 공유기를 설치한 집까지의 거리가 현재 확인하는 거리보다 크면 공유기를 놓고 다음 집을 확인함

이렇게 놓은 공유기의 갯수가 C와 같게 되는 거리중 최댓값을 찾으면 정답

### 풀이 설명

문제에서 구하는 값이 공유기 사이의 거리이고, 집이 수직선 위에 나열되어 있는 형태이기 때문에 수직선 한쪽 끝의 집을 기준으로 잡고 공유기를 일정 거리 이상으로 놓기 시작한다면 기준 거리에 따른 공유기 갯수가 나오게 된다

이 때 공유기를 놓는 기준 거리를 크게 잡으면 공유기를 하나 설치할 때마다 더 먼 거리에 있는 집에 설치해야 하기 때문에 설치 가능한 갯수가 줄어들게 되고, 거리를 짧게 잡으면 반대로 더 가까운 집에도 설치할 수 있기 때문에 설치 가능한 갯수가 늘어나게 된다

즉 기준 거리에 따라 공유기 설치 갯수가 달라지기 때문에 문제 조건에 맞게 C개가 되는 기준 거리 중 최댓값을 구하면 문제에서 구하는 값이 된다

이분 탐색을 통해 기준에 맞는 값 중 최댓값을 구해야 하기 때문에 조건에 맞는 값이라면 더 큰 값의 범위를 확인해야 하고 중간값을 범위의 최솟값과 최댓값을 더한 값에 2를 나눈 몫으로 취하기 때문에 최솟값과 최댓값(코드에선 left, right)이 어긋날 때까지 범위를 줄여야 해당하는 모든 값을 확인할 수 있다

이 때 범위의 최댓값(코드에서 right)이 구하는 답이 된다

### 소스 코드
```kotlin
import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()

    var left = 1
    var right = 1000000000
    while(left <= right){
        val mid = (left + right) / 2
        var count = 1
        var prevIdx = 0
        for (i in 1 until N){
            if(arr[i] - arr[prevIdx] >= mid){
                count++
                prevIdx = i
            }
        }
        if(count >= C){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(right)
}
```