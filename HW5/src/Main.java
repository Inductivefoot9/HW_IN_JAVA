public class Main {

    public static void main(String[] args) {
        Solutions sol = new Solutions();

        //problem 1
        String test = "()())()";
        System.out.println(sol.removeInvalidParentheses(test));

        //Problem 2
        Solutions.TreeNode root = new Solutions.TreeNode();
        System.out.println(sol.getMinimumDifference(root));

        //Problem 3
        int[][] graph = {{1,2,3},{0},{0},{0}};
        System.out.println(sol.shortestPathLength(graph));

        //problem 4
        System.out.println(sol.maxPathSum(root));

        //Problem 5
        int n = 13;
        System.out.println(sol.lexicalOrder( n));

    }

}