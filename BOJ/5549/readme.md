# 백준 5549번: 행성 탐사

> 문제: https://www.acmicpc.net/problem/5549

### 문제 풀이

누적합

jungle[i][j], ocean[i][j], ice[i][j] = (1, 1)칸부터 (i, j)칸까지 정글, 바다, 얼음의 갯수

입력을 받을 때 누적합을 구하려면 왼쪽 칸까지의 누적합, 위쪽 칸까지의 누적합을 더하고 왼쪽 위칸까지의 누적합을 빼주어야 함

따라서 모든 칸 (i, j)에 대해 jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1]과 같이 누적합을 구하면서 해당 칸의 입력이 어떤 칸인지에 따라 해당 칸의 갯수를 추가로 더해주면 됨

그 이후엔 영역 (a, b), (c, d)의 누적합을 구해야 하므로 (1, 1)부터 (c, d)까지의 누적합에서 (1, 1)부터 (c, b - 1)의 누적합과 (1, 1)부터 (a - 1, d)까지의 누적합을 빼주고 중복으로 빠지는 (1, 1)부터 (a - 1, b - 1)까지의 누적합을 더해줘서 구하고 출력하면 정답

### 풀이 설명

직사각형의 영역에 정글, 바다, 얼음이 각각 몇 개씩 있는지 구하는 문제이다. 따라서 누적합을 이용해 영역 내에 정글, 바다, 얼음이 몇 개있는지 구하면 된다.

정글, 바다, 얼음의 갯수를 따로 구해야 하므로 각각의 누적합을 저장할 배열을 jungle, ocean, ice로 만든다. 누적합을 구할 때 왼쪽 칸까지의 누적합과 위쪽 칸까지의 누적합을 더하고 왼쪽 위칸까지의 누적합을 빼주기 때문에 칸의 인덱스를 1부터 시작하도록 만들어준다.

영역으로 (a, b)부터 (c, d)까지로 주어지면 먼저 정글부터 확인하면 (1, 1)부터 (c, d)까지의 누적합 jungle[c][d]에서 빼야 하는 왼쪽 칸 (1, 1)부터 (c, b - 1)의 누적합 jungle[c][b - 1]을 빼주고, 위쪽 칸 (1, 1)부터 (a - 1, d)의 누적합 jungle[a - 1][d]를 빼주면 왼쪽 위의 칸 (1, 1)부터 (a - 1, b - 1)까지의 누적합이 추가로 빠지므로 jungle[a - 1][b - 1]을 추가로 더해주면 구하는 영역의 누적합이 나온다.

따라서 위에 정글을 구한 것처럼 영역마다 정글, 바다, 얼음의 누적합을 구해 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextString(): String{
        nextToken()
        return sval
    }
    val M = nextInt()
    val N = nextInt()
    val K = nextInt()
    val jungle = Array(M + 1){IntArray(N + 1)}
    val ocean = Array(M + 1){IntArray(N + 1)}
    val ice = Array(M + 1){IntArray(N + 1)}
    for(i in 1..M){
        val s = nextString()
        for(j in 1..N){
            jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1]
            ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1]
            ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1]
            when(s[j - 1]){
                'J' -> jungle[i][j]++
                'O' -> ocean[i][j]++
                'I' -> ice[i][j]++
            }
        }
    }
    val sb = StringBuilder()
    repeat(K){
        val a = nextInt()
        val b = nextInt()
        val c = nextInt()
        val d = nextInt()
        sb.append(jungle[c][d] - jungle[c][b - 1] - jungle[a - 1][d] + jungle[a - 1][b - 1])
        sb.append(' ')
        sb.append(ocean[c][d] - ocean[c][b - 1] - ocean[a - 1][d] + ocean[a - 1][b - 1])
        sb.append(' ')
        sb.append(ice[c][d] - ice[c][b - 1] - ice[a - 1][d] + ice[a - 1][b - 1])
        sb.append("\n")
    }
    print(sb)
}
```