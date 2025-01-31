package BOJ_1766;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> countList = new ArrayList<>(); // 먼저 풀어야 하는 문제의 갯수
        ArrayList<ArrayList<Integer>> problemList = new ArrayList<>(); // 해당 문제를 풀고나서 풀면 좋은 문제를 저장
        for(int i=0; i<=N; i++){
            countList.add(new ArrayList<>());
            problemList.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            countList.get(B).add(A);
            problemList.get(A).add(B);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            if(countList.get(i).isEmpty()){
                pq.offer(i);
            }
        }
        while(!pq.isEmpty()){
            int current = pq.poll();
            bw.write(current + " ");
            for(int i=0; i<problemList.get(current).size(); i++){
                int next = problemList.get(current).get(i);
                countList.get(next).remove(Integer.valueOf(current));
                if(countList.get(next).isEmpty()){
                    pq.offer(next);
                }
            }
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}
