package BOJ_1197

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()
    val root = IntArray(V + 1){it}
    val edges = ArrayList<IntArray>()
    for(i in 1..E){
        st = StringTokenizer(br.readLine())
        val A = st.nextToken().toInt()
        val B = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        edges.add(intArrayOf(A, B, C))
    }
    var answer = 0
    edges.sortWith{ o1, o2 ->
        o1[2] - o2[2]
    }
    for(edge in edges){
        if(find(root, edge[0]) != find(root, edge[1])){
            answer += edge[2]
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