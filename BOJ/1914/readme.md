# 백준 1914번: 하노이 탑

> 문제: https://www.acmicpc.net/problem/1914

### 문제 풀이

재귀

printTower(level, start, empty, target) = level개 쌓인 하노이 타워를 start에서 target으로 옮기는 과정을 출력하는 재귀 함수

N개의 원판이 있는 하노이 탑을 옮길려면 N - 1개의 원판을 빈 칸으로 옮기고 N번째 원판을 원하는 칸으로 옮긴 이후 빈 칸에 있던 N - 1개의 원판을 다시 원하는 칸으로 옮겨야 함

이를 printTower(level, start, empty, target) 재귀함수로 구현함

N개의 원판이 있는 하노이 탑을 옮기는 최소 이동 횟수는 $2^N - 1$이므로 출력하고 N이 20이하일때만 printTower(N, 1, 2, 3)을 호출해 이동 순서를 출력하면 정답

### 풀이 설명

세 개의 장대가 있고 첫 번째 장대에는 반경이 서로 다른 n개의 원판이 쌓여 있다. 각 원판은 반경이 큰 순서대로 쌓여있다. 이제 수도승들이 다음 규칙에 따라 첫 번째 장대에서 세 번째 장대로 옮기려 한다.

1. 한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
2. 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.

이 작업을 수행하는데 필요한 이동 순서를 출력하는 프로그램을 작성하라. 단, 이동 횟수는 최소가 되어야 한다.

아래 그림은 원판이 5개인 경우의 예시이다.

![](https://velog.velcdn.com/images/kosdjs/post/01c218b3-d74e-44fa-ba9c-f207f6812cb7/image.png)

N개의 원판이 있는 하노이 탑을 옮기는 것은 한 번에 한 개의 원판만 옮길 수 있고 항상 위의 것이 아래의 것보다 작아야 하므로 N - 1개의 원판을 먼저 빈 칸으로 옮겨놓고 가장 큰 원판을 원하는 칸으로 옮긴 이후에 다시 빈 칸에 옮겨놓았던 N - 1개의 원판들을 원하는 칸으로 옮겨놓아야 한다.

따라서 $a_N$을 N개의 원판을 옮기는 최소 횟수라고 하면 다음과 같은 식이 성립한다.

$a_N = 2 	imes a_{N - 1} + 1$

여기서 양 변에 1을 더해주면 식이 다음과 같이 변한다.

$a_N + 1 = 2 	imes (a_{N - 1} + 1)$

여기서 $a_N + 1$을 $b_N$이라고 하면 다음과 같은 식이 나오고 $b_N$이 공비가 2인 등비수열이라는 것을 알 수 있다.

$b_N = 2 	imes b_{N - 1}$

$b_1$이 $a_1 + 1$이고 $a_1$이 1이기 때문에 $b_N$이 첫 항이 2이고 공비가 2인 등비수열이므로 $b_N = 2^N$이 된다. 즉, $a_N = 2^N - 1$이라는 식이 성립한다.

그러므로 N개의 원판을 옮기는 최소 횟수는 $2^N - 1$이라는 것을 알 수 있다.

그 이후에 N이 20 이하라면 이동 과정을 출력해야 하므로 앞서 언급했듯이 하노이 탑의 이동 과정을 재귀적으로 구현할 수 있으므로 재귀 함수로 이동 과정을 출력할 수 있다.

따라서 level개의 원판을 start에서 target으로 옮기는 함수를 printTower(level, start, empty, target)로 정의하고 호출되면 level - 1개의 원판을 빈 칸 empty로 옮겨야 하기 때문에 내부에서 printTower(level - 1, start, targer, empty)를 호출해 옮긴다.

그 이후엔 가장 큰 원판을 start에서 target으로 옮기는 것이므로 원하는 출력 형식에 맞게 "$start $target\
"을 출력하면 된다.

마지막으로 빈 칸으로 옮겨놨던 level - 1개의 원판을 empty에서 target으로 옮겨야 하므로 printTower(level - 1, empty, start, target)을 호출해 level - 1개의 원판을 옮기는 과정을 출력해주면 된다.

이때 재귀 함수는 입력된 level보다 1 작은 level의 함수를 재귀적으로 계속 호출하기 때문에 함수 호출을 멈출 base case가 없다면 함수가 끝나지 않게 된다. 여기서는 원판이 0개가 되면 옮길 원판이 없기 때문에 함수 맨 앞에 level이 0이라면 함수를 바로 반환하도록 만들면 된다.

이 문제는 N이 100까지이므로 N개의 원판을 옮기는 최소 횟수가 $2^N - 1$이므로 N이 100일때는 Long의 범위를 넘어서게 된다. 이때는 BigInteger를 이용해 계산해서 출력하면 된다.

그러므로 BigInteger를 이용해 $2^N - 1$를 출력하고 N이 20 이하일때만 printTower(N, 1, 2, 3)을 호출해 이동 순서를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import java.math.BigInteger

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    val bw = System.out.bufferedWriter()
    bw.append("${BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE)}\
")
    if(N <= 20){
        fun printTower(level: Int, start: Int, empty: Int, target: Int){
            if(level == 0) return
            printTower(level - 1, start, target, empty)
            bw.append("$start $target\
")
            printTower(level - 1, empty, start, target)
        }
        printTower(N, 1, 2, 3)
    }
    bw.flush()
    bw.close()
}
```