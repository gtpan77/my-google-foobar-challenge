public class Main {

    public static int divide(int query, int left, int right, int cur, int parent) {
        if(cur==query) {
            return parent;
        }
        int mid = (left+right)/2;
        System.out.println(left + " " + right + " " + mid + " " + cur + " " + " " + parent + " " + query);
        if(query<mid) return divide(query,left,mid-1,mid-1,cur);
        else return divide(query,mid,right-1,right-1,cur);
    }
    
    public static int[] solution(int h, int[] q) {
        int[] result = new int[q.length];
        int largest = (int)(Math.pow(2,h)) - 1;
        for(int i=0;i<q.length;i++) {
            result[i] = divide(q[i],1,largest,largest,-1);
        }
        return result;
    }
   
    public static void main(String[] args) {
        int[] in1 = {7,3,5,1};
        int[] out1 = solution(3,in1);
        for(int s: out1) {
            System.out.println(s);
        }
        int[] in2 = {19,14,28};
        int[] out2 = solution(5,in2);
        for(int s: out2) {
            System.out.println(s);
        }
    }
}

