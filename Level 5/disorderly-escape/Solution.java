public class Main {

    static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    static BigInteger lcm(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO) || b.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        return a.multiply(b).divide(gcd(a, b));
    }

    static List<List<List<Integer>>> parti(int n) {
        List<List<List<Integer>>> p = new ArrayList<>();
        List<List<Integer>> p0 = new ArrayList<>();
        List<Integer> p00 = new ArrayList<>();
        p0.add(p00);
        p.add(p0);

        List<List<Integer>> p1 = new ArrayList<>();
        List<Integer> p10 = new ArrayList<>();
        p10.add(1);
        p1.add(p10);
        p.add(p1);

        while (p.size() < n + 1) {
            List<List<Integer>> newE = new ArrayList<>();
            for (int i = 1; i <= p.size() / 2; i++) {
                for (List<Integer> j : p.get(i)) {
                    for (List<Integer> k : p.get(p.size() - i)) {
                        List<Integer> b = new ArrayList<>(j);
                        b.addAll(k);
                        b.sort(null);
                        if (!newE.contains(b)) {
                            newE.add(b);
                        }
                    }
                }
            }
            List<Integer> newElast = new ArrayList<>();
            newElast.add(p.size());
            newE.add(newElast);
            p.add(newE);
        }
        return p;
    }

    static BigInteger newFact(int n) {
        if (n < 30) {
            return BigInteger.valueOf(new long[]{
                    1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L,
                    87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L,
                    2432902008176640000L, 51090942171709440000L, 1124000727777607680000L, 25852016738884976640000L,
                    620448401733239439360000L, 15511210043330985984000000L, 403291461126605635584000000L,
                    10888869450418352160768000000L, 304888344611713860501504000000L, 8841761993739701954543616000000L
            }[n]);
        }

        BigInteger result = BigInteger.valueOf(n);
        for (int i = 1; i < n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    static BigInteger numCycles(int n, int k) {
        return newFact(n).divide(BigInteger.valueOf(k).multiply(newFact(n - k)));
    }

    static BigInteger binom(int n, int k) {
        return newFact(n).divide(newFact(k).multiply(newFact(n - k)));
    }

    static List<List<Integer>> toTerm(List<Integer> lis) {
        int size = lis.stream().mapToInt(Integer::intValue).sum();
        int[] powers = new int[size];
        for (int i : lis) {
            powers[i - 1]++;
        }
        BigInteger coefficient = BigInteger.ONE;
        int num = size;
        for (int i : lis) {
            coefficient = coefficient.multiply(numCycles(num, i));
            num -= i;
        }
        for (int c : powers) {
            coefficient = coefficient.divide(newFact(c));
        }
        List<Integer> term = new ArrayList<>();
        term.add(coefficient.intValue());
        for (int power : powers) {
            term.add(power);
        }
        return List.of(term);
    }

    static List<List<List<Integer>>> getCycleIndex(int n) {
        List<List<List<Integer>>> k = parti(n);
        List<List<List<Integer>>> poly = new ArrayList<>();
        for (List<List<Integer>> i : k) {
            poly.add(toTerm(i.get(0)));
        }
        return poly;
    }

    static List<Integer> multiply(List<Integer> term1, List<Integer> term2) {
        int t = term1.size() - 1;
        int l = term2.size() - 1;
        List<Integer> mult = new ArrayList<>();
        mult.add(term1.get(0) * term2.get(0));
        for (int i = 0; i < t; i++) {
            mult.add(0);
        }
        for (int i = 0; i < l; i++) {
            for (int k = 0; k < t; k++) {
                mult.set(lcm(i + 1, k + 1) - 1,
                        mult.get(lcm(i + 1, k + 1) - 1) + term1.get(i + 1) * term2.get(k + 1) * gcd(i + 1, k + 1));
            }
        }
        return mult;
    }

    static List<Integer> cycleProduct(int n, int m) {
        List<List<List<Integer>>> a = getCycleIndex(n);
        List<List<List<Integer>>> b = getCycleIndex(m);
        List<Integer> prod = new ArrayList<>();
        for (List<List<Integer>> i : a) {
            for (List<List<Integer>> k : b) {
                prod.addAll(multiply(i.get(0), k.get(0)));
            }
        }
        return prod;
    }

    static String solution(int n, int m, int c) {
        List<Integer> product = cycleProduct(n, m);
        BigInteger s = BigInteger.ZERO;
        for (int k : product) {
            s = s.add(BigInteger.valueOf(k).multiply(BigInteger.valueOf(c).pow(k)));
        }
        return s.divide(newFact(m).multiply(newFact(n))).toString();
    }
   
    public static void main(String[] args) {
        System.out.println(solution(2,2,2));
        System.out.println(solution(2,3,4));
    }
}

