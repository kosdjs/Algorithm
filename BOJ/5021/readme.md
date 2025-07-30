# 백준 5021번: 왕위 계승

> 문제: https://www.acmicpc.net/problem/5021

### 문제 풀이

위상 정렬

parent[]를 부모 중 혈통 값이 확인되지 않은 사람의 수, score[]를 왕족 혈통 값, son[]을 자식 리스트로 만든 후 모든 부모가 혈통 값이 확인된 사람들을 큐에 넣고 한명씩 꺼내서 다음과 같은 단계를 진행함

1. 확인하는 사람의 자식이 있는지 확인함.
2. 자식이 있다면 현재 확인하는 사람은 혈통 값이 확인된 사람이므로 자식의 parent[] 값을 하나 줄임
3. 자식의 score[] 값에 본인의 score[] 값을 더해줌
4. 이 때 자식의 parent[] 값이 0이 되었으면 부모의 혈통 값이 둘 다 더해져있는 상태이므로 2로 나누어 혈통 값을 확인하고 자식을 큐에 넣음

이 단계를 거치면 score[] 에 모든 사람의 혈통 값이 들어가게 되고 왕위를 주장하는 사람들의 score[] 값을 확인해 최댓값인 사람을 출력하면 정답.

### 풀이 설명

모든 사람이 아버지의 혈통의 반, 어머니의 혈통의 반을 받는다고 했으니 혈통을 계산하려면 부모의 혈통을 먼저 알아야 한다.

그렇기 때문에 부모 자식 관계를 혈통을 확인하는 순서로 생각해보면 부모를 먼저 확인해야 자식의 혈통을 확인할 수 있기 때문에 위상 정렬로 풀 수 있다.

따라서 위상 정렬로 풀기 위해 부모가 먼저 혈통이 확인이 되었는지를 나타내는 parent[], 혈통 값을 나타내는 score[], 자식의 리스트를 나타내는 son[]을 각각 HashMap을 사용해 만든다. 이 때 맵을 사용하는 이유는 입력이 사람의 이름으로 주어지기 때문에 String을 인덱스처럼 사용하기 위해서이다.

그 후 parent[] 가 0인 사람들, 즉 부모의 혈통 값이 확인된 사람들은 본인의 혈통 값도 확인이 되어있는 상태이므로 앞서 써놓은 단계들을 거치면 모든 사람의 혈통 값을 확인할 수 있고, 이에 따라 왕위를 주장하는 사람들의 혈통 값을 확인해 제일 큰 값을 가진 사람을 출력하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val parent = HashMap<String, Int>()
    val score = HashMap<String, Double>()
    val son = HashMap<String, ArrayList<String>>()
    score[br.readLine()] = 1.0
    for(i in 1..N){
        st = StringTokenizer(br.readLine())
        val child = st.nextToken()
        val p1 = st.nextToken()
        val p2 = st.nextToken()
        if(!score.containsKey(child)){
            score[child] = 0.0
        }
        if(!score.containsKey(p1)){
            score[p1] = 0.0
        }
        if(!score.containsKey(p2)){
            score[p2] = 0.0
        }
        parent[child] = 2
        if(!parent.containsKey(p1)){
            parent[p1] = 0
        }
        if(!parent.containsKey(p2)){
            parent[p2] = 0
        }
        if(!son.containsKey(p1)){
            son[p1] = ArrayList()
        }
        if(!son.containsKey(p2)){
            son[p2] = ArrayList()
        }
        son[p1]!!.add(child)
        son[p2]!!.add(child)
    }
    val queue = ArrayDeque<String>()
    for(person in parent.keys){
        if (parent[person] == 0){
            queue.add(person)
        }
    }
    while(queue.isNotEmpty()){
        val person = queue.removeFirst()
        if(son.containsKey(person)){
            for(child in son[person]!!){
                parent[child]?.let { parent[child] = it - 1 }
                score[child]?.let { score[child] = it + score[person]!! }
                if(parent[child] == 0){
                    score[child]?.let { score[child] = it / 2.0 }
                    queue.add(child)
                }
            }
        }
    }
    var max = 0.0
    var answer = ""
    for(i in 1..M){
        val person = br.readLine()
        score[person]?.let {
            if(it > max){
                max = it
                answer = person
            }
        }
    }
    println(answer)
}
```