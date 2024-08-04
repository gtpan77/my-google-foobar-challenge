public class Main {    
    public static int solution(int[] l) {
        int n = l.length, ans = 0;
        int[] divisors = new int[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<i;j++) {
                if(l[i]%l[j] == 0) {
                    divisors[i] += 1;
                    ans += divisors[j];
                }
            }
        }
        return ans;
    }
   
    public static void main(String[] args) {
        
        int[] in1 = {1, 1, 1};
        System.out.println(solution(in1));
        int[] in2 = {1, 2, 3, 4, 5, 6};
        System.out.println(solution(in2));
        
    }
}

