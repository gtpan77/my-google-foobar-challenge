public class Main {

    public static List<List<Integer>> generatePermutations(int[] array, int size) {
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] used = new boolean[array.length];
        generatePermutationsHelper(array, size, new ArrayList<>(), used, permutations);
        return permutations;
    }

    private static void generatePermutationsHelper(int[] array, int size, List<Integer> currentPermutation, boolean[] used, List<List<Integer>> result) {
        if (currentPermutation.size() == size) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (!used[i]) {
                used[i] = true;
                currentPermutation.add(array[i]);
                generatePermutationsHelper(array, size, currentPermutation, used, result);
                currentPermutation.remove(currentPermutation.size() - 1);
                used[i] = false;
            }
        }
    }

    public static int[] solution(int[][] times, int times_limit) {
        int n = times.length, bunnies = times.length-2;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (times[i][k] + times[k][j] < times[i][j])
                        times[i][j] = times[i][k] + times[k][j];
                }
            }
        }
        
        for(int i=0;i<n;i++) {
            if(times[i][i]<0) {
                int[] array = IntStream.range(0, bunnies).toArray();
                return array;
            }
        }
        
        for(int size=bunnies;size>=0;size--) {
            int[] array = IntStream.range(1, bunnies+1).toArray();
            List<List<Integer>> permutations = generatePermutations(array, size);
            for (List<Integer> permutation : permutations) {
                int totalTime = 0;
                permutation.add(0,0);
                permutation.add(n-1);
                for(int i=1;i<permutation.size();i++) {
                    totalTime += times[permutation.get(i-1)][permutation.get(i)];
                }
                if(totalTime<=times_limit) {
                    int[] ans = new int[size];
                    for(int i=1;i<permutation.size()-1;i++) {
                        ans[i-1] = permutation.get(i) - 1;
                    }
                    return ans;
                }
            }
        }
        int[] ans = {};
        return ans;
    }
   
    public static void main(String[] args) {
        
        int[][] in1 = {{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}};
        int[] out1 = solution(in1,3);
        for(int s: out1) {
            System.out.println(s);
        }
        int[][] in2 = {{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}};
        int[] out2 = solution(in2,1);
        for(int s: out2) {
            System.out.println(s);
        }
    }
}

