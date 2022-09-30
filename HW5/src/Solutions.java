import java.util.*;

public class Solutions {
   //problem 1
   public List<String> removeInvalidParentheses(String s) {
       List<String> ans = new ArrayList<>();
       remove(s, ans, 0, 0, new char[]{'(', ')'});
       return ans;
   }
    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    //Problem 2

    public int getMinimumDifference(TreeNode root) {
        int[] arr = {-1, Integer.MAX_VALUE};
        inorder(root, arr);
        return arr[1];
    }

    public void inorder(TreeNode root, int[] arr) {
        if(root == null) return;
        inorder(root.left,arr);
        if(arr[0] != -1) {
            arr[1] = Math.min(arr[1], Math.abs(arr[0] - root.val));
        }
        arr[0] = root.val;
        inorder(root.right,arr);
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Problem 3
    public class pair{
        int node;
        int mask;
        pair(int node , int mask){
            this.node = node;
            this.mask = mask;
        }
    }

    public int shortestPathLength(int[][] graph) {
        int size =  graph.length;
        int target = (1<<size)-1;

        boolean[][] visited = new boolean[size][target];
        Queue<pair> q = new ArrayDeque<>();

        for(int i = 0; i < size; i++){
            q.add(new pair(i , (1<<i)));
        }

        int steps = 0;
        while(!q.isEmpty()){
            int cuurentInQueue = q.size();

            while(cuurentInQueue-- > 0){
                pair remove = q.poll();
                if(remove.mask == target){
                    return steps;
                }
                int node = remove.node;
                int mask = remove.mask;
                if(visited[node][mask] == true){
                    continue;
                }

                visited[node][mask] = true;

                for(int nbr : graph[node]){
                    q.add(new pair(nbr , mask | (1<<nbr)));
                }

            }
            steps++;
        }
        return -1;
    }

    //problem 4

    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        oneSideMaxSum(root);
        return maxSum;
    }

    private int oneSideMaxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = oneSideMaxSum(root.left);
        int r = oneSideMaxSum(root.right);
        maxSum = Math.max(maxSum, root.val + Math.max(l, 0) + Math.max(r, 0));
        return root.val + Math.max(0, Math.max(l, r));
    }

    //problem 5
    public List<Integer> lexicalOrder(int n) {
        List<Integer> lst = new ArrayList<>();
        dfs(lst, n, 0);
        return lst;
    }

    private void dfs(List<Integer> lst, int n, int num) {

        for(int i = 0; i <=9; i++) {
            int cur = 10*num + i;
            //get rid of 0
            if(cur == 0) continue;
            // when larger than n, return to the previous level
            if (cur > n) return;
            lst.add(cur);
            dfs(lst, n, cur);
        }
    }
}
