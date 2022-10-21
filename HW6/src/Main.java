public class Main {
    public static void main(String[] args) {
        Solutions sol = new Solutions();
        //problem 1
        int n = 3;
        int sor = 0;
        int dest = 0;
        int[][] edges ={ {0,1},{0,2},{3,5},{5,4},{4,3}};

        sol.validPath(n, edges, sor,dest);

        //problem 2
        int[]edges2 = new int[]{3,3,4,2,3};

        sol.longestCycle(edges2);

        //problem 3
         n = 3;
        int[][] edges3 = {{1,2,5} , {1,3,6} , {2,3,1}};
        sol.minimumCost(n, edges3);
    }
}