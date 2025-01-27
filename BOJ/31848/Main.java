package BOJ_31848;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] hole = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int prev = 0;
        for(int i=1; i<=N; i++){
            int curr = Integer.parseInt(st.nextToken()) + i - 1;
            if(curr > prev) {
                prev = curr;
            }
            hole[i] = prev;
        }
        int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++){
            int s = Integer.parseInt(st.nextToken());
            int left = 1;
            int right = N;
            while(left < right){
                int mid = (left + right) / 2;
                if(hole[mid] < s){
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            bw.write(right + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}
