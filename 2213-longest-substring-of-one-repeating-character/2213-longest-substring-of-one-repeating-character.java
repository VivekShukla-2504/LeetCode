class Solution {
    class SegmentTree {
        int n;
        char[] s;
        int[] maxLen;
        int[] prefixLen;
        int[] suffixLen;

        public SegmentTree(String str) {
            this.s = str.toCharArray();
            this.n = s.length;
            int treeSize = 4 * n;
            maxLen = new int[treeSize];
            prefixLen = new int[treeSize];
            suffixLen = new int[treeSize];
            build(1, 0, n - 1);
        }

        private void merge(int node, int l, int r, int mid) {
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;
            int leftLen = mid - l + 1;
            int rightLen = r - mid;

            maxLen[node] = Math.max(maxLen[leftChild], maxLen[rightChild]);
            prefixLen[node] = prefixLen[leftChild];
            suffixLen[node] = suffixLen[rightChild];

            if (s[mid] == s[mid + 1]) {
                maxLen[node] = Math.max(maxLen[node], suffixLen[leftChild] + prefixLen[rightChild]);

                if (prefixLen[leftChild] == leftLen) {
                    prefixLen[node] = leftLen + prefixLen[rightChild];
                }
                if (suffixLen[rightChild] == rightLen) {
                    suffixLen[node] = rightLen + suffixLen[leftChild];
                }
            }
        }

        private void build(int node, int l, int r) {
            if (l == r) {
                maxLen[node] = 1;
                prefixLen[node] = 1;
                suffixLen[node] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node, l, mid);
            build(2 * node + 1, mid + 1, r);
            merge(node, l, r, mid);
        }

        public void update(int node, int l, int r, int idx, char ch) {
            if (l == r) {
                s[idx] = ch;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node, l, mid, idx, ch);
            } else {
                update(2 * node + 1, mid + 1, r, idx, ch);
            }
            merge(node, l, r, mid);
        }

        public int getMax() {
            return maxLen[1];
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        SegmentTree tree = new SegmentTree(s);
        int k = queryIndices.length;
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            tree.update(1, 0, s.length() - 1, idx, ch);
            result[i] = tree.getMax();
        }

        return result;
    }
}