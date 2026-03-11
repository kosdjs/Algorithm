# 백준 2118번: 두 개의 탑

> 문제: https://www.acmicpc.net/problem/2118

### 문제 풀이

투 포인터

arr[i] = N번 지점부터 i번 지점까지 시계 방향으로 가는 경로의 길이

answer = 두 지점 사이의 경로 중 최단 거리의 최댓값

두 지점 left, right 사이의 시계 방향 경로의 길이 d1, 반시계 방향 경로의 길이 d2에 대해 d1이 더 작다면 이 경로의 길이가 최댓값인지 비교하기 위해 answer와 d1 중 최댓값을 answer에 저장하고 현재 시계 방향의 경로가 전체 경로의 절반에 더 가깝게 만들기 위해 right를 다음 지점으로 넘김

만약 반대로 d2가 더 작다면 answer와 d2을 비교해 최댓값을 answer에 저장하고 시계 방향의 경로가 절반에 가깝게 만들기 위해 left를 다음 지점으로 넘김

이를 left가 right보다 커지거나, right가 N을 넘기지 않을 동안 반복하면 answer에 두 지점 사이의 경로 중 최단 거리의 최댓값이 저장되므로 출력하면 정답

### 풀이 설명

원형으로 이어진 N개의 지점이 있을 때 두 지점을 골라 두 지점 사이의 최단 거리의 최댓값을 구하는 문제이다.

이 때 거리는 원형으로 이어진 경로를 타야 하므로 두 지점을 고르면 시계, 반시계 방향의 경로가 생긴다.

![](https://velog.velcdn.com/images/kosdjs/post/fef380d0-982b-4c4c-876e-762e2f5e6e03/image.png)

즉, 위 그림처럼 빨간색의 시계 방향의 경로가 있다면 반시계 방향의 경로는 전체 경로의 길이에서 시계 방향의 경로의 길이를 뺀 만큼이 된다는 소리이다.

두 지점 사이의 경로의 길이는 사이에 이어진 경로의 합이 되므로 경로의 길이는 누적합으로 계산하면 편하다.

두 지점을 어떻게 골라야 하는지 생각해보아야 하는데 앞서 살펴보았듯 전체 경로가 두 경로로 나뉘는 꼴이므로 나뉜 경로 중 짧은 경로의 길이의 최댓값은 전체 경로의 절반이 된다.

두 지점 사이의 경로의 길이가 누적합 형식이 되고, 최대한 전체 경로의 절반에 가까운 두 쌍을 찾아야 하는 것이므로 투 포인터 알고리즘을 적용해 문제를 풀 수 있다.

그렇게 두 지점 left, right를 잡고, arr[i]를 N번 지점에서부터 i번 지점까지 시계 방향으로 가는 경로의 길이로 정의한 이후에 left와 right 사이에 시계 방향 경로의 길이를 d1, 반시계 방향 경로의 길이를 d2로 잡으면 매번 d1과 d2를 비교하며 최단 경로를 찾을 수 있다.

만약 d1이 더 작아서 최단 경로가 되었다면 현재 d1이 최단 경로의 길이이므로 두 지점 사이의 최단 경로 길이의 최댓값을 저장하는 answer에 저장된 값과 d1을 비교해 최댓값을 answer에 저장하면 된다.

그리고 d1이 더 작기 때문에 이 경로의 길이가 전체 경로의 길이의 절반에 가깝게 만들기 위해서 right를 1 증가시켜 다음 지점까지의 경로를 살펴보면 된다.

d2가 더 작다면 d2와 answer를 비교해 더 큰 값을 answer에 저장하고 마찬가지로 시계 방향 경로의 길이를 전체 경로의 절반에 가깝게 만들기 위해 left를 1 증가해 더 짧은 경로를 확인하게 한다.

이를 right가 N보다 커지거나 left가 right를 넘어설때까지 하면 시계 방향 경로의 길이가 최대한 전체 경로의 절반에 가까워지는 두 지점들을 확인한 것이므로 answer에 두 지점 사이의 최단 경로의 최댓값이 저장된다. 그러므로 answer를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N + 1)
    arr[1] = nextInt()
    for(i in 2..N){
        arr[i] = nextInt() + arr[i - 1]
    }
    var left = 1
    var right = 1
    var answer = 0
    while(left <= right && right < N + 1){
        val d1 = arr[right] - arr[left - 1]
        val d2 = arr[N] - d1
        if(d1 > d2){
            answer = maxOf(answer, d2)
            left++
        } else {
            answer = maxOf(answer, d1)
            right++
        }
    }
    println(answer)
}
```