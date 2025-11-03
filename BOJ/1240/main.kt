package BOJ_1240

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