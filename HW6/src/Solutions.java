//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
import java.util.*;

public class Solutions {
    //Problem 1
    public boolean validPath(int n, int[][] edges, int start, int end) {
        return validPath(getAdjList(edges), new HashSet<>(), start, end);
    }

    private Map<Integer, Set<Integer>> getAdjList(int[][] edges) {
        var adjList = new HashMap<Integer, Set<Integer>>();

        for (var edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }
        return adjList;
    }

    private boolean validPath(Map<Integer, Set<Integer>> adjList, Set<Integer> visited, int current, int end) {
        if (current == end)
            return true;

        if (!visited.add(current))
            return false;

        for (var neighbor : adjList.getOrDefault(current, Set.of()))
            if (validPath(adjList, visited, neighbor, end))
                return true;

        return false;
    }
    //problem 2
    public int longestCycle(int[] edges) {
        int res = -1;
        boolean[] vis = new boolean[edges.length]; // global visisted

        for(int i=0; i<edges.length; i++){
            if(vis[i]) continue;
            HashMap<Integer, Integer> x = new HashMap<>();  // local visited
            for (int idx=i, dist=0; idx!=-1; idx=edges[idx]){
                if(x.containsKey(idx)){
                    res = Math.max(res, dist-x.get(idx));
                    break;
                }
                if(vis[idx]) break;
                vis[idx] = true;
                x.put(idx, dist++);
            }
        }
        return res;
    }

    //problem 3
    public int minimumCost(int n, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] connection : connections) {
            int from = connection[0], to = connection[1], cost = connection[2];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(from).add(new int[]{to, cost});
            graph.get(to).add(new int[]{from, cost});
        }

        int sum = 0;
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{connections[0][0], 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.remove();
            if (visited.contains(curr[0]))
                continue;
            visited.add(curr[0]);
            sum += curr[1];
            for (int[] next : graph.get(curr[0])) {
                int to = next[0], cost = next[1];
                if (!visited.contains(to)) {
                    pq.add(new int[]{to, cost});
                }
            }
        }

        return visited.size() == n ? sum : -1;
    }
}
