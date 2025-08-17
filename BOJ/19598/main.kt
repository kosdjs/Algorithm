package BOJ_19598

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val meetings = Array(N){ IntArray(2) }
    for(i in 0 until N){
        val st = StringTokenizer(readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        meetings[i] = intArrayOf(start, end)
    }
    meetings.sortWith { o1, o2 -> o1[0] - o2[0] }
    val meetingRoom = PriorityQueue<IntArray>{ o1, o2 -> o1[1] - o2[1]}
    meetingRoom.add(meetings[0])
    for(i in 1 until N){
        val prevEnd = meetingRoom.peek()[1]
        val curStart = meetings[i][0]
        if(curStart >= prevEnd){
            meetingRoom.poll()
        }
        meetingRoom.add(meetings[i])
    }
    println(meetingRoom.size)
}