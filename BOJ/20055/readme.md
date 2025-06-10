> 문제: https://www.acmicpc.net/problem/20055

### 문제 풀이

구현 문제

벨트의 올리는 위치를 idx에 저장

올리는 위치 idx 기준으로 N칸까지 로봇이 올라가있는지 확인하기 위해 upperBelt 배열에 저장

현재 단계를 나타내는 answer, 내구도가 0인 칸의 갯수를 count에 저장

문제의 과정 1번부터 4번까지를 반복문을 통해 구현

### 풀이 설명

![](https://velog.velcdn.com/images/kosdjs/post/6761b1c1-c6b4-4a73-91ec-db98b6195c8b/image.png)


벨트가 계속 회전함에 따라 현재 컨베이어 벨트의 올리는 위치, 내리는 위치가 계속 변경됨

현재 올리는 위치를 idx라고 잡으면 내리는 위치는 idx + N - 1 가 됨

현재 벨트 위에 로봇이 있는지 확인해야 하기 때문에 N 칸짜리 upperBelt 배열을 만듬

이러면 upperBelt 배열의 인덱스가 0 부터 N - 1까지 이므로 올리는 위치 idx에 그대로 더해주면 내구도가 저장된 A의 인덱스와 같게 됨

현재 단계 수를 answer, 내구도가 0이 된 칸의 갯수를 count로 두고 단계를 시작함

1번 과정에 따라 올리는 위치를 옮기고, 로봇이 올라가 있다면 회전에 의해 한칸씩 앞으로 갈 것이기 때문에 upperBelt에 저장된 값들을 한칸씩 뒤로 밀고 새로 올리는 위치의 값 0, 내리는 위치는 로봇이 도달하면 바로 내린다고 했기 때문에 값 0으로 변경

2번 과정은 upperBelt의 배열을 뒤쪽부터 확인해 다음칸이 비었고 현재 로봇이 있는 칸을 찾고, 현재 올리는 위치 idx와 upperBelt의 인덱스 i를 이용해 움직일 칸의 내구도가 있는지 확인함, 내구도가 있다면 내구도를 1 감소시키고 이동시킨 후 내구도가 0이 되었다면 count를 증가시킴

3번 과정은 올리는 위치 idx의 내구도를 확인해 0이 아니면 내구도 1 감소 후 upperBelt의 첫 칸에 1을 저장해 로봇을 올린 것을 표현, 그리고 이를 통해 칸의 내구도가 0이 되었는지 확인하고 0이 되면 count를 증가시킴

마지막으로 4번 과정에 따라 count를 확인해 내구도가 0인 칸이 K개 이상인지 확인해 이상이라면 반복문을 끝내서 작업을 끝내고, 아니라면 answer값을 증가시켜 다음 단계를 진행한다.

### 소스 코드
```kotlin
import java.io.*
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val A = IntArray(N * 2)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N * 2){
        A[i] = st.nextToken().toInt()
    }
    var answer = 1
    var count = 0
    var idx = 0
    val upperBelt = IntArray(N){0}
    while (true){
        //1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        idx--
        if(idx < 0){
            idx += N * 2
        }
        for(i in N-1 downTo 1){
            upperBelt[i] = upperBelt[i-1]
        }
        upperBelt[0] = 0
        upperBelt[N-1] = 0
        //2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다
        // 만약 이동할 수 없다면 가만히 있는다.
        for(i in N-2 downTo 0){
            if(upperBelt[i] == 1 && upperBelt[i + 1] == 0){
                var idxA = idx + i + 1
                if(idxA >= N * 2){
                    idxA -= N * 2
                }
                if(A[idxA] > 0){
                    A[idxA]--
                    upperBelt[i+1] = 1
                    upperBelt[i] = 0
                    if(A[idxA] == 0){
                        count++
                    }
                }
            }
        }
        //3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if(A[idx] > 0){
            A[idx]--
            upperBelt[0] = 1
            if(A[idx] == 0){
                count++
            }
        }
        //4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
        if(count >= K){
            break
        } else {
            answer++
        }
    }
    println(answer)
}
```