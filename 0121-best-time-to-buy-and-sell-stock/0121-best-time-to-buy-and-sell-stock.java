class Solution {
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = Integer.MIN_VALUE;
        for(int price : prices)
        {
            minprice = Math.min(minprice,price);
            maxprofit = Math.max(maxprofit, price - minprice);
        }
        return maxprofit;
    }
}