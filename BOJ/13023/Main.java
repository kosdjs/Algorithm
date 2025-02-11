package BOJ_13023;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visit;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for(int i=0; i<N; i++){
            visit = new boolean[N];
            dfs(i, 1);
            if(result == 1){
                break;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static void dfs(int u, int depth){
        if(result == 1 || visit[u]){
            return;
        }
        visit[u] = true;
        if(depth >= 5){
            result = 1;
            return;
        }
        for(int i=0; i<graph.get(u).size(); i++){
            int v = graph.get(u).get(i);
            if(visit[v]){
                continue;
            } else {
                dfs(v, depth+1);
                visit[v] = false;
            }
        }
    }
}
