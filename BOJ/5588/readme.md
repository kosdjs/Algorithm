# 백준 5588번: 별자리 찾기

> 문제: https://www.acmicpc.net/problem/5588

### 문제 풀이

브루트포스, 집합과 맵

stars = 별자리에 존재하는 별의 좌표를 저장한 List

picture = 사진에 존재하는 별의 좌표를 저장한 Set

diffX, Y = 사진에 존재하는 별의 좌표와 별자리의 한 별의 좌표 차이

사진에 존재하는 별의 좌표마다 별자리에 존재하는 한 별과의 좌표 차이를 diffX, diffY에 저장함

별자리에 존재하는 모든 별에 대해 diffX, Y만큼 평행이동 시키고 이 별들이 모두 사진에 있는지 확인함

모두 있다면 별자리를 diffX, Y만큼 이동시키면 사진에 존재하는 것이므로 이를 출력하면 정답

### 풀이 설명

찾고 싶은 별자리의 모양과, 사진에 찍힌 별의 위치가 주어졌을 때, 별자리 좌표를 사진 속 좌표로 변환하기 위해 변환해야 하는 양을 구하는 프로그램을 작성해야 한다.

별자리의 좌표에서 얼마나 평행이동을 해야 사진에 찍힌 좌표가 나오는지를 구해야 하므로 별자리를 평행이동 시킨 좌표가 전부 사진에 있는지를 구해야 한다.

평행이동을 해야 하는 양을 정하는 것은 별자리의 좌표와 사진에 찍힌 별과의 좌표 차이를 구하면 된다. 그 이후에 별자리의 모든 별을 그만큼 평행이동 시킨 좌표가 사진에 있는지를 구하면 된다.

이때 평행시킨 좌표가 사진에 존재하는지 여부를 구하는 방법을 생각해봐야 하는데 일반적으로 사진에 존재하는 좌표를 리스트에 저장하면 리스트에 존재하는 좌표의 개수만큼 반복을 해야하므로 오래걸린다. 리스트가 아닌 집합 Set을 이용하면 그 좌표가 집합에 존재하는지 더 빠르게 구할 수 있으므로 집합을 사용해야 한다.

따라서 별자리는 모든 좌표를 확인해야 하므로 List, 사진에 찍힌 좌표는 존재여부만 확인하면 되므로 Set으로 저장을 하고 사진에 찍힌 별의 좌표를 하나씩 꺼내보며 별자리의 한 별과 좌표 차이를 구한 이후 그 좌표차이로 평행이동을 한 모든 좌표가 사진에 있을때 평행이동한 양을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val m = nextInt()
    val stars = mutableListOf<Pair<Int, Int>>()
    repeat(m){
        stars.add(Pair(nextInt(), nextInt()))
    }
    val n = nextInt()
    val picture = mutableSetOf<Pair<Int, Int>>()
    repeat(n){
        picture.add(Pair(nextInt(), nextInt()))
    }
    for((x, y) in picture){
        val diffX = x - stars[0].first
        val diffY = y - stars[0].second
        var match = true
        for((nx, ny) in stars){
            if(Pair(nx + diffX, ny + diffY) !in picture){
                match = false
                break
            }
        }
        if(match){
            println("$diffX $diffY")
        }
    }
}
```