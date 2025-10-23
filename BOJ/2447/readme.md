# 백준 2447번: 별 찍기 - 10

> 문제: https://www.acmicpc.net/problem/2447

### 문제 풀이

분할 정복, 재귀

N x N 배열을 공백으로 초기화 함

재귀 함수 star를 이용해 level x level 배열의 시작점에서 부터 9등분하면 나뉘는 부분의 시작점들을 다시 재귀적으로 호출함, 이 때 가운데 부분은 공백이므로 호출하지 않음

이를 level이 1이 될때까지 반복하면 더이상 배열이 나뉘지 않는 곳이고 공백을 제외했기 때문에 해당 인덱스를 별로 채움

이렇게 별을 채워놓은 배열을 출력하면 정답

### 풀이 설명

재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.

크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.

```
***
* *
***
```

N이 3보다 클 경우, 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이다.

즉, N이 3의 거듭제곱이고 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이므로 패턴을 9등분해 가운데를 비운다는 뜻이고, 나머지 8부분도 다시 9등분하고 가운데를 비운다는 뜻이다.

다시 말해 더이상 나누어지지 않을 때까지 9등분을 하면 어디를 비우고 별을 찍어야하는지 위치를 알 수 있다는 뜻이다. 이를 풀기 위해서 분할 정복과 재귀 함수를 이용해 풀 수 있다.

star(level, y, x)는 level x level 정사각형의 시작 위치가 (y, x)라는 뜻이고 이를 9등분하면 시작 위치가 y와 x에 level / 3을 더하는 꼴이 된다. 여기서 정확히 가운데 좌표는 y와 x에 정확히 level / 3만큼만 더한 경우이므로 이 경우에는 재귀 호출을 하지 않는다.

재귀 호출을 할 때 정사각형이 9등분 되므로 level도 3으로 나누어서 호출을 해야 한다. 그렇게 재귀 호출을 하다보면 더이상 나눌 수 없도록 level이 1이 되는데 이 때 위치는 재귀 호출을 할 때 비워야 할 칸을 빼고 호출했으므로 이 위치에는 별을 찍어주면 된다. 따라서 배열에 별을 저장하면 된다.

이렇게 함수가 끝나고 나면 별을 찍어야 하는 모든 위치에 별이 들어가 있으므로 배열을 출력하면 정답이 된다.

### 소스 코드
```kotlin
lateinit var arr: Array<CharArray>

fun main(){
    val N = System.`in`.bufferedReader().readLine().toInt()
    arr = Array(N){ CharArray(N){' '} }
    star(N, 0, 0)
    val bw = System.out.bufferedWriter()
    for(i in 0 until N){
        bw.write(arr[i])
        bw.newLine()
    }
    bw.flush()
    bw.close()
}

fun star(level: Int, y: Int, x: Int){
    if(level == 1){
        arr[y][x] = '*'
    } else {
        for (ny in y until y + level step level / 3){
            for(nx in x until x + level step level / 3){
                if(nx == x + level / 3 && ny == y + level / 3){
                    continue
                } else {
                    star(level / 3, ny, nx)
                }
            }
        }
    }
}
```