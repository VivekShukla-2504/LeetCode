class Solution {
    public int countIntersectingIntervals(int[][] intervals) {
        int count = 0 ;
        int n = intervals.length;
        for(int i = 0 ; i< n ;i++)
        {
            for(int j = i+1 ; j< n ; j++ )
            {
                int start = Math.max(intervals[i][0],intervals[j][0]);
                int end = Math.min(intervals[i][1],intervals[j][1]);
                if(start <= end )
                {
                    count++;
                }
            }
        }
        return count;
    }
}