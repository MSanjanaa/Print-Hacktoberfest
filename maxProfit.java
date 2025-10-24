class Solution {
    public long maxProfit(int[] prices, int[] strategy, int r) {
        int n = prices.length;
        long base = 0;
        long[] loss = new long[n], gain = new long[n];
        
        for (int i = 0; i < n; i++) {
            base += (long) strategy[i] * prices[i];
            loss[i] = -(long) strategy[i] * prices[i];
            gain[i] = (1L - strategy[i]) * prices[i];
        }
        
        long[] prefLoss = new long[n + 1], prefGain = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefLoss[i + 1] = prefLoss[i] + loss[i];
            prefGain[i + 1] = prefGain[i] + gain[i];
        }
        
        long best = 0;
        int half = r / 2;
        for (int i = 0; i + r <= n; i++) {
            long left = prefLoss[i + half] - prefLoss[i];
            long right = prefGain[i + r] - prefGain[i + half];
            best = Math.max(best, left + right);
        }
        
        return base + best;
        
    }
}
