public class Main {
    public static void main(String[] args) {
        Solutions sol = new Solutions();

        // problem 1
        int numCourses = 2;
        int [][] prereq = {{1,0}};
        sol.findOrder(numCourses, prereq);

        //problem 2
        int dividend = 10;
        int divisor = 3;
        sol.divide(dividend, divisor);

        //problem 3
        int[] coins = {1,2,5};
        int amount = 11;
        sol.coinChange(coins, amount);
    }
}