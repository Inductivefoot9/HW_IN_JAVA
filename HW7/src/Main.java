public class Main {
    public static void main(String[] args) {
        Solutions s = new Solutions();

        //Problem 1
        int num = 2;
        int[][] preReq = { {0,1}};
        s.canFinish(num, preReq);

        //Problem 2
        int n =2;
        int k = 1;
        int[][] times = {{1,2,1}};
        s.networkDelayTime(times, n ,k);

        //Problem 3

        n = 3;
        int[] wells = {1,2,2};
        int[][] pipes = {{1,2,1},{2,3,1}};
        s.minCostToSupplyWater(n, wells, pipes);

    }
}