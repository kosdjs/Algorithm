package BOJ_5021

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val parent = HashMap<String, Int>()
    val score = HashMap<String, Double>()
    val son = HashMap<String, ArrayList<String>>()
    score[br.readLine()] = 1.0
    for(i in 1..N){
        st = StringTokenizer(br.readLine())
        val child = st.nextToken()
        val p1 = st.nextToken()
        val p2 = st.nextToken()
        if(!score.containsKey(child)){
            score[child] = 0.0
        }
        if(!score.containsKey(p1)){
            score[p1] = 0.0
        }
        if(!score.containsKey(p2)){
            score[p2] = 0.0
        }
        parent[child] = 2
        if(!parent.containsKey(p1)){
            parent[p1] = 0
        }
        if(!parent.containsKey(p2)){
            parent[p2] = 0
        }
        if(!son.containsKey(p1)){
            son[p1] = ArrayList()
        }
        if(!son.containsKey(p2)){
            son[p2] = ArrayList()
        }
        son[p1]!!.add(child)
        son[p2]!!.add(child)
    }
    val queue = ArrayDeque<String>()
    for(person in parent.keys){
        if (parent[person] == 0){
            queue.add(person)
        }
    }
    while(queue.isNotEmpty()){
        val person = queue.removeFirst()
        if(son.containsKey(person)){
            for(child in son[person]!!){
                parent[child]?.let { parent[child] = it - 1 }
                score[child]?.let { score[child] = it + score[person]!! }
                if(parent[child] == 0){
                    score[child]?.let { score[child] = it / 2.0 }
                    queue.add(child)
                }
            }
        }
    }
    var max = 0.0
    var answer = ""
    for(i in 1..M){
        val person = br.readLine()
        score[person]?.let {
            if(it > max){
                max = it
                answer = person
            }
        }
    }
    println(answer)
}