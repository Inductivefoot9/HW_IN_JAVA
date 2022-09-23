import java.util.*;

public class Solution {
    // problem 1
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){ map.put(num, map.getOrDefault(num, 0) + 1); }

        Queue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for(int key : map.keySet()){ heap.add(key); }

        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++){
            ans.add(heap.poll());
        }

        return ans;
    }

    // problem 2
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        Queue<Integer> pq = new PriorityQueue<>((a,b)->(Math.abs(a-x)<Math.abs(b-x))?1:-1);
        List <Integer> output = new ArrayList<>();

        for(int i: arr){
            pq.add(i);
            if(pq.size()>k){
                pq.remove();
            }
        }

        while(!pq.isEmpty()){
            output.add(pq.remove());
        }
        Collections.sort(output);
        return output;
    }


     // problem 3
     List<Integer> peekTopK(int[] arr, int k) {
         List<Integer> res = new ArrayList<>();
         PriorityQueue<Integer> nums = new PriorityQueue<>((a, b) -> (b-a));

         int i = 0;
         int left = 0;
         int right = 0;

         res.add(arr[0]);
         int temp = k - 1;
         while (temp-- > 0) {
             left = 2 * i + 1;
             right = 2 * i + 2;
             if (left < arr.length) {
                nums.add(arr[left]);
             }
             if (right < arr.length) {
                 nums.add(arr[right]);
             }
             res.add(nums.poll());
             i++;
         }
         return res;
     }

     //Problem 4:
     public int shortestSubarray(int[] A, int K) {
         int N = A.length;
         int res = N + 1;
         long[] B = new long[N + 1];
         for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
         Deque<Integer> d = new ArrayDeque<>();
         for (int i = 0; i < N + 1; i++) {
             while (d.size() > 0 && B[i] - B[d.getFirst()] >=  K)
                 res = Math.min(res, i - d.pollFirst());
             while (d.size() > 0 && B[i] <= B[d.getLast()])
                 d.pollLast();
             d.addLast(i);
         }
         return res <= N ? res : -1;
     }
     //problem 5
     public int[] kthSmallestPrimeFraction(int[] arr, int k) {

         double hi = 1;
         double lo = 0;

         while(true){

             int numerator = 0;
             int denom= 1;

             double mid = lo + (hi - lo)/2;

             int i = 0;
             int j = arr.length-1;
             int count = 0;

             while(i < arr.length-1 && j > 0){

                 while(j > 0 && arr[i] > mid * arr[arr.length - j]){
                     j--;
                 }
                 count += j;

                 if(j > 0 && arr[i]*denom>= numerator*arr[arr.length - j]){
                     numerator = arr[i];
                     denom = arr[arr.length - j];
                 }
                 i++;
             }

             if(count == k){

                 int[] nums2 = {numerator,denom};
                 return nums2;

             }else if(count > k){
                 hi = mid;
             }else{
                 lo = mid;
             }
         }
     }
}

