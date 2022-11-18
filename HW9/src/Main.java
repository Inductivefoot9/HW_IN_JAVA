public class Main {
    public static void main(String[] args) {
        Solutions sol = new Solutions();

        //problem 1
        String s = "(()";
        sol.longestValidParentheses(s);


        //problem 2
        int[] nums = {-2, 1,-3,4,-1,2,1,-5,4};
        sol.maxSubArray(nums);

        //problem 3
        int m = 3;
        int n = 2;
        sol.uniquePaths(m , n);

        //problem 4
        int [][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        sol.uniquePathsWithObstacles(obstacleGrid);
    }
}