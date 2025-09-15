# 백준 14891번: 톱니바퀴

> 문제: https://www.acmicpc.net/problem/14891

### 문제 풀이

구현

gears[i][j] = i번 톱니바퀴의 j번째 톱니의 극

gearPoint[i] = i번 톱니바퀴의 현재 9시 방향에 있는 톱니

direction[i] = i번 톱니바퀴가 이번 횟수에 회전하는 방향

아래 그림처럼 12시의 톱니를 0번이라고 하고 시계 방향으로 톱니 번호가 1씩 늘어난다고 하면 초기 상태에서 9시 방향의 톱니는 2번임

![](https://velog.velcdn.com/images/kosdjs/post/3b05e93d-393d-4a9c-93c2-4daf5e2b7f24/image.png)

아래 그림처럼 반시계 방향으로 회전하면 9시 방향에 있는 톱니가 2번에서 3번 톱니로 바뀜

![](https://velog.velcdn.com/images/kosdjs/post/f7a7c08b-90bd-4fd4-aa25-9142af9d339e/image.png)

즉, 반시계 방향에 톱니 번호가 증가, 시계 방향에 톱니 번호 감소

3시 방향의 톱니 번호는 정확히 9시 방향의 톱니 번호와 4 차이남

이를 이용해 현재 톱니와 인접한 톱니의 극을 확인해 톱니바퀴가 이번 횟수에 회전하는 전체 방향을 구해서 회전시킴

모든 횟수에 대해 회전시키고 12 방향의 톱니의 극을 확인해 점수를 계산하면 정답.

### 풀이 설명

![](https://velog.velcdn.com/images/kosdjs/post/3b05e93d-393d-4a9c-93c2-4daf5e2b7f24/image.png)

톱니바퀴의 극을 배열에 저장하기 위해 12시 방향의 톱니부터 시계방향으로 번호를 붙인다.

![](https://velog.velcdn.com/images/kosdjs/post/f7a7c08b-90bd-4fd4-aa25-9142af9d339e/image.png)

톱니바퀴가 회전함에 따라 처음에 붙인 번호도 같이 돌아가기 때문에 현재 톱니바퀴의 상태를 확인하기 위해 현재 9시 방향에 몇번 톱니가 위치해 있는지를 저장해놓는다. 이 번호를 이용해 톱니바퀴의 3시 방향, 12시 방향의 극을 찾을 수 있다.

이에 따라 회전하는 톱니바퀴의 번호와 방향이 주어지면 회전할 톱니바퀴의 왼쪽에 있는 모든 톱니바퀴를 극을 확인하며 회전하는지 확인하고, 오른쪽에 있는 톱니바퀴도 회전하는지 확인해 이번 횟수에 모든 톱니바퀴가 어떻게 회전하는지를 구해놓는다.

이렇게 구해놓은 방향에 따라 9시 방향에 위치하는 톱니 번호를 갱신해준다. 이를 모든 회전 횟수에 대해 반복하면 회전 후에 모든 톱니바퀴에 9시 방향에 어떤 번호의 톱니가 위치해있는지 구할 수 있다.

이렇게 구한 번호를 이용해 12시 방향의 톱니의 극을 구해 점수를 계산해서 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val gears = Array(5){IntArray(8)}
    for(i in 1..4){
        val s = br.readLine()
        for(j in 0 until 8){
            gears[i][j] = s[j] - '0'
        }
    }
    //각 기어의 9시 방향
    val gearPoint = IntArray(5){2}
    val K = br.readLine().toInt()
    repeat(K){
        val st = StringTokenizer(br.readLine())
        val num = st.nextToken().toInt()
        val direction = IntArray(5){0}
        direction[num] = st.nextToken().toInt() * -1
        var left = num
        var right = num
        while (left > 1){
            if (gears[left][(gearPoint[left] + 4) % 8] != gears[left - 1][gearPoint[left - 1]]){
                direction[left - 1] = direction[left] * -1
            } else {
                break
            }
            left--
        }
        while (right < 4){
            if(gears[right][gearPoint[right]] != gears[right + 1][(gearPoint[right + 1] + 4) % 8]){
                direction[right + 1] = direction[right] * -1
            } else {
                break
            }
            right++
        }
        for(i in 1..4){
            gearPoint[i] += direction[i]
            if(gearPoint[i] < 0){
                gearPoint[i] += 8
            } else if(gearPoint[i] >= 8){
                gearPoint[i] -= 8
            }
        }
    }
    var answer = 0
    var point = 1
    for(i in 1..4){
        if(gears[i][(gearPoint[i] + 6) % 8] == 1){
            answer += point
        }
        point *= 2
    }
    println(answer)
}
```