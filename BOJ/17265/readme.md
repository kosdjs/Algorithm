# 백준 17265번: 나의 인생에는 수학과 함께

> 문제: https://www.acmicpc.net/problem/17265

### 문제 풀이

DP

max[i][j] = (i + 1, j + 1)까지 가는 경로 중 연산 결과의 최댓값

min[i][j] = (i + 1, j + 1)까지 가는 경로 중 연산 결과의 최솟값

최단 경로는 오른쪽과 아래로만 이동해야 하기 때문에 (i, j)칸의 연산 결과는 항상 왼쪽, 위쪽 연산자를 통해서 나옴

연산자도 왼쪽, 위쪽 숫자를 통해서 올 수 있기 때문에 연산 결과는 총 4개를 비교해야 함

이렇게 모든 경로에 대해 max, min 배열을 채우고 max[N - 1][N - 1]과 min[N - 1][N - 1]을 출력해주면 정답

### 풀이 설명

세현이네 집에서 학교까지 가는 길은 N x N 크기의 바둑판과 같다. 그리고 각 블록은 1x1 정사각형으로 구분 지을 수 있다. 세현이는 그 블록마다 숫자와 연산자가 존재한다고 생각해서 임의의 숫자와 연산자를 각 블록에 넣어 바둑판을 만들었다.

세현이는 학교에서 집으로 가는 경로에서 만나는 숫자와 연산자의 연산 결과의 최댓값과 최솟값을 구하려고 한다. 세현이는 항상 자신의 집 (1, 1)에서 학교 (N, N)까지 최단거리로 이동한다. 최단거리로 이동하기 위해서는 오른쪽과 아래쪽으로만 이동해야 한다.

![](https://velog.velcdn.com/images/kosdjs/post/3875b089-0c81-416a-881d-e082e2c0aa63/image.png)

위와 같이 N = 5 인 5 x 5 바둑판을 만들었다고 해보자.

그림 1의 경로를 통해 집(1, 1)에서 학교(N, N)까지 어떻게 연산이 되는지 확인해보자. 경로에서 만나는 연산자들의 우선순위는 고려하지 않는다.

1. 5 → × → 4 = 20
2. 20 → + → 5 = 25
3. 25 → ×→ 5 = 125
4. 125 → + → 2 = 127
 
그림 1은 최댓값을 가지는 경로이며 127이라는 최댓값을 얻을 수 있다.

그리고 위와 같이 연산하여 그림 2의 경로를 통해서 최솟값으로 3을 얻을 수 있다.

이런 문제 조건들을 확인했을 때 숫자가 있는 칸에 도착했을 때 연산을 하는 것을 알 수 있다. 그리고 항상 최단거리로 이동하기 때문에 현재 칸에 도달하려면 현재 칸의 왼쪽 칸 또는 위쪽 칸을 통해 도달해야 한다.

따라서 DP를 이용해 집에서부터 현재 칸까지의 최댓값, 최솟값을 저장해놓으면 다음 칸의 최댓값, 최솟값을 구할 수 있다.

따라서 최댓값을 저장하는 max, 최솟값을 저장하는 min 배열을 만들고 그 칸에 해당하는 최댓값, 최솟값을 계산해 저장한다. 이때 숫자 칸에 도착했을 때 계산을 하고 그 칸의 왼쪽, 위쪽 칸을 거치는 경로를 확인해야 하기 때문에 왼쪽 연산자의 경우 왼쪽 숫자와 위쪽 숫자의 경우를 계산해봐야 하고, 위쪽 연산자의 경우 왼쪽 숫자와 위쪽 숫자를 계산해봐야 하기 때문에 각 칸마다 해당하는 경우를 계산해야 한다.

이에 따라 모든 경우를 계산하면 max[N - 1][N - 1], min[N - 1][N - 1]에 학교까지 가는 최단거리의 최댓값, 최솟값이 저장되므로 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val map = Array(N){ CharArray(N) }
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until N){
            map[i][j] = st.nextToken()[0]
        }
    }
    val max = Array(N){ IntArray(N){Int.MIN_VALUE} }
    val min = Array(N){ IntArray(N){Int.MAX_VALUE} }
    max[0][0] = map[0][0].digitToInt()
    min[0][0] = map[0][0].digitToInt()
    for(j in 2 until N step 2){
        max[0][j] = op(max[0][j - 2], map[0][j - 1], map[0][j])
        min[0][j] = op(min[0][j - 2], map[0][j - 1], map[0][j])
    }
    for(i in 2 until N step 2){
        max[i][0] = op(max[i - 2][0], map[i - 1][0], map[i][0])
        min[i][0] = op(min[i - 2][0], map[i - 1][0], map[i][0])
    }
    for(i in 1 until N){
        var startIdx = if(i % 2 == 0) 2 else 1
        for(j in startIdx until N step 2){
            var maxResult = Int.MIN_VALUE
            var minResult = Int.MAX_VALUE
            if(i > 1){
                maxResult = maxOf(maxResult, op(max[i - 2][j], map[i - 1][j], map[i][j]))
                minResult = minOf(minResult, op(min[i - 2][j], map[i - 1][j], map[i][j]))
            }
            if(j > 1){
                maxResult = maxOf(maxResult, op(max[i][j - 2], map[i][j - 1], map[i][j]))
                minResult = minOf(minResult, op(min[i][j - 2], map[i][j - 1], map[i][j]))
            }
            maxResult = maxOf(maxResult, op(max[i - 1][j - 1], map[i][j - 1], map[i][j]))
            minResult = minOf(minResult, op(min[i - 1][j - 1], map[i][j - 1], map[i][j]))
            maxResult = maxOf(maxResult, op(max[i - 1][j - 1], map[i - 1][j], map[i][j]))
            minResult = minOf(minResult, op(min[i - 1][j - 1], map[i - 1][j], map[i][j]))
            max[i][j] = maxResult
            min[i][j] = minResult
        }
    }
    println("${max[N - 1][N - 1]} ${min[N - 1][N - 1]}")
}

fun op(num1: Int, op: Char, num2: Char): Int{
    if(op == '+'){
        return num1 + num2.digitToInt()
    } else if(op == '-'){
        return num1 - num2.digitToInt()
    } else {
        return num1 * num2.digitToInt()
    }
}
```