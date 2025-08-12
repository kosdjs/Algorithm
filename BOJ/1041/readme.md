# 백준 1041번: 주사위

> 문제: https://www.acmicpc.net/problem/1041

### 문제 풀이

그리디

$min1 =$ 한 면의 최솟값

$min2 =$ 맞닿은 두 면의 최솟값

$min3 =$ 맞닿은 세 면의 최솟값

$answer = (min3 * 4) + (min2 * 4) + min2 * (N - 2) * 8 + min1 * (N - 2) * (N - 2) * 5 + min1 * (N - 2) * 4$ $(N > 1)$

$answer = \sum_{i = 0}^{5}dice[i] - max(dice[i])$ $(N = 1)$

### 풀이 설명

$N$이 $1,000,000$ 이하의 자연수라고 했으므로 $1$ 일때와 $2$ 이상일 때로 나뉜다.

$N$이 $1$일 때는 주사위를 하나 놓는 것이므로 $6$개의 면에 쓰여있는 값들 중 보이는 $5$개의 면의 합이 가장 작게 하려면 면에 쓰여있는 최댓값이 바닥으로 오게 놓으면 된다. 따라서 모든 면의 합에서 최댓값을 빼주면 된다.

$N$이 $2$ 이상일 때는 보이는 $5$개의 면의 합이 가장 작게 하려면 바깥에 두는 주사위를 최대한 숫자가 작은 면이 보이도록 배치해야 한다. 이 때 정육면체를 생각해보면 꼭짓점에 있는 주사위, 꼭짓점을 제외한 모서리에 있는 주사위, 보이는 면의 중앙에 있는 주사위로 나뉘게 된다.

먼저 정육면체를 쌓았을 때 정육면체의 보이는 면의 중앙에 있는 주사위들은 한 면만 보이면 되기 때문에 주사위의 한 면의 최솟값이 보이게 배치하면 된다. 이 주사위들을 개수를 세기 위해서 보이는 한 면에서 생각해 보면 $N \times N$개의 주사위가 있을 때 테두리에 있는 주사위를 제외하면 $(N - 2) \times (N - 2)$개가 되므로 한 면에 $(N - 2) \times (N - 2)$개가 있으니 개수는 이의 5배인 $(N - 2) \times (N - 2) \times 5$개가 된다.

꼭짓점에 있는 주사위의 경우 주사위의 세 면이 보이기 때문에 보이는 세 면의 합이 최솟값이 되게 배치해야 한다. 개수는 정육면체라 8개가 있지만 여기서 주의해야 할 점은 바닥에 있는 주사위는 바닥에 닿는 면이 보이지 않기 때문에 두 면만 보이게 된다. 따라서 세 면이 보이는 주사위 $4$개, 두 면이 보이는 주사위 $4$개로 나뉘게 된다.

꼭짓점을 제외한 모서리에 있는 주사위들은 두 면이 보이기 때문에 보이는 두 면의 합이 최솟값이 되게 배치해야 한다. 개수는 한 선분에서 양 꼭짓점에 있는 두 개의 주사위를 제외한 $(N - 2)$개가 되고 정육면체가 $12$개의 선분이 있으므로 $(N - 2) \times 12$개지만 이 때에도 바닥에 닿는 주사위들은 바닥에 닿는 면이 보이지 않으므로 바닥에 닿는 선분 4개에 위치한 주사위들은 한 면만 보이게 된다. 따라서 두 면이 보이는 주사위 $(N - 2) \times 8$개와 한 면이 보이는 주사위$(N - 2) \times $개가 된다.

따라서 주사위들의 개수를 알았기 때문에 $min1$을 한 면의 최솟값, $min2$를 맞닿은 두 면의 최솟값, $min3$를 맞닿은 세 면의 최솟값으로 정의하고 값을 구한다. 구한 값들을 개수에 맞게 곱해서 더해주면 정답이 나온다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val dice = IntArray(6)
    var min1 = Long.MAX_VALUE
    var min2 = Long.MAX_VALUE
    var min3 = Long.MAX_VALUE
    for(i in 0 until 6){
        dice[i] = st.nextToken().toInt()
        min1 = minOf(min1, dice[i].toLong())
    }
    val twoSides = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, 2),
        intArrayOf(0, 3),
        intArrayOf(0, 4),
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 5),
        intArrayOf(2, 4),
        intArrayOf(2, 5),
        intArrayOf(3, 4),
        intArrayOf(3, 5),
        intArrayOf(4, 5)
    )
    val threeSides = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(0, 1, 3),
        intArrayOf(0, 2, 4),
        intArrayOf(0, 3, 4),
        intArrayOf(5, 1, 2),
        intArrayOf(5, 1, 3),
        intArrayOf(5, 2, 4),
        intArrayOf(5, 3, 4)
    )
    for(arr in twoSides){
        var sum = 0
        for(i in arr){
            sum += dice[i]
        }
        min2 = minOf(min2, sum.toLong())
    }
    for(arr in threeSides){
        var sum = 0
        for(i in arr){
            sum += dice[i]
        }
        min3 = minOf(min3, sum.toLong())
    }
    if(N > 1){
        val answer = (min3 * 4) + (min2 * 4) + min2 * (N - 2) * 8 + min1 * (N - 2) * (N - 2) * 5 + min1 * (N - 2) * 4
        println(answer)
    } else {
        var max = 0
        var answer = 0
        for(i in 0 until 6){
            answer += dice[i]
            max = maxOf(max, dice[i])
        }
        answer -= max
        println(answer)
    }
}
```