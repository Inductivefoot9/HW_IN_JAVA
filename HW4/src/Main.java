public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        //Problem 1
        int[] nums = {1, 1, 1, 2, 2,3 }; // test case
        int  k = 2;
        System.out.println(sol.topKFrequent(nums , k));

        //Problem 2
        int [] nums2 = {1,2,3,4,5}; // test case
        System.out.println(sol.findClosestElements(nums2 , 4 , 3));

        //problem 3
        int[] nums3 = {15, 13, 12, 10, 8, 9};
        System.out.println(sol.peekTopK(nums3, 5));

        //problem 4
         int[] nums4 = {2,-1,2};
        System.out.println(sol.shortestSubarray(nums4 , 3));

        //Problem 5
        int[] nums5 = {1,2,3,5} ;

        System.out.println(sol.kthSmallestPrimeFraction(nums5, 3));
    }
}