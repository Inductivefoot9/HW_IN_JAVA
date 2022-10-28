import java.util.*;

public class Solutions {
    // problem 1
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] pre : prerequisites) {
            adj[pre[0]].add(pre[1]);
        }

        int[] visited = new int[numCourses];

        for(int i=0; i<numCourses; i++) {
            if( !dfs(i, visited, adj))
                return false;
        }

        return true;
    }

    public boolean dfs(int node, int[] visited, ArrayList<Integer>[] adj) {
        if(visited[node] == 1) {
            return false;
        }

        if(visited[node] == 2) {
            return true;
        }

        visited[node] = 1;

        for(int n : adj[node]) {
            if(!dfs(n, visited, adj))
                return false;
        }


        visited[node] = 2;

        return true;
    }

    //problem 2
    class Node{
        int vertex, cost;
        public Node(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
    }

        public void printGraph(Map<Integer, List<int[]>> graph){
            for(int u:graph.keySet()){
                List<int[]> neighbors = graph.get(u);
                for(int[] neighbor: neighbors){
                    int v = neighbor[0];
                    int w = neighbor[1];
                    System.out.println(u+"->"+v+" "+w);
                }
            }
        }
        public int networkDelayTime(int[][] times, int n, int k) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for(int[] edge: times){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if(!graph.containsKey(u)){
                    graph.put(u, new ArrayList<>());
                }
                graph.get(u).add(new int[]{v, w});
            }
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n1->n1.cost));

            boolean[] visited = new boolean[n+1];
            int[] distance = new int[n+1];

            Arrays.fill(distance, Integer.MAX_VALUE);

            distance[k] = 0;
            pq.add(new Node(k, 0));

            while(!pq.isEmpty()){
                Node node = pq.poll();
                int u = node.vertex;
                visited[u] = true;
                List<int[]> neighbors = graph.get(u);
                if(neighbors==null)
                    continue;
                for(int[] neighbor: neighbors){
                    int v = neighbor[0];
                    int w = neighbor[1];
                    if( !visited[v] && ( (distance[u] + w) < distance[v] ) ){
                        distance[v] = distance[u] + w;
                        pq.add(new Node(v, distance[v]));
                    }
                }
            }
            int max = Integer.MIN_VALUE;
            for(int i=1;i<=n;i++){
                if(distance[i]==Integer.MAX_VALUE)
                    return -1;
                max = Math.max(max, distance[i]);
            }
            return max;
        }

        // problem 3


        /// List of { [node_id_1, node_id_2, distance] }
        List<int[]> graphEdges;
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        graphEdges = new ArrayList<>();
        addEdges(n, wells, pipes);
        return minCostKruskals(n);
    }

    /// select edges based on Kruskals - with least edge node to be added first
    private int minCostKruskals(int n) {
        /// Sort edges in increasing order of edge value
        Collections.sort(graphEdges, (a, b) -> a[2] - b[2]);
        int minCost = 0, processedEdges = 0;

        DSU dsu = new DSU(n + 1);
        for(int[] edge: graphEdges) {
            /// check if this can be merged (in this case:`union`) to the existing graph component
            if(dsu.union(edge[0], edge[1])) {
                processedEdges++;
                minCost += edge[2];
                /// Break processing once all edges are processed
                if(processedEdges == n) break;
            }
        }
        return minCost;
    }

    private void addEdges(int n, int[] wells, int[][] pipes) {
        for(int i=0;i<n;i++) {
            graphEdges.add(new int[] {0, i+1, wells[i]});
        }

        for(int[] pipe: pipes) {
            graphEdges.add(pipe);
        }
    }
}

/// Disjoint Set Union Implementation
class DSU {
    int[] root;
    int[] size;

    DSU(int n) {
        root = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++) root[i] = i;
    }

    public int find(int x) {
        if(x != root[x]) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        /// same root - no need to merge (since both are part of same tree)
        if(rootX == rootY) return false;

        /// merge if they have different root
        if(size[rootX] <= size[rootY]) {
            root[rootX] = rootY;
            size[rootY]++;
        } else {
            root[rootY] = rootX;
            size[rootX]++;
        }
        return true;
    }
}

