public class Main {
    public static String solution(String x) {
        char[] msg = x.toCharArray();
        for(int i=0;i<msg.length;i++) {
            if(Character.isLowerCase(msg[i])) {
                msg[i] = (char)('a' + 'z' - msg[i]);
            }
        }
        String decrypted = String.valueOf(msg);
        return decrypted;
    }
   
    public static void main(String[] args) {
        System.out.println(solution("Yvzs! "));
        System.out.println(solution("wrw blf "));
    }
}

