# 백준 2115번: 갤러리

> 문제: https://www.acmicpc.net/problem/2115

### 문제 풀이

구현

wall[i][j] = (i, j) 칸이 벽인지 여부

answer = 걸수 있는 그림의 최대 갯수

세로, 가로 방향마다 벽과 빈 공간이 나란히 2칸씩 이어져있으면 그 공간에 그림을 걸 수 있는 것이므로 모든 경계를 확인해 걸 수 있는 그림의 갯수를 세어서 answer에 저장하고 출력하면 정답

### 풀이 설명

갤러리의 벽과 빈 공간의 정보가 주어졌을 때 2칸 짜리 그림을 겹치지 않게 몇 개까지 걸 수 있는지 구하는 문제이다.

![](https://velog.velcdn.com/images/kosdjs/post/9ec232ae-8748-4074-9329-1b7797725f20/image.png)

갤러리는 위 그림처럼 회색 칸이 벽, 흰 칸이 빈 공간으로 주어지고 모든 가장자리는 항상 벽으로 주어진다. 가장자리가 항상 벽으로 주어지기 때문에 그림을 걸 수 있는지 확인해야 하는 곳이 다음과 같이 변한다.

![](https://velog.velcdn.com/images/kosdjs/post/c8d725f7-86d7-4eac-8fc1-11a8fc780959/image.png)|![](https://velog.velcdn.com/images/kosdjs/post/17e5ec67-dca4-43e5-9972-4e18a7580fb3/image.png)
::|::
그림을 세로로 걸 수 있는지 확인해야 하는 곳|그림을 가로로 걸 수 있는지 확인해야 하는 곳

세로, 가로 각각 그림이 걸린 곳을 확인해보면 선으로 벽과 빈 공간이 나누어지고 각각 칸이 같은 공간으로 2칸이 이어지는 것을 볼 수 있다.

이에 따라 칸을 세로, 가로로 보면서 경계가 벽과 빈 공간을 나누며 연속된 2칸이 같은 공간인 경계에 그림을 걸 수 있는 것이므로 이런 경계들의 갯수를 세면 된다.

갤러리는 벽 또는 빈 공간으로만 이루어지므로 (i, j) 칸이 벽인지 여부를 wall[i][j]에 저장하고 걸수 있는 그림의 최대 갯수를 저장할 answer 변수를 정의한다.

그 이후 배열 wall에 저장된 값을 이용해 세로, 가로 방향으로 벽과 빈 공간이 연속으로 2칸씩 이어지고 경계로 나누어지는 공간을 찾으면 그 공간에 그림을 걸 수 있는 것이므로 answer를 1 증가시킨다.

그렇게 모든 경계에 대해 조건을 만족하는지 검사하면 걸수 있는 그림의 최대 갯수가 answer에 저장되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run{
    val st = StringTokenizer(readLine())
    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val wall = Array(M){ BooleanArray(N) }
    var answer = 0
    for(i in 0 until M){
        val s = readLine()
        for(j in 0 until N){
            wall[i][j] = s[j] == 'X'
        }
    }
    for(i in 1 until M){
        var j = 1
        while(j < N - 1){
            if(wall[i - 1][j] == wall[i - 1][j + 1] && wall[i][j] == wall[i][j + 1] && wall[i - 1][j] != wall[i][j]){
                answer++
                j++
            }
            j++
        }
    }
    for(j in 1 until N){
        var i = 1
        while(i < M - 1){
            if(wall[i][j - 1] == wall[i + 1][j - 1] && wall[i][j] == wall[i + 1][j] && wall[i][j - 1] != wall[i][j]){
                answer++
                i++
            }
            i++
        }
    }
    println(answer)
}
```