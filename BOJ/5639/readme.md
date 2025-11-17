# 백준 5639번: 이진 검색 트리

> 문제: https://www.acmicpc.net/problem/5639

### 문제 풀이

재귀, 트리

list = 이진 검색 트리를 전위 순회한 결과

buildBST(start, end) = list[start]부터 list[end]까지의 이진 검색 트리를 만들고 루트 노드를 반환하는 재귀함수

postTraversal(root) = 트리를 후위 순회한 결과를 한줄에 하나씩 출력하는 재귀 함수

이진 검색 트리를 전위 순회하면 첫 값이 루트 노드, 그 이후에 루트 노드의 값보다 작은 모든 숫자가 루트 노드의 왼쪽 서브트리를 전위 순회한 결과, 그 뒤에 루트 노드의 값보다 큰 모든 숫자가 루트 노드의 오른쪽 서브트리를 전위 순회한 결과이므로 재귀 함수를 이용해 이진 검색 트리를 복원함

복원한 이진 검색 트리를 후위 순회한 결과를 출력하면 정답

### 풀이 설명

이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.

- 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
- 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
- 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.

전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다.

이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.

이진 검색 트리의 정의를 전위 순회한다면 첫 노드가 루트 노드이고, 왼쪽 서브트리를 전위 순회한 결과, 오른쪽 서브트리를 전위 순회한 결과로 나뉘게 된다.

문제의 예 50 30 24 5 28 45 98 52 60을 살펴보면 50이 루트 노드이고 30부터 45까지가 왼쪽 서브트리를 전위 순회한 결과, 98부터 60까지가 오른쪽 서브트리를 전위 순회한 결과로 나뉘게 된다.

또한 이진 검색 트리의 왼쪽, 오른쪽 서브트리도 이진 검색 트리이기 때문에 왼쪽, 오른쪽 서브트리를 전위 순회한 결과도 루트 노드, 왼쪽 서브트리를 전위 순회한 결과, 오른쪽 서브트리를 전위 순회한 결과로 나뉘게 된다.

따라서 리스트의 시작 인덱스, 끝 인덱스를 입력받아 루트 노드, 왼쪽 서브트리, 오른쪽 서브트리를 나누어서 이진 검색 트리를 복원하는 재귀함수를 만들어 이진 검색 트리를 복원할 수 있다.

그 이후에 트리를 후위 순회한 결과를 출력하는 재귀함수를 만들어 복원한 이진 검색 트리를 후위 순회한 결과를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

class Node(val value: Int){
    var left: Node? = null
    var right: Node? = null
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int?{
        if(nextToken() != StreamTokenizer.TT_EOF){
            return nval.toInt()
        } else {
            return null
        }
    }
    var num = nextInt()
    val list = ArrayList<Int>()
    while(num != null){
        list.add(num)
        num = nextInt()
    }
    fun buildBST(start: Int, end: Int): Node? {
        if(start > end){
            return null
        }
        val curValue = list[start]
        val cur = Node(curValue)
        var rightSubTreeStart = start + 1
        while (rightSubTreeStart <= end && list[rightSubTreeStart] < curValue){
            rightSubTreeStart++
        }
        cur.left = buildBST(start + 1, rightSubTreeStart - 1)
        cur.right = buildBST(rightSubTreeStart, end)
        return cur
    }
    val root = buildBST(0, list.size - 1)
    val bw = System.out.bufferedWriter()
    fun postTraversal(cur: Node?){
        if(cur == null){
            return
        }
        postTraversal(cur.left)
        postTraversal(cur.right)
        bw.write(cur.value.toString())
        bw.newLine()
    }
    postTraversal(root)
    bw.flush()
    bw.close()
}
```