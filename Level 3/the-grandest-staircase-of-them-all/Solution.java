public class Main {

    public static int solve(int[][] dp, int height, int bricksLeft) {
        if(dp[height][bricksLeft] != 0) return dp[height][bricksLeft];
        if(bricksLeft == 0) return 1;
        if(bricksLeft < height) return 0;
        int value = solve(dp,height+1,bricksLeft-height) + 
            solve(dp,height+1,bricksLeft);
        dp[height][bricksLeft] = value;
        return dp[height][bricksLeft];
    }
    
    public static int solution31(int n) {
        int[][] dp = new int[202][202];
        return solve(dp,1,n) - 1;
    }
   
    public static void main(String[] args) {
        
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
        System.out.println(solution(200));
        
    }
}

