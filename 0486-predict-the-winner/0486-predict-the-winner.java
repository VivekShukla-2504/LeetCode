class Solution {

    public boolean predictTheWinner(int[] nums) {
        return solve(nums, 0, nums.length - 1, 0, 0, true);
    }

    private boolean solve(int[] nums, int left, int right,
            int p1Score, int p2Score,
            boolean p1Turn) {

        if (left > right) {
            return p1Score >= p2Score;
        }

        if (p1Turn) {

            boolean takeLeft = solve(nums,
                    left + 1,
                    right,
                    p1Score + nums[left],
                    p2Score,
                    false);

            boolean takeRight = solve(nums,
                    left,
                    right - 1,
                    p1Score + nums[right],
                    p2Score,
                    false);

            return takeLeft || takeRight;
        }

        // Player 2's Turn
        else {

            boolean takeLeft = solve(nums,
                    left + 1,
                    right,
                    p1Score,
                    p2Score + nums[left],
                    true);

            boolean takeRight = solve(nums,
                    left,
                    right - 1,
                    p1Score,
                    p2Score + nums[right],
                    true);

            return takeLeft && takeRight;
        }
    }
}