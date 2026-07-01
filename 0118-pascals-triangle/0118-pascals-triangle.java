class Solution {

    static List<Integer> generateRow(int row) {

        List<Integer> ans = new ArrayList<>();

        long res = 1;

        ans.add(1);

        for (int col = 1; col <= row; col++) {

            res = res * (row - col + 1);

            res = res / col;

            ans.add((int) res);
        }

        return ans;
    }

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {

            ans.add(generateRow(i));
        }

        return ans;
    }
}