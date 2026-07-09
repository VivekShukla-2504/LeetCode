class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int [] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int j = 2*n-1;j>=0;j--) 
        { int i = j%n;
            while(!stack.isEmpty() && stack.peek()<= nums[i])
            {
                stack.pop();
            }
            if(stack.isEmpty())
            {
                ans[i] = -1;
            }
            else
            {
                ans[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return ans;
    }
}