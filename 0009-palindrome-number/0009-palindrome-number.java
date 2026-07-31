class Solution {
    public boolean isPalindrome(int x) {
       if(x<0)
       {
        return false;
       }
       long result = 0;
       int res = x;
       while(x!=0)
       {
        int rem = x%10;
        x = x/10;
        result = result*10+rem;
       }
       return res ==result ;
    }

}