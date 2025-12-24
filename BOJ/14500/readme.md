# 백준 14500번: 테트로미노

> 문제: https://www.acmicpc.net/problem/14500

### 문제 풀이

구현

map = NxM 크기의 종이에 적힌 정수 값

answer = 테트로미노가 놓인 칸에 쓰여 있는 수들의 합 중 최댓값

|변수명|테트로미노|
|:-:|:-:|
|box|![](https://velog.velcdn.com/images/kosdjs/post/02109d64-288d-400a-8b48-b92ff4451b36/image.png)|
|longVertical|![](https://velog.velcdn.com/images/kosdjs/post/250c8a74-c0c5-41e5-a00a-aff9eac77f68/image.png)|
|longHorizontal|![](https://velog.velcdn.com/images/kosdjs/post/67daef68-99c2-4792-af32-0eeafda1deef/image.png)|
|tVertical1|![](https://velog.velcdn.com/images/kosdjs/post/fe84de5b-3fc4-455d-97ad-f27b3bc62e2a/image.png)|
|tVertical2|![](https://velog.velcdn.com/images/kosdjs/post/555c4971-5411-49a1-a62f-e93036162e28/image.png)|
|sVertical1|![](https://velog.velcdn.com/images/kosdjs/post/fe74d941-ba14-4f4f-8c77-6f310f95fb1a/image.png)|
|sVertical2|![](https://velog.velcdn.com/images/kosdjs/post/70eb6aac-6b7d-41d3-aec8-8fee89b7a95a/image.png)|
|lVertical1|![](https://velog.velcdn.com/images/kosdjs/post/c1fc12bd-b36c-41ea-b9c2-c5efc849c10d/image.png)|
|lVertical2|![](https://velog.velcdn.com/images/kosdjs/post/ae83f21e-823f-4b1c-ac77-b127f3a18eac/image.png)|
|lVertical3|![](https://velog.velcdn.com/images/kosdjs/post/4ae50fbf-8e86-4383-86e5-1d6b2ac5031d/image.png)|
|lVertical4|![](https://velog.velcdn.com/images/kosdjs/post/f4330be2-60d6-4c20-9d79-00f2a9d8ece1/image.png)|
|tHorizontal1|![](https://velog.velcdn.com/images/kosdjs/post/fe01e2e2-449f-43c1-b067-c9f6319f053a/image.png)|
|tHorizontal2|![](https://velog.velcdn.com/images/kosdjs/post/8db2688d-e05a-45c3-8956-399308977054/image.png)|
|sHorizontal1|![](https://velog.velcdn.com/images/kosdjs/post/8472c450-0cea-4269-b8a6-1982826be358/image.png)|
|sHorizontal2|![](https://velog.velcdn.com/images/kosdjs/post/019108b4-00ab-4ece-9b70-b573a618ee43/image.png)|
|lHorizontal1|![](https://velog.velcdn.com/images/kosdjs/post/7516d600-7a9f-40a8-ba57-1bc5a23121ec/image.png)|
|lHorizontal2|![](https://velog.velcdn.com/images/kosdjs/post/3b4ddabc-48aa-4869-a4b1-b9144c30239a/image.png)|
|lHorizontal3|![](https://velog.velcdn.com/images/kosdjs/post/d4a49336-6247-48f2-85e4-cf4a77290d95/image.png)|
|lHorizontal4|![](https://velog.velcdn.com/images/kosdjs/post/5f7cb1b6-f44b-4208-b13d-5ee044018ea9/image.png)|

종이의 모든 칸 (i, j)에 대해 해당 칸을 테트로미노 격자의 가장 맨 왼쪽 위칸이라고 했을 때 각 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 구해 최댓값을 answer에 저장하고 출력하면 정답

### 풀이 설명

폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.

- 정사각형은 서로 겹치면 안 된다.
- 도형은 모두 연결되어 있어야 한다.
- 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.

정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.

![](https://velog.velcdn.com/images/kosdjs/post/7d3b0f3a-26f0-415f-9e1d-0754ab0fd839/image.png)

아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.

테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.

테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.

테트로미노를 회전, 대칭까지 포함해 N x M의 크기로 구분하면 다음과 같이 나누어진다.

- 2 x 2 크기의 테트로미노

![](https://velog.velcdn.com/images/kosdjs/post/02109d64-288d-400a-8b48-b92ff4451b36/image.png)

- 4 x 1 크기의 테트로미노

![](https://velog.velcdn.com/images/kosdjs/post/250c8a74-c0c5-41e5-a00a-aff9eac77f68/image.png)

- 1 x 4 크기의 테트로미노

![](https://velog.velcdn.com/images/kosdjs/post/67daef68-99c2-4792-af32-0eeafda1deef/image.png)

- 3 x 2 크기의 테트로미노

|![](https://velog.velcdn.com/images/kosdjs/post/fe84de5b-3fc4-455d-97ad-f27b3bc62e2a/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/555c4971-5411-49a1-a62f-e93036162e28/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/fe74d941-ba14-4f4f-8c77-6f310f95fb1a/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/70eb6aac-6b7d-41d3-aec8-8fee89b7a95a/image.png)|
|-|-|-|-|
|![](https://velog.velcdn.com/images/kosdjs/post/c1fc12bd-b36c-41ea-b9c2-c5efc849c10d/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/ae83f21e-823f-4b1c-ac77-b127f3a18eac/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/4ae50fbf-8e86-4383-86e5-1d6b2ac5031d/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/f4330be2-60d6-4c20-9d79-00f2a9d8ece1/image.png)|

- 2 x 3 크기의 테트로미노

|![](https://velog.velcdn.com/images/kosdjs/post/fe01e2e2-449f-43c1-b067-c9f6319f053a/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/8db2688d-e05a-45c3-8956-399308977054/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/8472c450-0cea-4269-b8a6-1982826be358/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/019108b4-00ab-4ece-9b70-b573a618ee43/image.png)|
|-|-|-|-|
|![](https://velog.velcdn.com/images/kosdjs/post/7516d600-7a9f-40a8-ba57-1bc5a23121ec/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/3b4ddabc-48aa-4869-a4b1-b9144c30239a/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/d4a49336-6247-48f2-85e4-cf4a77290d95/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/5f7cb1b6-f44b-4208-b13d-5ee044018ea9/image.png)|

이렇게 구한 모든 테트로미노의 격자 칸 중에서 가장 왼쪽 위칸을 테트로미노의 시작 칸이라고 하면 이를 NxM 크기의 종이의 모든 칸에 대해서 값의 합을 구해 최댓값을 구해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val map = Array(N){IntArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
        }
    }
    var answer = 0
    for(i in 0 until N ){
        for(j in 0 until M){
            if(i <= N - 2 && j <= M - 2){
                val box = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1]
                answer = maxOf(answer, box)
            }
            if(i <= N - 4){
                val longVertical = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 3][j]
                answer = maxOf(answer, longVertical)
            }
            if(j <= M - 4){
                val longHorizontal = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3]
                answer = maxOf(answer, longHorizontal)
            }
            if(i <= N - 3 && j <= M - 2){
                val tVertical1 = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j]
                answer = maxOf(answer, tVertical1)
                val tVertical2 = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1]
                answer = maxOf(answer, tVertical2)
                val sVertical1 = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1]
                answer = maxOf(answer, sVertical1)
                val sVertical2 = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j]
                answer = maxOf(answer, sVertical2)
                val lVertical1 = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 2][j]
                answer = maxOf(answer, lVertical1)
                val lVertical2 = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1]
                answer = maxOf(answer, lVertical2)
                val lVertical3 = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j + 1]
                answer = maxOf(answer, lVertical3)
                val lVertical4 = map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j] + map[i + 2][j + 1]
                answer = maxOf(answer, lVertical4)
            }
            if(i <= N - 2 && j <= M - 3){
                val tHorizontal1 = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]
                answer = maxOf(answer, tHorizontal1)
                val tHorizontal2 = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, tHorizontal2)
                val sHorizontal1 = map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, sHorizontal1)
                val sHorizontal2 = map[i][j + 1] + map[i][j + 2] + map[i + 1][j] + map[i + 1][j + 1]
                answer = maxOf(answer, sHorizontal2)
                val lHorizontal1 = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j]
                answer = maxOf(answer, lHorizontal1)
                val lHorizontal2 = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 2]
                answer = maxOf(answer, lHorizontal2)
                val lHorizontal3 = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, lHorizontal3)
                val lHorizontal4 = map[i][j + 2] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]
                answer = maxOf(answer, lHorizontal4)
            }
        }
    }
    println(answer)
}
```