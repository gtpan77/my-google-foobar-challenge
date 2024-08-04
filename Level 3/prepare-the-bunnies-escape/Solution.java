public class Main {

    public static int bfs(int[][] map) {
        int[] ROW_DIRECTIONS = {-1, 0, 1, 0};
        int[] COL_DIRECTIONS = {0, 1, 0, -1};
        int n = map.length, m = map[0].length;
        boolean[][] visited = new boolean[map.length][map[0].length];
        int[][] distance = new int[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        distance[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];

            if (currentRow == n-1 && currentCol == m-1) {
                return distance[currentRow][currentCol];
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + ROW_DIRECTIONS[i];
                int newCol = currentCol + COL_DIRECTIONS[i];

                if (isValid(newRow, newCol, n, m) && !visited[newRow][newCol] 
                    && map[newRow][newCol] == 0) {
                    queue.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    distance[newRow][newCol] = distance[currentRow][currentCol] + 1;
                }
            }
        }
        return 10000;
    }
    
    private static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    
    public static int solution(int[][] map) {
        int n = map.length, m = map[0].length, ans;
        ans = bfs(map);
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] == 1) {
                    map[i][j] = 0;
                    ans = Math.min(ans,bfs(map));
                    map[i][j] = 1;
                }
            }
        }
        return ans;
    }
   
    public static void main(String[] args) {
        int[][] in1 = {{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}};
        System.out.println(solution(in1));
        int[][] in2 = {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};
        System.out.println(solution(in2));
    }
}

