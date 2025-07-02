package BOJ_1647

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N + 1){it}
    val graph = ArrayList<IntArray>()
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val A = st.nextToken().toInt()
        val B = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        graph.add(intArrayOf(A, B, C))
    }
    graph.sortWith{o1, o2 -> o1[2] - o2[2]}
    var answer = 0
    var count = 0
    for(edge in graph){
        if(count == N - 2){
            break
        }
        if(find(root, edge[0]) != find(root, edge[1])){
            union(root, edge[0], edge[1])
            answer += edge[2]
            count++
        }
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