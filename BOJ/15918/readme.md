# 백준 15918번: 랭퍼든 수열쟁이야!!

> 문제: https://www.acmicpc.net/problem/15918

### 문제 풀이

백트래킹

arr[i] = 수열의 i 번째 숫자

visit[i] = 숫자 i를 수열에 썼는지 여부

answer = 조건에 맞는 랭퍼드 수열의 갯수

pick(count, idx) = 현재 숫자 count개를 수열에 넣었고 idx 번째가 비어있을 경우

랭퍼드 수열은 j 번째 위치에 숫자 i가 있으면 j + i + 1 번째 위치에도 숫자 i가 있어야 함

x, y 번째가 같은 숫자라고 했으므로 위 조건으로 생각해보면 들어있는 숫자는 y - x - 1임

이 x, y 번째를 제외한 다른 빈 칸에 숫자를 하나씩 넣기 위해 앞에서부터 빈 칸을 찾아 pick(count, idx) 함수를 이용해 수열에 숫자를 하나씩 넣으며 수열이 완성될때마다 answer를 증가시킴

그러면 조건에 맞는 랭퍼드 수열의 갯수가 answer에 저장되므로 출력하면 정답

### 풀이 설명

1이상 n이하의 자연수가 각각 2개씩 들어있고, 두 개의 i 사이에는 정확히 i개의 수가 있는 길이 2n 수열을 랭퍼드 수열이라고 할 때 x, y 번째의 숫자가 같은 랭퍼드 수열이 몇 개 있는지 구하는 문제이다.

이때 랭퍼드 수열의 정의에 의해서 x, y 번째 숫자 사이에는 y - x - 1개의 숫자가 있으므로 x, y 번째 숫자는 y - x - 1가 된다.

나머지 빈 자리에 숫자를 채워가야 하는데 조건에 맞게 숫자를 넣으려면 현재 숫자 i를 j 번째 위치에 넣는다고 하면 조건에 맞게 j + i + 1 번째 위치에 숫자 i를 넣어야 한다. 이에 따라  j + i + 1 번째 위치에 i를 넣을 수 있을 때만 j 번째 위치에 i를 넣으면 된다.

이에 따라 x, y 번째에 먼저 y - x - 1을 넣고 나머지 위치에 숫자를 하나씩 넣어보며 만들어지는 수열의 갯수를 세어 answer에 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val x = nextInt()
    val y = nextInt()
    val arr = IntArray(n * 2 + 1)
    val visit = BooleanArray(n + 1)
    arr[x] = y - x - 1
    arr[y] = y - x - 1
    visit[y - x - 1] = true
    var answer = 0
    fun pick(count: Int, idx: Int){
        for(i in 1..n){
            if(!visit[i] && idx + i + 1 <= n * 2 && arr[idx + i + 1] == 0){
                visit[i] = true
                arr[idx] = i
                arr[idx + i + 1] = i
                if(count == n - 1){
                    answer++
                } else {
                    for(j in (idx + 1)..(n * 2)){
                        if(arr[j] == 0){
                            pick(count + 1, j)
                            break
                        }
                    }
                }
                visit[i] = false
                arr[idx] = 0
                arr[idx + i + 1] = 0
            }
        }
    }
    for(i in 1..(n * 2)){
        if(arr[i] == 0){
            pick(1, i)
            break
        }
    }
    println(answer)
}
```