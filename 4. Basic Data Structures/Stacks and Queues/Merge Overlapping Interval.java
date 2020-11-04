// 1. You are given a number n, representing the number of time-intervals.
// 2. In the next n lines, you are given a pair of space separated numbers.
// 3. The pair of numbers represent the start time and end time of a meeting (first number is start time and second number is end time)
// 4. You are required to merge the meetings and print the merged meetings output in increasing order of start time.

// E.g. Let us say there are 6 meetings
// 1 8
// 5 12
// 14 19
// 22 28
// 25 27
// 27 30

// Then the output of merged meetings will belongs
// 1 12
// 14 19
// 22 30

// Note -> The given input maynot be sorted by start-time.
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time
        Pair[] pair = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pair[i] = new Pair(arr[i][0], arr[i][1]);
        }
        Arrays.sort(pair);
        Stack<Pair> st = new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(i==0){
                st.push(pair[i]);
            }
            else{
                Pair top = st.peek();
                if(pair[i].st> top.et){
                    st.push(pair[i]);
                }
                else{
                    top.et = Math.max(pair[i].et,top.et);
                }
            }
        } 
        Stack <Pair> rs = new Stack<>();
        while(st.size()>0){
            rs.push(st.pop());
        }
        while( rs.size()>0 ){
            Pair p = rs.pop();
            System.out.println(p.st+" "+p.et);
        }

    }
    public static class Pair implements Comparable <Pair>{
        int st;
        int et;
        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }
        // this >other then return +ve
        // this == other then 0
        // this<othe then retrun -ve
        public int compareTo(Pair other){
            if( this.st !=other.st){
                return this.st-other.st;
            }
            else{
                return this.et-other.et;
            }
        }
    }

}