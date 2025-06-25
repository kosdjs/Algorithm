package BOJ_1043

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