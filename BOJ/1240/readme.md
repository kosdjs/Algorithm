# 백준 1240번: 노드사이의 거리

> 문제: https://www.acmicpc.net/problem/1240

### 문제 풀이

트리, LCA

depth[i] = 트리에서 i번째 노드의 깊이(레벨)

parent[i][k] = i번째 노드의 $2^k$번째 부모 노드

dist[i] = i번째 노드에서 루트 노드까지의 거리

1번 노드를 루트 노드로 두고 모든 노드까지의 거리를 구하고 부모 노드 정보를 저장한다.

LCA 알고리즘을 사용해 a, b의 공통 조상을 ancestor라고 하면 a에서 b까지의 거리는 dist[a] + dist[b] - 2 * dist[ancestor]이므로 이를 출력하면 정답

### 풀이 설명

$N$개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

입력에 트리가 주어진다고 했으므로 두 노드 a, b가 주어진다고 하면 a에서 b까지 가려면 a, b의 공통 조상을 거치는 길밖에 없다. 따라서 a, b 사이의 거리는 a에서 공통 조상까지 가는 거리와 b에서 공통 조상까지 가는 거리로 나눌 수 있다.

그리고 a에서 공통 조상까지 가는 거리는 루트 노드에서 a까지 가는 거리에서 루트 노드에서 공통 조상까지 가는 거리를 뺀 거리와 같다. 이와 같이 b에서 공통 조상까지 가는 거리를 루트 노드에서 b까지 가는 거리에서 루트 노드에서 공통 조상까지 가는 거리를 뺀 거리로 나타낼 수 있으므로 루트 노드에서 모든 노드까지 거리를 구해놓으면 공통 조상만 구하면 두 노드 사이의 거리를 쉽게 구할 수 있다.

입력으로 트리가 주어진다고 했고 어떤 노드가 루트 노드가 되어도 상관이 없으므로 1번 노드를 루트 노드로 두고 dfs를 통해 루트 노드에서 i번까지의 거리 dist[i], i번째 노드의 깊이 depth[i], i번째 노드의 부모 parent[i][0]을 구한다.

그 이후에 $2^k$번째 부모 노드를 구하기 위해 parent[i][k]에 parent[parent[i][k - 1]][k  1]을 대입해준다. 그 이후 주어지는 노드 쌍 a, b에 대해 LCA 알고리즘을 사용해 공통 조상 ancestor를 구하고 dist[a] + dist[b] - 2 * dist[ancestor]를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

const val MAX = 1001
const val LOG = 11 // 2^10 = 1024 >= 1000 (N 최대치)

var N = 0
val tree = Array(MAX) { ArrayList<Pair<Int, Int>>() }
val depth = IntArray(MAX)
val parent = Array(MAX) { IntArray(LOG) }
val dist = IntArray(MAX) // 루트부터 누적 거리

fun dfs(node: Int, par: Int, d: Int) {
    depth[node] = d
    for ((next, w) in tree[node]) {
        if (next != par) {
            parent[next][0] = node
            dist[next] = dist[node] + w
            dfs(next, node, d + 1)
        }
    }
}

fun initParents() {
    for (k in 1 until LOG) {
        for (v in 1..N) {
            parent[v][k] = parent[parent[v][k - 1]][k - 1]
        }
    }
}

fun lca(a: Int, b: Int): Int {
    var u = a
    var v = b
    if (depth[u] < depth[v]) {
        val tmp = u
        u = v
        v = tmp
    }
    // 깊이 맞추기
    for (k in LOG - 1 downTo 0) {
        if (depth[u] - depth[v] >= (1 shl k)) {
            u = parent[u][k]
        }
    }
    if (u == v) return u
    // 공통 조상 찾기
    for (k in LOG - 1 downTo 0) {
        if (parent[u][k] != parent[v][k]) {
            u = parent[u][k]
            v = parent[v][k]
        }
    }
    return parent[u][0]
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    repeat(N - 1) {
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        tree[u].add(Pair(v, w))
        tree[v].add(Pair(u, w))
    }

    dfs(1, 0, 0)
    initParents()

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val ancestor = lca(a, b)
        val answer = dist[a] + dist[b] - 2 * dist[ancestor]
        bw.write("$answer\n")
    }
    bw.flush()
    bw.close()
}
```