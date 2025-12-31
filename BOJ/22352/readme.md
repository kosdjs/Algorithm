# 백준 22352번: 항체 인식

> 문제: https://www.acmicpc.net/problem/22352

### 문제 풀이

BFS

before = 백신을 놓기 전의 촬영 결과

after = 백신을 놓은 후의 촬영 결과

visit = before, after를 비교했을 때 다른 칸을 방문했는지 여부

diffCount = before, after를 비교했을 때 다른 영역의 개수

other = before, after를 비교했을 때 다른 영역을 발견했을 때 before의 같은 영역이 모두 변하지 않았는지 여부

before, after의 모든 칸을 확인해 먼저 달라진 칸을 찾으면 diffCount 1 증가함

찾은 칸에서부터 BFS를 이용해 before에서 같은 영역을 찾는 동시에 after의 칸을 확인하며 모든 칸이 동일하게 바뀌었는지 확인해 other에 반영함

모든 칸을 확인한 이후 other가 true이거나 diffCount가 1을 넘는다면 NO, 아니라면 YES를 출력하면 정답

### 풀이 설명

VUNO는 빅데이터와 딥러닝 기술을 통해 학습한 인공지능을 이용해 의학 전문가들의 판단에 도움을 주는 Medical AI 솔루션을 개발하는 전문 기업이다.

VUNO는 최근 SP라는 강력한 새로운 촬영 기법을 개발했다. 이 기법을 사용하면 인체 조직이 격자 형태로 표현되고, 격자의 각 칸에는 해당 부분의 각종 분석 결과를 압축한 하나의 데이터 값이 부여된다. VUNO는 이 SP 촬영 기법을 사용해 CPCU-1202라는 새로운 항체를 연구하려고 한다.

조직에 CPCU-1202 백신을 놓으면, 격자의 칸 중 하나에 항체가 생성된다. 이 항체는 현재 속해 있는 칸과 같은 데이터 값을 가지면서 상하좌우로 인접한 칸이 있을 경우 그 칸으로 퍼져나간다. 이 과정을 계속 반복하다가 항체가 더 이상 퍼져나갈 수 없게 되면, 항체는 조직에 완전히 스며든다. 그 결과로, 항체가 퍼졌던 칸들의 데이터 값은 모두 어떤 동일한 값으로 새로 업데이트된다. 이때, 우연히 원래의 데이터 값과 업데이트된 데이터 값이 동일할 수도 있다.

VUNO의 연구 데이터는 하나의 조직에 백신을 놓기 전의 촬영 결과와 백신을 놓은 뒤의 촬영 결과가 한 쌍으로 이루어져 있다. 두 장의 촬영 결과가 주어질 때, 이 조직에 놓은 백신이 CPCU-1202 백신일 가능성이 있는지 판단하는 프로그램을 작성하라.

CPCU-1202 백신이 맞는지 판단하려면 놓기 전의 촬영 결과와 놓은 뒤의 촬영 결과를 비교해 달라진 영역이 1개가 맞는지 확인하면 된다. 여기서 바뀐 영역이 1개가 맞아도 놓기 전의 촬영 결과에서 한 영역으로 되어있던 모든 칸이 동시에 바뀌지 않았다면 찾는 백신이 아닐 수 있다.

따라서 놓기 전의 영역을 before, 놓은 후의 영역을 after에 저장하고 before와 after를 한 칸씩 비교하며 다른 칸이 생기면 before에서 그 칸이 속한 영역을 모두 탐색하며 after와 모든 칸을 비교해 전부 같은 영역으로 바뀌었는지 확인해야 한다. 만약 before에서 같은 영역을 탐색하는 도중에 after와 비교했을 때 바뀌지 않은 칸이 있다면 찾는 백신이 아닌 다른 백신인 것이므로 other에 다른 백신이라는 표시로 true를 대입한다.

그리고 바뀐 영역이 1개보다 많다면 역시 찾는 백신이 아닐 수 있으므로 바뀐 영역이 총 몇 개인지도 셀 필요가 있다. 그러므로 바뀐 영역을 찾을 때마다 diffCount를 증가시켜야 한다. 그렇게 모든 칸을 비교하면 우리가 찾는 백신일 경우 other가 false이고, diffCount가 1 이하여야 한다.

따라서 other와 diffCount의 값에 따라 YES, NO를 출력하면 정답이 된다.

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
    val before = Array(N){IntArray(M)}
    val after = Array(N){IntArray(M)}
    val visit = Array(N){BooleanArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            before[i][j] = nextInt()
        }
    }
    for(i in 0 until N){
        for(j in 0 until M){
            after[i][j] = nextInt()
        }
    }
    var diffCount = 0
    var other = false // 백신 이전의 한 영역 전체가 다른 값으로 변하지 않으면
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    val queue = ArrayDeque<IntArray>()
    for(i in 0 until N){
        for(j in 0 until M){
            if(visit[i][j] || before[i][j] == after[i][j]) continue
            visit[i][j] = true
            diffCount++
            queue.add(intArrayOf(i, j))
            while(queue.isNotEmpty()){
                val (y, x) = queue.removeFirst()
                for(k in 0 until 4){
                    val ny = y + dy[k]
                    val nx = x + dx[k]
                    if(ny >= 0 && ny < N && nx >= 0 && nx < M && !visit[ny][nx]){
                        if(before[i][j] == before[ny][nx]){
                            if(after[i][j] != after[ny][nx]){
                                other = true
                                break
                            } else {
                                visit[ny][nx] = true
                                queue.add(intArrayOf(ny, nx))
                            }
                        }
                    }
                }
                if(other) break
            }
            if(other) break
        }
        if(other) break
    }
    println(if(other || diffCount > 1) "NO" else "YES")
}
```