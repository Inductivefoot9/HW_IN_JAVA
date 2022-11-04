import java.util.ArrayList;
import java.util.List;

public class Solutions {

    //problem 1
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDeg = new int[numCourses];
        List<Integer>[] chl = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            chl[i] = new ArrayList<Integer>();
        }

        int pre;
        int cour;

        for (int[] pair : prerequisites) {
            pre = pair[1];
            cour = pair[0];

            chl[pre].add(cour);
            inDeg[cour]++;
        }

        int[] res = new int[numCourses];
        int k = 0;

        for (int i = 0; i < numCourses; i++) {
            if (inDeg[i] == 0) {
                res[k++] = i;
            }
        }

        if (k == 0) {
            return new int[0];
        }

        int j = 0;
        List<Integer> tmp;

        while (k < numCourses) {
            tmp = chl[res[j++]];
            for (int id : tmp) {
                if (--inDeg[id] == 0) {
                    res[k++] = id;
                }
            }
            if (j == k) {
                return new int[0];
            }
        }
        return res;
    }

    //problem 2

    public int divide(int dividend, int divisor) {

        boolean minValFlag = false;
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) return Integer.MAX_VALUE;
            else if (divisor == 1) return Integer.MIN_VALUE;

            else minValFlag = true;
        }
        if (divisor == Integer.MIN_VALUE) return minValFlag ? 1 : 0;

        boolean isNegative = dividend < 0 ^ divisor < 0;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int n = 1, dvdn = 1;
        while (dvdn < dividend && dvdn != Integer.MIN_VALUE) {
            dvdn <<= 1;
            n++;
        }
        int quotient = 0, remainder = 0;

        if (minValFlag) {
            n = 31;
            remainder = 1;
        }

        for (n--; n >= 0; n--) {
            remainder <<= 1;
            remainder += (dividend >> n) & 1;
            if (remainder >= divisor || remainder == Integer.MIN_VALUE) {
                remainder -= divisor;
                quotient += 1 << n;
            }

        }

        return isNegative ? 0 - quotient : quotient;
    }

    //Problem 3
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0)
            return -1;

        if (amount <= 0)
            return 0;

        int dp[] = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int am = 1; am < dp.length; am++) {
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= am) {
                    int sub = dp[am - coins[i]];
                    if (sub != Integer.MAX_VALUE)
                        dp[am] = Math.min(sub + 1, dp[am]);
                }
            }
        }
        return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }
}
