> 문제: https://www.acmicpc.net/problem/1717

### 문제 풀이

Union Find

배열에 연결되어있는 노드 중 가장 번호가 빠른 노드(루트 노드)의 번호를 저장

합칠 때 루트 노드를 비교해 더 번호가 작은 노드를 루트 노드로 잡음

루트 노드를 찾을 때 배열에 값을 저장해 루트 노드를 바로 찾을 수 있게 함

### 풀이 설명

이 문제를 그래프로 나타내면 집합은 연결된 그래프이고, 합집합은 그래프 사이를 연결하는 것, 두 원소가 같은 집합인지 확인하는 것은 두 노드가 연결되어있는지 여부가 된다

![](https://velog.velcdn.com/images/kosdjs/post/3cba0ca4-a955-4e19-886f-8cd931311237/image.png)

위와 같이 N이 4일 때의 상황에서 한 번 생각해보면 초기 상태는 이럴 것이다

이 때 연결된 그래프의 노드 중 가장 빠른 번호를 저장해놓은 배열이 있다고 하면 처음에 연결이 되어있지 않을 때는 본인 노드가 그래프 중 가장 빠른 번호이기 때문에 본인 노드의 값을 가지고 있을 것이다

0|1|2|3|4
|:-:|:-:|:-:|:-:|:-:|
0|1|2|3|4

이 때 0번과 1번을 합쳐보면 그래프는 다음과 같이 될 것이다

![](https://velog.velcdn.com/images/kosdjs/post/b3822870-3ec9-485e-ad9b-d6565b698eb2/image.png)

이 때 배열은 1번 노드가 0번 노드와 연결되었기 때문에 연결된 노드 중 가장 빠른 번호인 0을 저장할 것이다

0|1|2|3|4
|:-:|:-:|:-:|:-:|:-:|
0|0|2|3|4

이 상황에서 1번 노드와 2번 노드를 연결해보면 다음과 같이 될 것이다

![](https://velog.velcdn.com/images/kosdjs/post/595ab0b1-06af-4eee-8cf1-46a2c726faad/image.png)

이때 배열을 바꾸려면 배열에 저장된 값을 확인할 텐데 단순히 연결된 노드끼리만 생각하면 배열이 다음과 같이 될것이다

0|1|2|3|4
|:-:|:-:|:-:|:-:|:-:|
0|0|1|3|4

하지만 이는 1번이 0번과 연결되어있다는 것을 모르기 때문에 일어난 일이고 처음 가정했던 배열의 조건을 만족시키려면 1번이 어디에 연결되어있는지를 확인하면 된다. 이는 배열에 저장되어 있는 값 0을 통해 확인할 수 있다

따라서 배열에 저장된 값을 확인해 연결된 노드 중 가장 빠른 번호가 나올때까지 재귀적으로 확인해야 한다는 점을 알 수 있다

0|1|2|3|4
|:-:|:-:|:-:|:-:|:-:|
0|0|0|3|4

그렇게 확인한 후 2번 노드의 배열 값도 0을 저장하게 된다

즉, 두 노드가 연결되어 있는지 매번 그래프를 탐색할 필요 없이 그래프마다 대표 노드를 하나씩 놓아서 그 대표 노드가 같은지를 확인하면 같은 그래프에 속해있는지 확인할 수 있다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = IntArray(N + 1){it}
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val op = st.nextToken().toInt()
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        if(op == 0){
            union(arr, a, b)
        } else {
            if(find(arr, a) == find(arr, b)){
                println("YES")
            } else {
                println("NO")
            }
        }
    }
}

fun find(arr: IntArray, idx: Int): Int{
    return if(arr[idx] == idx){
        idx
    } else {
        arr[idx] = find(arr, arr[idx])
        arr[idx]
    }
}

fun union(arr: IntArray, a: Int, b: Int){
    val aFind = find(arr, a)
    val bFind = find(arr, b)
    if(aFind < bFind){
        arr[bFind] = aFind
    } else if(aFind > bFind){
        arr[aFind] = bFind
    }
}
```