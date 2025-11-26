# 백준 1991번: 트리 순회

> 문제: https://www.acmicpc.net/problem/1991

### 문제 풀이

트리, 재귀

find(cur, c) = cur 노드에서부터 문자 c를 가지는 노드를 찾는 함수

root = 루트 노드 (A)

입력 한 줄당 부모 노드, 왼쪽 자식 노드, 오른쪽 자식 노드가 주어지므로 이를 토대로 부모 노드를 루트 노드에서부터 찾은 후 왼쪽 자식 노드와 오른쪽 자식 노드를 연결해줌

입력으로 주어지는 트리를 구성한 후에 전위 순회는 루트 먼저, 왼쪽 서브트리, 오른쪽 서브트리 순서대로 출력하므로 재귀 함수를 구현할 때 현재 노드의 알파벳을 출력하고 왼쪽 노드, 오른쪽 노드 순서로 함수를 다시 호출하면 됨

중위 순회는 왼쪽 노드, 현재 노드 출력, 오른쪽 노드 순서대로, 후위 순회는 왼쪽 노드, 오른쪽 노드, 현재 노드 출력 순으로 재귀 함수를 구현하면 됨

이렇게 구현한 재귀함수를 이용해 전위, 중위, 후위 순회를 출력하면 정답

### 풀이 설명

이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.

첫째 줄에는 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다. 노드의 이름은 A부터 차례대로 알파벳 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현한다.

입력으로 주어지는 트리가 부모 노드, 왼쪽 자식 노드, 오른쪽 자식 노드 이런 식으로 나온다고 했으므로 왼쪽 자식 노드, 오른쪽 자식 노드를 가질 수 있는 Node 클래스를 만들어 트리를 구성한다.

또한 항상 A가 루트 노드라고 했으므로 루트 노드를 기준으로 트리를 구성한다. 그 이후 재귀 함수를 이용해 전위, 중위, 후위 순회를 하면서 노드의 알파벳을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

class Node(val c: Char){
    var left: Node? = null
    var right: Node? = null
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    wordChars('A'.code, 'Z'.code)
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextChar(): Char{
        val tokenType = nextToken()
        return if(tokenType == StreamTokenizer.TT_WORD) sval[0] else '.'
    }
    val N = nextInt()
    fun find(cur: Node, c: Char): Node?{
        var result: Node? = null
        if(cur.c == c){
            result = cur
        }
        if(cur.left != null){
            val left = find(cur.left!!, c)
            if(left != null){
                result = left
            }
        }
        if(cur.right != null){
            val right = find(cur.right!!, c)
            if(right != null){
                result = right
            }
        }
        return result
    }
    val root = Node('A')
    repeat(N){
        val cur = find(root, nextChar())!!
        val left = nextChar()
        if(left != '.'){
            cur.left = Node(left)
        }
        val right = nextChar()
        if(right != '.'){
            cur.right = Node(right)
        }
    }
    val bw = System.out.bufferedWriter()
    fun preorder(cur: Node){
        bw.append(cur.c)
        if(cur.left != null) preorder(cur.left!!)
        if(cur.right != null) preorder(cur.right!!)
    }
    fun inorder(cur: Node){
        if(cur.left != null) inorder(cur.left!!)
        bw.append(cur.c)
        if(cur.right != null) inorder(cur.right!!)
    }
    fun postorder(cur: Node){
        if(cur.left != null) postorder(cur.left!!)
        if(cur.right != null) postorder(cur.right!!)
        bw.append(cur.c)
    }
    preorder(root)
    bw.newLine()
    inorder(root)
    bw.newLine()
    postorder(root)
    bw.flush()
    bw.close()
}
```