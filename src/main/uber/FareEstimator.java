package uber;

// https://app.codesignal.com/company-challenges/uber/HNQwGHfKAoYsz9KX6
public class FareEstimator {
    double[] fareEstimator(int ride_time, int ride_distance, double[] cost_per_minute, double[] cost_per_mile) {
        int n = cost_per_mile.length;
        double[] ans = new double[n];
        for (int i = 0; i < n; i++) {
            ans[i] = ride_time * cost_per_minute[i] + ride_distance * cost_per_mile[i];
        }
        return ans;
    }

}
