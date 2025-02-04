package BOJ_2143;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int[] aSum = new int[(n * (n + 1)) / 2];
        for (int i=0; i<n; i++){
            int t=0;
            for(int j=i; j<n; j++){
                t += a[j];
                aSum[idx] = t;
                idx++;
            }
        }

        idx = 0;
        int[] bSum = new int[(m * (m + 1)) / 2];
        for (int i=0; i<m; i++){
            int t=0;
            for(int j=i; j<m; j++){
                t += b[j];
                bSum[idx] = t;
                idx++;
            }
        }

        Arrays.sort(aSum);
        Arrays.sort(bSum);

        long result = 0;
        int left = 0;
        int right = bSum.length - 1;
        while(left < aSum.length && right >= 0){
            int aCurrent = aSum[left];
            int bCurrent = bSum[right];
            int sum = aCurrent + bCurrent;
            if(sum > T){
                right--;
            } else if(sum < T){
                left++;
            } else {
                long aCount = 0;
                while(left < aSum.length && aSum[left] == aCurrent){
                    left++;
                    aCount++;
                }
                long bCount = 0;
                while(right >= 0 && bSum[right] == bCurrent){
                    right--;
                    bCount++;
                }
                result += aCount * bCount;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
