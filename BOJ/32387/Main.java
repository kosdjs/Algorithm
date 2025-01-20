package BOJ_32387;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 1; i <= N; i++){
            set.add(i);
        }
        for (int k = 1; k <= Q; k++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            if(t == 1){
                Integer port = set.ceiling(i);
                if(port == null){
                    bw.write(-1 + "\n");
                } else {
                    set.remove(port);
                    arr[port] = k;
                    bw.write(port + "\n");
                }
            } else if(t == 2){
                if(arr[i] != 0){
                    bw.write(arr[i] + "\n");
                    arr[i] = 0;
                    set.add(i);
                } else {
                    bw.write(-1 + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
