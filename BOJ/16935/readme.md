# 백준 16935번: 배열 돌리기 3

> 문제: https://www.acmicpc.net/problem/16935

### 문제 풀이

구현

3, 4번 연산만 배열의 구조가 바뀌기 때문에 새로운 배열을 만들어주고 나머지 연산은 인덱스를 활용해 원소를 바꿔줌

1번은 상하반전이므로 반복을 행열의 위쪽 반만 하도록 만들고 아래쪽 반과 원소를 교환하면 됨

2번은 좌우 반전이고 2차원 배열에서 열이 IntArray이므로 reverse를 사용해 반전시키면 됨

5, 6번은 반복이 1번 그룹에서만 되도록 하고 회전하는 방향에 따라 원소를 교환해주면 됨

### 풀이 설명

배열이 NxM이므로 3, 4번 연산을 적용하면 배열이 MxN으로 바뀐다. 따라서 이때는 배열을 새로 만들어 원소를 배치해야 한다. 이 때 행이 열이되고 열이 행이 되기 때문에 반복문에서 인덱스 i, j를 새로 만드는 배열을 기준으로 한다면 arr에 사용할 때는 j, i순으로 사용해야 한다. 그리고 배열의 구조가 바뀌었으니 N, M을 다시 바꿔줘서 다른 연산을 할 때 N, M을 그대로 사용할 수 있도록 한다.

1, 2, 5, 6번 연산은 전부 중심을 기준으로 반씩 나눠서 구역이 구분되므로 halfN, halfM을 미리 계산해놓고 사용하면 더 빠르다.

1번 연산의 경우 상하반전이므로 반전되는 원소와 교환을 할 때 반복문을 halfN까지만 돌아야 한다. 이에 따라 arr[i][j]와 arr[N - 1 - i][j]를 교환해주면 된다.

2번 연산의 경우 좌우반전이고 모든 열을 좌우반전해주면 되는데 여기서 열은 IntArray 즉, 배열이기 때문에 reverse() 연산을 사용할 수 있다. 따라서 모든 열에 대해 reverse()를 사용하면 따로 구현할 필요가 없다.

5, 6번 연산은 회전이기 때문에 그룹별로 원소를 교환해주면 된다. 1번 그룹은 (0, 0)에서 (halfN - 1, halfM - 1) 까지이고, 2번 그룹은 (0, halfM)에서 (halfN - 1, M)까지이고, 3번 그룹은 (halfN, halfM)에서 (N, M)까지이고, 4번 그룹은 (halfN, 0)에서 (N, halfM - 1)까지이다. 이에 따라 1번 그룹을 인덱스 (i, j)로 반복한다고 하면 2번 그룹은 (i, halfM + j), 3번 그룹은 (halfN + i, halfM + j), 4번 그룹은 (i, halfM + j)의 인덱스를 가지게 된다. 따라서 이 인덱스들을 사용해 1, 2, 3, 4번 그룹이 회전하도록 원소를 교환해주면 된다.

이렇게 구현된 연산을 순서대로 사용하고 남은 배열을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    var M = st.nextToken().toInt()
    var halfN = N / 2
    var halfM = M / 2
    val R = st.nextToken().toInt()
    var arr = Array(N){ IntArray(M) }
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            arr[i][j] = st.nextToken().toInt()
        }
    }
    st = StringTokenizer(br.readLine())
    repeat(R){
        val op = st.nextToken().toInt()
        if(op == 1){
            for(i in 0 until halfN){
                for(j in 0 until M){
                    val temp = arr[i][j]
                    arr[i][j] = arr[N - 1 - i][j]
                    arr[N - 1 - i][j] = temp
                }
            }
        } else if(op == 2){
            for(i in 0 until N){
                arr[i].reverse()
            }
        } else if(op == 3){
            val temp = Array(M){IntArray(N)}
            for(i in 0 until M){
                for(j in 0 until N){
                    temp[i][j] = arr[N - 1 - j][i]
                }
            }
            arr = temp
            val t = N
            N = M
            M = t
            halfN = N / 2
            halfM = M / 2
        } else if(op == 4){
            val temp = Array(M){IntArray(N)}
            for(i in 0 until M){
                for(j in 0 until N){
                    temp[i][j] = arr[j][M - 1 - i]
                }
            }
            arr = temp
            val t = N
            N = M
            M = t
            halfN = N / 2
            halfM = M / 2
        } else if(op == 5){
            for(i in 0 until halfN){
                for(j in 0 until halfM){
                    val temp = arr[i][j]
                    arr[i][j] = arr[halfN + i][j]
                    arr[halfN + i][j] = arr[halfN + i][halfM + j]
                    arr[halfN + i][halfM + j] = arr[i][halfM + j]
                    arr[i][halfM + j] = temp
                }
            }
        } else {
            for(i in 0 until halfN){
                for(j in 0 until halfM){
                    val temp = arr[i][j]
                    arr[i][j] = arr[i][halfM + j]
                    arr[i][halfM + j] = arr[halfN + i][halfM + j]
                    arr[halfN + i][halfM + j] = arr[halfN + i][j]
                    arr[halfN + i][j] = temp
                }
            }
        }
    }
    for(i in 0 until arr.size){
        for(j in 0 until arr[0].size){
            print("${arr[i][j]} ")
        }
        println()
    }
}
```