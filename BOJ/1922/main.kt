package BOJ_1922

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    val root = IntArray(N + 1){it}
    val edges = ArrayList<IntArray>()
    for(i in 1..M){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        edges.add(intArrayOf(a, b, c))
    }
    edges.sortWith{o1, o2 -> o1[2] - o2[2]}
    var count = 0
    var answer = 0
    for(edge in edges){
        if(find(root, edge[0]) != find(root, edge[1])){
            union(root, edge[0], edge[1])
            answer += edge[2]
            count++
        }
        if(count == N - 1) break
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

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return root[idx]
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}