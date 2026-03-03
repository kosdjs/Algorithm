# 백준 22251번: 빌런 호석

> 문제: https://www.acmicpc.net/problem/22251

### 문제 풀이

브루트 포스

diff[i][j] = LED로 i를 나타낸 상태에서 j를 나타내기 위해 반전시켜야 하는 LED의 갯수

answer = P개 이내로 LED를 반전시켜 나타낼 수 있는 층의 갯수

LED를 반전시켜 다른 숫자로 바꾸려면 필요한 갯수가 숫자에 따라 달라짐

숫자는 각 자리마다 0부터 9까지의 숫자로 나타내므로 10개의 숫자를 서로 바꾸도록 반전시켜야 하는 LED의 갯수를 diff 배열에 저장함

1부터 N까지의 i층에 대해 X에서 LED를 몇개 반전시켜야 나타낼 수 있는지를 구해 P개 이내로 나타낼 수 있으면 answer를 1 증가시킴

모든 층을 나타내기 위해 반전시켜야 하는 LED 갯수를 구했으면 answer에 P개 이내로 반전시켜 나타낼 수 있는 층의 갯수가 저장되므로 출력하면 정답

### 풀이 설명

K자리까지 층수를 보여주는 디스플레이가 있는 엘리베이터에서 1층부터 N층까지 있을 때 현재 X층에 있다면 최대 P개의 LED를 반전시켜 나타낼 수 있는 실제 층이 몇 개가 있는지 구하는 문제이다.

LED로 각 자리마다 0부터 9까지의 수를 나타내고 숫자를 나타내는 방법이 정해져 있기 때문에 숫자를 변경하기 위해 반전시켜야 하는 LED의 갯수도 정해져 있다.

그리고 현재 K가 최대 6이고 N이 최대 999,999까지 이므로 X층에서 다른 층으로 변환할 때 LED를 몇 개 반전시켜야 하는지 확인해야 하는 횟수는 약 6,000,000번이 조금 안된다. 따라서 모든 경우를 확인해 경우의 수를 확인할 수 있다.

그러면 숫자 i에서 j로 바뀔때 반전시켜야 하는 LED의 갯수를 미리 구해놓아야 하는데 한 자리에 숫자가 0부터 9까지 있으므로 이 10개의 숫자에서 중복을 포함해 2개를 뽑는다고 하면 총 100개의 쌍이 나오므로 이를 이차원 배열 diff[i][j]에 저장을 해놓는다.

P개 이내로 LED를 반전시켜 나타낼 수 있는 층의 갯수를 구하기 위해 변수 answer를 정의하고 0으로 초기화한다. 이 값을 구하기 위해 X를 제외한 1부터 N까지의 모든 층에 대해 X에서 변환할 때 모든 자릿수에 대해 반전시켜야 할 LED의 갯수를 구하고 이 갯수가 P개 이내라면 X에서 변환이 가능한 것이므로 answer를 1 증가시킨다.

모든 층에 대해 반전시켜야 할 LED의 갯수를 구해 조건을 확인하면 answer에 구하는 X에서 변환 가능한 층의 갯수가 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    val P = nextInt()
    val X = nextInt()
    var answer = 0
    val diff = arrayOf(
        intArrayOf(0, 4, 3, 3, 4, 3, 2, 3, 1, 2),
        intArrayOf(4, 0, 5, 3, 2, 5, 6, 1, 5, 4),
        intArrayOf(3, 5, 0, 2, 5, 4, 3, 4, 2, 3),
        intArrayOf(3, 3, 2, 0, 3, 2, 3, 2, 2, 1),
        intArrayOf(4, 2, 5, 3, 0, 3, 4, 3, 3, 2),
        intArrayOf(3, 5, 4, 2, 3, 0, 1, 4, 2, 1),
        intArrayOf(2, 6, 3, 3, 4, 1, 0, 5, 1, 2),
        intArrayOf(3, 1, 4, 2, 3, 4, 5, 0, 4, 3),
        intArrayOf(1, 5, 2, 2, 3, 2, 1, 4, 0, 1),
        intArrayOf(2, 4, 3, 1, 2, 1, 2, 3, 1, 0),
    )
    for(i in 1..N){
        if(i == X) continue
        var before = X
        var after = i
        var d = 0
        while(before > 0 || after > 0){
            d += diff[before % 10][after % 10]
            before /= 10
            after /= 10
        }
        if(d <= P) answer++
    }
    println(answer)
}
```