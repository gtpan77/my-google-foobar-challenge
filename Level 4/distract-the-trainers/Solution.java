public class Main {
    
    public static boolean loop(int x, int y) {
        int base = (x+y) / gcd(x,y);
        return (base&(base-1)) != 0;
    }
    
    public static int gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }
    
    public static Map<Integer, List<Integer>> generateGraph(int[] l) {
        Map<Integer, List<Integer>> G = new HashMap<>();
        for(int i=0;i<l.length;i++) {
            G.put(i, new ArrayList<>());
        }
        
        for(int i=0;i<l.length;i++) {
            for(int j=0;j<l.length;j++) {
                if(i!=j && loop(l[i],l[j])) {
                    G.get(i).add(j);
                    G.get(j).add(i);
                }
            }
        }
        return G;
    }
    
    public static int reduce(Map<Integer, List<Integer>> g) {
        int matched = 0;
        int checks = g.get(Collections.max(g.entrySet(), Comparator.comparingInt(entry -> entry.getValue().size())).getKey()).size();

        while (g.size() > 1 && checks >= 1) {
            int initMwNode = Collections.min(g.entrySet(), Comparator.comparingInt(entry -> entry.getValue().size())).getKey();
            if (g.get(initMwNode).size() < 1) {
                g.remove(initMwNode);
            } else {
                int[] tempSecMin = {g.get(g.get(initMwNode).get(0)).size() + 1, 1};
                for (int node_i : g.get(initMwNode)) {
                    if (g.get(node_i).size() < tempSecMin[0]) {
                        tempSecMin[0] = g.get(node_i).size();
                        tempSecMin[1] = node_i;
                    }
                    for (int check_i = 0; check_i < g.get(node_i).size(); check_i++) {
                        if (g.get(node_i).get(check_i) == initMwNode) {
                            g.get(node_i).remove(check_i);
                            break;
                        }
                    }
                }
                for (int node_i : g.get(tempSecMin[1])) {
                    for (int check_i = 0; check_i < g.get(node_i).size(); check_i++) {
                        if (g.get(node_i).get(check_i) == tempSecMin[1]) {
                            g.get(node_i).remove(check_i);
                            break;
                        }
                    }
                }
                g.remove(initMwNode);
                g.remove(tempSecMin[1]);
                matched += 2;
            }

            if (g.size() > 1) {
                checks = g.get(Collections.max(g.entrySet(), Comparator.comparingInt(entry -> entry.getValue().size())).getKey()).size();
            }
        }

        return matched;
    }
    
    public static int solution(int[] banana_list) {
        Map<Integer, List<Integer>> g =  generateGraph(banana_list);
        int matches = reduce(g);
        return banana_list.length - matches;
    }
   
    public static void main(String[] args) {
        
        int[] in1 = {1,1};
        System.out.println(solution(in1));
        int[] in2 = {1, 7, 3, 21, 13, 19};
        System.out.println(solution(in2));
        
    }
}

