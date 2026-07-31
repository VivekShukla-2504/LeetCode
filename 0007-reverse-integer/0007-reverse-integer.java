class Solution {
    public int reverse(int x) {
       long result = 0;//45
       while(x!=0)
       {
        int rem = x%10;
        x = x/10;
        result = result*10+rem;
        if(result>Integer.MAX_VALUE|| result<Integer.MIN_VALUE)
        {
            return 0;
        }
       }
       return (int) result;
    }
}