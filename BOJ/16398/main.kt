package BOJ_16398

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val root = IntArray(N + 1){it}
    val graph = ArrayList<IntArray>()
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        for(j in 1..N){
            val flow = st.nextToken().toInt()
            if(i != j){
                graph.add(intArrayOf(i, j, flow))
            }
        }
    }
    graph.sortWith{ o1, o2 -> o1[2] - o2[2]}
    var answer = 0L
    var count = 0
    for(edge in graph){
        if(count == N - 1) break
        if(find(root, edge[0]) != find(root, edge[1])){
            answer += edge[2].toLong()
            count++
            union(root, edge[0], edge[1])
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