# 백준 16562번: 친구비

> 문제: https://www.acmicpc.net/problem/16562

### 문제 풀이

Union Find

A[i] = 학생 i와 친구가 되는 친구비

root[i] = 학생 i가 속해있는 친구 집단에서 번호가 가장 작은 학생

minMoney[i] = 학생 i가 속해있는 친구 집단에서 가장 적은 친구비

moneySum = 모든 학생과 친구가 되기 위한 친구비의 최소 합

Union Find를 이용해 모든 학생의 친구 집단을 root[i]로 나타냄

모든 학생에 대해 find 함수를 이용해 그 집단의 번호가 가장 작은 학생의 번호 curRoot를 구하고 minMoney[curRoot]가 존재하면 현재 학생의 친구비와 값을 비교해 더 작은 값을 저장하고 아니라면 minMoney[curRoot]에 현재 학생의 친구비를 저장함

위의 단계에 따라 minMoney에 친구 집단마다 가장 적은 친구비가 저장되어 있으므로 모든 값을 moneySum에 더해 모든 학생과 친구가 되기 위한 친구비의 최소 합을 구함

구한 moneySum과 k를 비교해 moneySum이 더 작거나 같다면 k원 이내로 모든 학생과 친구가 될 수 있는 것이므로 moneySum 출력, 크다면 Oh no를 출력하면 정답

### 풀이 설명

학생이 N명인 학교에 입학을 했다. 입학을 맞아 모든 학생과 친구가 되고 싶어한다. 학생 i에게 Ai만큼의 돈을 주면 그 학생은 1달간 친구가 되어준다. 총 k원의 돈이 있고 그 돈을 이용해서 친구를 사귀기로 했다. “친구의 친구는 친구다”를 이용하기로 했다.

위와 같은 논리를 사용했을 때, 가장 적은 비용으로 모든 사람과 친구가 되는 방법을 구하라.

친구의 친구는 친구다라는 뜻은 친구 관계를 그래프로 나타냈을 때 한 명의 친구와 연결된 모든 학생들은 친구로 생각한다는 뜻이므로 친구 집단중 한 명을 골라 친구로 삼아서 모든 친구 집단과 친구가 될 수 있는 최소 비용을 구하는 문제다.

먼저 친구 집단이 어떻게 되는지 파악을 해야 하므로 Union Find를 이용해 친구 집단에 속해 있는 학생 중 가장 번호가 빠른 학생을 그 친구 집단의 루트 노드로 삼아 모든 학생에 대해 그 학생의 친구 집단의 루트 노드를 찾도록 한다.

그 이후에 HashMap을 이용해 모든 루트 노드에 대해 가장 친구비가 적은 학생의 친구비를 저장한다. 그러면 HashMap에 저장된 모든 친구비의 합이 모든 학생과 친구가 될 수 있는 친구비의 최소 비용이므로 이 합을 k와 비교해 작거나 같다면 모든 학생과 친구가 가능한 것이므로 비용을 출력하면 되고 크다면 k원 이내로 모든 학생과 친구가 될 수 없는 것이므로 Oh no를 출력하면 정답이 된다.

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
    val k = nextInt()
    val root = IntArray(N + 1){it}
    val A = IntArray(N + 1)
    for(i in 1..N){
        A[i] = nextInt()
    }
    fun find(i: Int): Int{
        if(root[i] == i){
            return i
        } else {
            root[i] = find(root[i])
            return root[i]
        }
    }
    fun union(a: Int, b: Int){
        val aFind = find(a)
        val bFind = find(b)
        if(aFind > bFind){
            root[aFind] = bFind
        } else {
            root[bFind] = aFind
        }
    }
    repeat(M){
        val v = nextInt()
        val w = nextInt()
        union(v, w)
    }
    val minMoney = HashMap<Int, Int>()
    for(i in 1..N){
        val curRoot = find(i)
        if(minMoney.containsKey(curRoot)){
            minMoney[curRoot] = minOf(minMoney[curRoot]!!, A[i])
        } else {
            minMoney[curRoot] = A[i]
        }
    }
    var moneySum = 0
    for(money in minMoney.values){
        moneySum += money
    }
    println(if(k >= moneySum) moneySum else "Oh no")
}
```