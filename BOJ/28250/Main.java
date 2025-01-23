package BOJ_28250;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int zero = 0;
        int one = 0;
        int other = 0;
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i]==0){
                zero++;
            } else if(arr[i]==1){
                one++;
            } else {
                other++;
            }
        }
        long result = 0;
        for(int i=0;i<N;i++){
            if(arr[i]==0){
                zero--;
                result += zero + (one * 2) + other;
            } else if(arr[i]==1){
                one--;
                result += (zero * 2);
            } else{
                other--;
                result += zero;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
