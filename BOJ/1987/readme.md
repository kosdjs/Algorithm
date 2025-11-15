# 백준 1987번: 알파벳

> 문제: https://www.acmicpc.net/problem/1987

### 문제 풀이

백트래킹, DFS, 비트마스킹

visitedBits[y][x] = 현재 칸에 방문했을 때 어떤 알파벳을 지나왔는지 나타내는 비트를 저장

bits = 현재 칸까지 방문한 알파벳들을 비트로 나타낸 값, 이진수의 자릿수 하나마다 알파벳 하나

알파벳은 26개이므로 BooleanArray를 쓰는 것 보다 Int에 비트마스킹을 하면 더 쉽게 나타낼 수 있음

좌측 상단 칸에서부터 DFS로 탐색할 때 그 칸의 알파벳을 사용했는지 여부를 현재 사용한 알파벳을 나타낸 bits와 그 칸의 알파벳을 비트로 나타낸 값 curBit을 and로 비교하면 그 칸에 이동할 수 있는지 알 수 있음

따라서 좌측 상단에서부터 DFS로 이동하면 얼마나 갈 수 있는지를 answer에 저장해 출력하면 정답

### 풀이 설명

세로 $R$칸, 가로 $C$칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 ($1$행 $1$열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

좌측 상단에서부터 다른 알파벳으로 어디까지 갈 수 있는지 확인하면 되는 문제이므로 다음 칸이 현재까지 사용하지 않은 알파벳일때만 탐색하는 DFS를 사용하면 얼마나 갈 수 있는지 알 수 있다.

따라서 재귀 함수 dfs(y, x, dist, bits)를 정의하는데 이때 bits는 현재까지 사용한 알파벳이 무엇인지 비트마스킹으로 나타낸 것이다. 비트마스킹은 이진수로 나타냈을 때 자릿수마다 알파벳이 있는지 없는지를 1과 0으로 나타내는 방법이다. 다시 말해 비트 값에 $2^i$가 있는지에 따라 i번째 원소가 있는지를 나타내는 방법이다.

이에 따라 다음 칸을 탐색할지 결정할 때 다음 칸의 원소에 따라 해당 비트가 2의 몇승인지 확인해 현재까지 사용한 알파벳을 나타낸 비트값과 and 연산을 했을 때 0이라면 해당 알파벳을 사용하지 않은 것이므로 해당 칸을 탐색하고 0이 아니라면 해당 알파벳을 이미 사용한 것이므로 그 칸을 탐색하지 않으면 된다.

이에 따라 DFS로 탐색을 하는데 격자를 상하좌우로 탐색하므로 이미 탐색한 칸을 다시 탐색할 가능성이 있다. 따라서 탐색 횟수를 줄이기 위해 해당 칸을 탐색했을 때의 비트 값을 저장하는 visitedBits 배열을 만든다. 그 칸을 탐색했을 때 배열에 현재 비트 값과 같은 값이 저장되어있다면 그 칸은 이미 탐색이 끝난 것이므로 함수를 반환해 탐색을 끝내면 된다.

이에 따라 좌측 상단에서부터 탐색을 해 최대 거리를 answer에 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

lateinit var map: Array<CharArray>
lateinit var visitedBits: Array<IntArray>
var R = 0
var C = 0
val dy = intArrayOf(0, -1, 0, 1)
val dx = intArrayOf(-1, 0, 1, 0)
var answer = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextString(): String{
        nextToken()
        return sval.toString()
    }
    R = nextInt()
    C = nextInt()
    map = Array(R){ CharArray(C) }
    visitedBits = Array(R){ IntArray(C) }
    for(i in 0 until R){
        val s = nextString()
        for(j in 0 until C){
            map[i][j] = s[j]
        }
    }
    dfs(0, 0, 1, 1 shl (map[0][0] - 'A'))
    println(answer)
}

fun dfs(y: Int, x: Int, dist: Int, bits: Int){
    if(visitedBits[y][x] == bits) return
    visitedBits[y][x] = bits
    if(answer < dist){
        answer = dist
    }
    for(k in 0 until 4){
        val ny = y + dy[k]
        val nx = x + dx[k]
        if(ny < 0 || ny >= R || nx < 0 || nx >= C){
            continue
        }
        val curBit = 1 shl (map[ny][nx] - 'A')
        if(curBit and bits == 0){
            dfs(ny, nx, dist + 1, bits or curBit)
        }
    }
}
```