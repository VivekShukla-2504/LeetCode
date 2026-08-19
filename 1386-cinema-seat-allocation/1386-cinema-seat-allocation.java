class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        // Start with max possible allocation assuming all rows are completely empty
        int maxGroups = (n - rowMasks.size()) * 2;
        
        // Bitmask representations (0-indexed offset by 2):
        // Seats 2,3,4,5 -> bits 0,1,2,3 -> 0b00001111 (15)
        // Seats 6,7,8,9 -> bits 4,5,6,7 -> 0b11110000 (240)
        // Seats 4,5,6,7 -> bits 2,3,4,5 -> 0b00111100 (60)
        
        for (int mask : rowMasks.values()) {
            boolean leftAvailable = (mask & 15) == 0;
            boolean rightAvailable = (mask & 240) == 0;
            boolean middleAvailable = (mask & 60) == 0;
            
            if (leftAvailable && rightAvailable) {
                maxGroups += 2;
            } else if (leftAvailable || rightAvailable || middleAvailable) {
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}