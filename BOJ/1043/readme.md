> 문제: https://www.acmicpc.net/problem/1043

### 문제 풀이

Union Find

배열에 연결되어있는 노드 중 가장 번호가 빠른 노드(루트 노드)의 번호를 저장

먼저 진실을 아는 사람들을 같은 집합에 있다고 나타내기 위해 루트 노드를 0으로 저장

파티에 참석하는 사람들을 순서대로 확인하면서 같은 집합에 있다는 것을 나타내기 위해 앞의 사람과 Union을 함

이렇게 모든 사람들의 관계를 나타낸 이후에 파티에 참석하는 사람들을 확인해 진실을 아는 사람과 같은 집합이 아닌 사람들만 오면 그때 정답에 1을 더함

이렇게 모든 파티에 대해 확인하면 정답이 나옴

### 풀이 설명

문제 조건에 따라 진실을 아는 사람들이 파티에 참석하면 진실을 말해야 하기 때문에 이 때 참석한 모든 사람들이 진실을 아는 사람이 된다고 생각하면 된다

이를 그래프적으로 생각하면 파티에 참석하는 모든 사람들이 서로 연결되는 것이고, 이에 따라 모든 파티의 경우를 확인해 그래프를 그리고 나서 파티 명단을 확인해 진실을 아는 사람과 연결되지 않은 정점들만 있는 파티의 경우 문제의 조건에 맞는 파티임으로 횟수를 세면 된다

따라서 Union Find를 이용해 진실을 아는 사람들을 먼저 연결하고, 그 뒤에 파티에 참석하는 인원들을 연결해서 연결관계를 확인하면 되는 문제이다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N+1){it}
    st = StringTokenizer(br.readLine())
    for(i in 1..st.nextToken().toInt()){
        val person = st.nextToken().toInt()
        union(root, person, 0)
    }
    var answer = 0
    val partyList = ArrayList<ArrayList<Int>>()
    for(i in 0 until M){
        st = StringTokenizer(br.readLine())
        partyList.add(arrayListOf())
        var condition = false
        for(j in 1..st.nextToken().toInt()){
            val person = st.nextToken().toInt()
            partyList[i].add(person)
            if(j != 1){
                union(root, person, partyList[i][j - 2])
            }
            if(find(root, person) == 0){
                condition = true
            }
        }
        if(condition){
            for(person in partyList[i]){
                union(root, person, 0)
            }
        }
    }
    for(list in partyList){
        var condition = true
        for(person in list){
            if(find(root, person) == 0){
                condition = false
                break
            }
        }
        if(condition) answer++
    }
    println(answer)
}

fun union(root: IntArray, a: Int, b: Int){
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind < bFind){
        root[bFind] = aFind
    } else if(aFind > bFind){
        root[aFind] = bFind
    }
}

fun find(root: IntArray, i: Int): Int{
    if(root[i] == i){
        return i
    } else {
        root[i] = find(root, root[i])
        return root[i]
    }
}
```