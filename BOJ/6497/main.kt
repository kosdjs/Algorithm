package BOJ_6497

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    while (true){
        var st = StringTokenizer(br.readLine())
        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()
        if(m == 0 && n == 0) break
        val root = IntArray(m){it}
        val graph = ArrayList<IntArray>()
        var answer = 0
        for(i in 1..n){
            st = StringTokenizer(br.readLine())
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            val z = st.nextToken().toInt()
            answer += z
            graph.add(intArrayOf(x, y, z))
        }
        graph.sortWith{ o1, o2 ->
            o1[2] - o2[2]
        }
        var min = 0
        var count = 0
        for(edge in graph){
            if(count == m - 1) break
            if(find(root, edge[0]) != find(root, edge[1])){
                min += edge[2]
                count++
                union(root, edge[0], edge[1])
            }
        }
        answer -= min
        println(answer)
    }
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