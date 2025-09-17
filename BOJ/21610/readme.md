# 백준 21610번: 마법사 상어와 비바라

> 문제: https://www.acmicpc.net/problem/21610

### 문제 풀이

구현

cloud[r][c] = (r, c) 칸이 구름이면 1, 아니면 0

구름을 이동시킬때 위로 이동하면 위로 이동하는 칸만큼 배열의 뒤로 가는 것이므로 배열을 잘라서 붙이는 식으로 이동처리

대각선 왼쪽 위 방향은 왼쪽 이동과 위쪽 이동을 동시에 하는 것이므로 상하좌우 이동만 구현해 방향에 맞게 이동처리

이렇게 이동처리한 cloud 배열을 이용해 구름 위치를 파악해 물 양을 증가시킴

cloud 배열 값이 1인 칸의 대각선 칸을 확인해 그 칸에 물이 있으면 물 양 증가시킴

전체 칸을 확인해 cloud 배열의 값이 1이면 구름을 없애야 하므로 0 대입, 배열에 값이 0이고 물의 양이 2 이상이면 1 대입해 구름 만들고 물의 양 2 감소

이를 모든 이동에 대해 처리하고 모든 칸의 물 양을 더해 출력하면 정답

### 풀이 설명

이동을 할 때 1번 행과 N번 행이 연결되어 있고, 1번 열과 N번 열을 연결한 상태이므로 한 행 내에서 이동하는 것은 행의 양 끝이 연결된 고리 형태에서 회전하는 것과 같다고 생각하면 된다. 그러므로 수직 또는 수평 방향으로 움직이는 것은 회전으로 나타낼 수 있고, 대각선으로 움직이는 방향은 수직 또는 수평 방향을 모두 움직이는 것으로 나타낼 수 있기 때문에 모든 방향을 상하좌우 이동의 조합으로 나타낼 수 있다.

2차원 배열을 회전하는 것은 회전하는 방향 끝쪽의 원소들을 잘라 반대편에 붙이는 방식으로 나타낼 수 있다. 왼쪽이 열의 인덱스 앞쪽이고 오른쪽이 인덱스 뒷쪽이므로 왼쪽은 열에서 앞쪽을 잘라 뒤에 붙이는 형식이 되고, 위쪽이 행의 인덱스 앞쪽이므로 위쪽은 행에서 앞쪽을 잘라서 뒤에 붙이는 방식이 된다.

이에 따라 moveCloud에서 d, s를 입력받으면 d를 확인해 상하좌우 중 어느 조합으로 움직여야 하는지 확인하고 각 방향으로 s만큼 이동처리를 한다. 그 이후 이동처리를 한 구름의 칸을 확인해 그 칸의 물을 증가시킨다.

이제 물이 증가한 칸에 물복사버그를 사용하기 위해 물이 증가한 칸이 구름이 있었던 칸이므로 다시 cloud 배열을 확인해 1일때만 그 칸의 대각선 칸을 확인해 물을 증가시킨다.

마지막으로 구름이 있던 칸은 구름이 사라지고 없었던 칸 중 물이 2 이상인 칸만 구름을 만들어야 하므로 cloud 배열을 확인해 1일때는 구름이 사라져야 하기 때문에 0을 대입하고, 0일때는 칸의 물을 확인해 2 이상이면 구름을 만들기 위해 cloud 배열에 1을 대입해주고 물을 2 감소시킨다.

이 과정을 모든 이동에 대해 반복하면 칸에 남아있는 물이 남아있기 때문에 모두 더해서 출력해주면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val A = Array(N){IntArray(N)}
    for(r in 0 until N){
        st = StringTokenizer(br.readLine())
        for(c in 0 until N){
            A[r][c] = st.nextToken().toInt()
        }
    }
    val cloud = Array(N){IntArray(N)}
    for(r in N - 2..N - 1){
        for(c in 0..1){
            cloud[r][c] = 1
        }
    }
    repeat(M){
        st = StringTokenizer(br.readLine())
        val d = st.nextToken().toInt()
        val s = st.nextToken().toInt()
        moveCloud(cloud, d, s)
        for(r in 0 until N){
            for(c in 0 until N){
                if(cloud[r][c] == 1){
                    A[r][c]++
                }
            }
        }
        //물 복사
        for(r in 0 until N){
            for(c in 0 until N){
                if(cloud[r][c] == 1){
                    var y = r - 1
                    var x = c - 1
                    if(y >= 0){
                        if(x >= 0 && A[y][x] > 0){
                            A[r][c]++
                        }
                        x = c + 1
                        if(x < N && A[y][x] > 0){
                            A[r][c]++
                        }
                    }
                    y = r + 1
                    x = c - 1
                    if(y < N){
                        if(x >= 0 && A[y][x] > 0){
                            A[r][c]++
                        }
                        x = c + 1
                        if(x < N && A[y][x] > 0){
                            A[r][c]++
                        }
                    }
                }
            }
        }
        //구름 있던 칸 제외 2이상인 칸만 구름
        for(r in 0 until N){
            for(c in 0 until N){
                if(cloud[r][c] == 1){
                    cloud[r][c] = 0
                } else if(A[r][c] >= 2) {
                    A[r][c] -= 2
                    cloud[r][c] = 1
                }
            }
        }
    }
    //전체 합 계산 후 출력
    var sum = 0
    for(r in 0 until N){
        for(c in 0 until N){
            sum += A[r][c]
        }
    }
    println(sum)
}

fun moveCloud(cloud: Array<IntArray>, d: Int, s: Int){
    if(d == 1 || d == 2 || d == 8){
        moveLeft(cloud, s)
    }
    if(d == 2 || d == 3 || d == 4){
        moveUp(cloud, s)
    }
    if(d == 4 || d == 5 || d == 6){
        moveRight(cloud, s)
    }
    if(d == 6 || d == 7 || d == 8){
        moveDown(cloud, s)
    }
}

fun moveLeft(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    for(i in 0 until cloud.size){
        cloud[i] = (cloud[i].drop(count) + cloud[i].take(count)).toIntArray()
    }
}

fun moveUp(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    val temp = (cloud.drop(count) + cloud.take(count)).toTypedArray()
    for(i in 0 until cloud.size){
        cloud[i] = temp[i]
    }
}

fun moveRight(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    for(i in 0 until cloud.size){
        cloud[i] = (cloud[i].takeLast(count) + cloud[i].dropLast(count)).toIntArray()
    }
}

fun moveDown(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    val temp = (cloud.takeLast(count) + cloud.dropLast(count)).toTypedArray()
    for(i in 0 until cloud.size){
        cloud[i] = temp[i]
    }
}
```