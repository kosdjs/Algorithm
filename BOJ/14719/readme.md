>문제: https://www.acmicpc.net/problem/14719

### 문제 풀이

구현 문제

map[i][j]는 i번째 줄 j번째 칸에 블록이 있는지 여부
- 1이면 블록, 0이면 빈 공간

층마다 블록과 블록 사이에 빈 공간 수가 빗물이 고이는 칸

prev는 이전에 블록이 있었는지를 확인하는 변수
space는 블록 사이의 빈 공간의 수를 확인하기 위한 변수

층을 한칸마다 블록이 있는지 검사해 블록을 한번이라도 마주치면 그때부터 나오는 빈 공간을 space 변수를 통해 세고, 다시 블록이 나올 때 그 값을 결과값으로 더하고 값을 0으로 초기화한다. 이 과정을 모든 층에서 하면 답이 나오는 문제

### 풀이 설명

![](https://velog.velcdn.com/images/kosdjs/post/68225c51-0d91-475c-acbe-4e2a4f79d9a1/image.png)

위 그림처럼 문제의 예시를 한 층마다 본다고 생각해보자

![](https://velog.velcdn.com/images/kosdjs/post/69d76446-980e-4ed2-b9c7-4f2324f409af/image.png)

첫 번째 층의 경우에는 모든 칸이 블록이기 때문에 빗물이 고이지 않는다.

![](https://velog.velcdn.com/images/kosdjs/post/1d207de5-b483-413a-a152-d04daf28c102/image.png)

두 번째 층부터 빗물이 고이기 시작하는데 블록과 블록 사이의 빈공간에 빗물이 고였다.

![](https://velog.velcdn.com/images/kosdjs/post/1b07215c-a081-4413-8f22-ceacb5591f5c/image.png)

세 번째 층의 경우엔 블록과 블록 사이만 빗물이 고였고 오른쪽 빈 공간은 오른쪽이 뚫려있기 때문에 빗물이 고이지 않았다

![](https://velog.velcdn.com/images/kosdjs/post/6d69b98f-41e7-4c6e-bb22-4593a494c5a3/image.png)

네 번째 층은 블록이 하나밖에 없기 때문에 빗물이 고이지 않는다.

즉, 빗물이 고이려면 모든 층에서 블록과 블록 사이의 빈 공간이 몇 칸인지 세면 된다.

문제에서 값을 벽의 높이로 주기 때문에 세로로 생각하기 쉽지만 빗물이 고이는지 확인하려면 결국 가로로 봐야 하는 문제

### 소스 코드

```kotlin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val H = st.nextToken().toInt()
    val W = st.nextToken().toInt()
    var result = 0
    st = StringTokenizer(br.readLine())
    val map = Array(H){IntArray(W){0}}
    for(i in 0 until W){
        val h = st.nextToken().toInt()
        for(j in 0 until h){
            map[j][i] = 1
        }
    }
    for(i in 0 until H){
        var prev = 0 // 이전 칸의 상태 1이면 벽, 0이면 빈 공간
        var space = 0
        for(j in 0 until W){
            if(map[i][j] == 0){// 빈 공간일 때
                if (prev == 1){
                    space++
                }
            } else {// 벽일 때
                if (prev == 0){
                    prev = 1
                } else {
                    result += space
                    space = 0
                }
            }
        }
    }
    println(result)
}
```