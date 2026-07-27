class Solution {
    public int largestInteger(int n, int s) {
       if(s==0) return 0;
        if(s>9*n) return -1;

        int[] digits = new int[n];
        int remaining = s;
        for(int i = 0;i<n&&remaining>0;i++)
            {
                int d = Math.min(9, remaining);
                digits[i] = d;
                remaining -= d;
            }
        StringBuilder sb = new StringBuilder();
        boolean started = false ;
        for(int i = 0 ;i<n; i++)
            {
                if(digits[i] != 0) started = true;
                if(started) sb.append(digits[i]);
            }
        return Integer.parseInt(sb.toString());
    }
}