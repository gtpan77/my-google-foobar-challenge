public class Main {

    public static String[] solution(String[] l) {
        Arrays.sort(l, new Comparator<String>() {
            public int compare(String v1, String v2) {
                int[] listOfVersion1 = getVersions(v1);
                int[] listOfVersion2 = getVersions(v2);
                // System.out.println(v1 + " " + Arrays.toString(listOfVersion1));
                // System.out.println(v2 + " " + Arrays.toString(listOfVersion2));
                if(listOfVersion1[0] < listOfVersion2[0]) {
                    return -1;
                } else if(listOfVersion1[0] == listOfVersion2[0] && listOfVersion1[1] < listOfVersion2[1]) {
                    return -1;
                } else if (listOfVersion1[0] == listOfVersion2[0] && listOfVersion1[1] == listOfVersion2[1] && listOfVersion1[2] < listOfVersion2[2]) {
                    return -1;
                } else if (listOfVersion1[0] == listOfVersion2[0] && listOfVersion1[1] == listOfVersion2[1] && listOfVersion1[2] == listOfVersion2[2] && v1.length() < v2.length()) {
                    return -1;
                }
                return 1;
            }

            public int[] getVersions(String version) {
                String[] listOfVersion = version.split("\\.");
                // System.out.println(version + " " + listOfVersion.length);
                int[] versionNumbers = {0,0,0};
                for(int i=0; i<listOfVersion.length;i++) {
                    // System.out.println(i + " " + listOfVersion[i]);
                    versionNumbers[i] = Integer.parseInt(listOfVersion[i]);
                }
                return versionNumbers;
            }
        });
        return l;
    }
   
    public static void main(String[] args) {
        String test = "2,0.2";
        System.out.println(Arrays.toString(test.split(",")));
        String[] in1 = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
        String[] out1 = solution2(in1);
        for(String s: out1) {
            System.out.println(s);
        }
        String[] in2 = {"1","1.0","1.0.0"};
        String[] out2 = solution2(in2);
        for(String s: out2) {
            System.out.println(s);
        }
        String[] in3 = {"1.1.2","1.0","1.3.3","1.0.12","1.0.2"};
        String[] out3 = solution2(in3);
        for(String s: out3) {
            System.out.println(s);
        }
    }
}

