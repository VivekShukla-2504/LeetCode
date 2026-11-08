class Solution {
    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        List<List<String>> result = new ArrayList<>();
        int n = s1.length();
        int i = 0; 
        while(i<n)
        {
            if(s1.charAt(i)==s2.charAt(i))
            {
                i++;
                continue;
            }
            int start = i;
            while(i<n && s1.charAt(i)!= s2.charAt(i))
            {
                i++;
            }
            result.add(Arrays.asList(s1.substring(start,i), s2.substring(start,i)));
        }
        return result;
    }
}