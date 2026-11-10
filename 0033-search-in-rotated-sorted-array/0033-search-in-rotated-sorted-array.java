class Solution {
    public int search(int[] a, int t) {
    int lo = 0, hi = a.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == t) return mid;
        if (a[lo] <= a[mid]) {                       // left half sorted
            if (a[lo] <= t && t < a[mid]) hi = mid - 1;
            else lo = mid + 1;
        } else {                                     // right half sorted
            if (a[mid] < t && t <= a[hi]) lo = mid + 1;
            else hi = mid - 1;
        }
    }
    return -1;
    }
}