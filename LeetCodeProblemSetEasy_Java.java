// ARRAYS --------------------------------------------------------------------------------------------------

// Remove Duplicates from Sorted Array
class Solution {
    public int removeDuplicates(int[] nums) {
        int curr = 0;
        int count = 0;
        if(nums.length == 0)
            return count;
        if(nums.length!=0)
            count++;
        for(int i=1; i<nums.length; i++){
            if(nums[curr] == nums[i])
                continue;
            else{
                nums[count] = nums[i];
                curr = i;
                count++;
            }
        }
        return count;
    }
}

// Best Time to Buy and Sell Stock II
class Solution {
    public int maxProfit(int[] prices) {        
        int maxProfit = 0;
        
        for(int i=1; i<prices.length; i++){
            if(prices[i] > prices[i-1])
                maxProfit += prices[i] - prices[i-1];
        }
        
        return maxProfit;
    }
}

// Rotate Array
class Solution {
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        int[] newArray = new int[size];
        System.arraycopy(nums, 0, newArray, 0, size);
        for(int i=0; i<size; i++)
            nums[(i+k)%size] = newArray[i];     
    }
}

// Contains Duplicate
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i]))
                return true;
            else
                map.put(nums[i], 1);
        }
        return false;
    }
}

// Single Number
class Solution {
    public int singleNumber(int[] nums) {
        /*
        int result = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(e.getValue() == 1)
                result = e.getKey();
        }
        return result;*/
        
        int result = 0;
        
        for(int num : nums){
            // when the number is the same ^ returns 0
            result ^= num;
        }
        
        return result;
    }
}

// Intersection of Two Arrays II
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> intList = new ArrayList<>();
        List<Integer> jIndex = new ArrayList<>();
        
        for(int i=0; i<nums1.length; i++){
            for(int j=0; j<nums2.length; j++){
                if(jIndex.contains(j))
                    continue;
                if(nums1[i] == nums2[j]){
                    intList.add(nums1[i]);
                    jIndex.add(j);
                    break;
                }
            }
        }
        
        int[] resultArr = new int[intList.size()];
        for(int i=0; i<intList.size(); i++)
            resultArr[i] = intList.get(i);
        
        return resultArr;
    }
}

// Plus One
class Solution {
    public int[] plusOne(int[] digits) {
        /*
        if(digits[0] == 0)
            return new int[]{1};
        
        String numStr = "";
        for(int i=0; i<digits.length; i++)
            numStr += String.valueOf(digits[i]);
        
        java.math.BigInteger num = new java.math.BigInteger(numStr);
        num = num.add(new java.math.BigInteger("1"));
        numStr = String.valueOf(num);
        
        int[] result = new int[numStr.length()];
        for (int i=0; i<numStr.length(); i++){
            result[i] = numStr.charAt(i) - '0';
        }
        
        return result;
        */
        
        for(int i=digits.length-1; i>=0; i--){
            if(digits[i] != 9){
                digits[i] += 1;
                return digits;
            }
            else
                digits[i] = 0;
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }
}

// Move Zeroes
class Solution {
    public void moveZeroes(int[] nums) {
        
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i] == 0)
                    nums = swap(nums, i, j);
                else
                    break;
            }
        }
    }
    
    static int[] swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }
}

// Two Sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])){
                result[0] = map.get(target-nums[i]);
                result[1] = i;
                break;
            }
            map.put(nums[i],i);
        }
        return result;
    }
}

// Valid Sudoku
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean duplicateFound = false;
        
        for(int i=0; i<9; i++){
            char[] arr = board[i];
            duplicateFound = containsDuplicate(arr);
            if(duplicateFound)
                return false;
        }
        
        char[] arr = new char[9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                arr[j] = board[j][i];
            }
            duplicateFound = containsDuplicate(arr);
            if(duplicateFound)
                return false;
        }
        
        // this part was not of my own creation, i got it online at:
        // https://www.programcreek.com/2014/05/leetcode-valid-sudoku-java/
        for (int block = 0; block < 9; block++) {
            boolean[] m = new boolean[9];
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (m[(int) (board[i][j] - '1')]) {
                            return false;
                        }
                        m[(int) (board[i][j] - '1')] = true;
                    }
                }
            }
        }
        
        return true;
    }
    
    public boolean containsDuplicate(char[] nums) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i]) && nums[i]!='.')
                return true;
            else
                map.put(nums[i], 1);
        }
        return false;
    }
}

// Rotate Image
class Solution {
    public void rotate(int[][] matrix) {
        int rowCount = matrix.length;
        int[] indexAdder = new int[rowCount];
        
        int temp = rowCount-1;
        for(int i=0;i<rowCount; i++)
            indexAdder[i] = temp--;
        
        List<int[]> listMap = new ArrayList<>();
        int indexCounter = 0;
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<rowCount; j++){
                int newIndex = (((indexCounter++)%rowCount)*rowCount)+indexAdder[i];
                listMap.add(new int[]{matrix[i][j], newIndex});
            }
        }
        
        indexCounter = 0;
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<rowCount; j++){
                matrix[i][j] = getKey(listMap, indexCounter++);
            }
        }
    }
    
    static int getKey(List<int[]> list, int value)
    {
        for(int i=0; i<list.size(); i++){
            if(list.get(i)[1] == value)
                return list.get(i)[0];
        }
        return 0;
    }
}

// STRINGS --------------------------------------------------------------------------------------------------

// Reverse String
class Solution {
    public void reverseString(char[] s) {
        for(int i=0; i<s.length/2; i++)
            s = swap(s, i, s.length-1-i);
    }
    
    static char[] swap(char[] s, int a, int b){
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
        return s;
    }
}

// Reverse Integer
class Solution {
    public int reverse(int x) {
        int sum = 0;
        int sign = 1;
        
        if(x<0)
            sign = -1;
        
        int num = Math.abs(x);
        while(num > 0){
            int rem = num % 10;
            num /= 10;
            /* If sum is 214,748,364 or Integer.MAX_VALUE/10 and we multiply by 10 we get
               2,147,483,640 - which is 7 away from Integer.MAX_VALUE-1 or 2^31-1, 
               so we check if the remainder is greater than 7, which will put sum 
               over the max value, so we return 0.
               Same deal for the minimum value except it's -2^31 or -2,147,483,648.
            */
            if (sum > Integer.MAX_VALUE/10 || (sum == Integer.MAX_VALUE / 10 && rem > 7)) return 0;
            if (sum < Integer.MIN_VALUE/10 || (sum == Integer.MIN_VALUE / 10 && rem < -8)) return 0;
            sum = sum * 10 + rem;
        }
        
        return sum*sign;
    }
}

// First Unique Character in a String
class Solution {
    public int firstUniqChar(String s) {
        boolean dupFound = false;
        
        for(int i=0; i<s.length(); i++){
            for(int j=0; j<s.length(); j++){
                if(i==j)
                    continue;
                if(s.charAt(i) == s.charAt(j)){
                    dupFound = true;
                    break;
                }
                dupFound = false;
            }
            if(!dupFound)
                return i;
        }
        
        return -1;
    }
}

// Valid Anagram
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        java.util.Arrays.sort(sChar);
        java.util.Arrays.sort(tChar);
        return java.util.Arrays.equals(sChar,tChar);
    }
}

// Valid Palindrome
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        String[] words = s.split("[^a-zA-Z0-9]+");
        s = "";
        
        for(String st : words)
            s += st;
        
        int start = 0;
        int end = s.length()-1;
        while(start<end){
            if(s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        
        return true;
    }
}

// String to Integer (atoi)
class Solution {
    public int myAtoi(String s) {
        int sign = 1;
        int numOfSigns = 0;
        int leadingZeros = 0;
        long result = 0;
        s = s.trim();
        
        for(int i=0; i<s.length(); i++){
            if(numOfSigns > 1)
                return 0;
            if(s.charAt(i) == '0')
                leadingZeros+=1;
            if(s.charAt(i) == ' '){
                if(numOfSigns > 0 && result > 0)
                    return (int) result*sign;
                if(numOfSigns > 0 || leadingZeros > 0)
                    return 0;
                if(result > 0)
                    return (int) result*sign;
                continue;
            }
            if(i!=s.length()-1 && s.charAt(i) == '-'){
                if(leadingZeros > 0)
                    return 0;
                if(result > 0)
                    return (int) result*sign;
                sign = -1;
                numOfSigns++;
                continue;
            }
            if(i!=s.length()-1 && s.charAt(i) == '+'){
                if(leadingZeros > 0)
                    return 0;
                if(result > 0)
                    return (int) result*sign;
                sign = 1;
                numOfSigns++;
                continue;
            }
            if(Character.isDigit(s.charAt(i))){
                result = (result * 10) + Character.getNumericValue(s.charAt(i));
                if(result > Integer.MAX_VALUE){
                    if(sign == -1){
                        return Integer.MIN_VALUE;
                    }
                    return Integer.MAX_VALUE;
                }
            }
            else
                break;
        }
        
        return (int) result*sign;
    }
}

// Implement strStr()
class Solution {
    public int strStr(String haystack, String needle) {
        
        if(haystack == null || needle == null || needle.length() == 0)
            return 0;
        
        if(needle.length() > haystack.length())
            return -1;
        
        for(int i = 0; i <= haystack.length() - needle.length(); i++) {
            String temp = haystack.substring(i, i + needle.length());
            if (temp.equals(needle)) {
                return i;
            }
        }
        
        return -1;
    }
}

// Longest Common Prefix
class Solution {
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        char prefix = ' ';
        
        for(int i=0; i<strs[0].length(); i++){
            prefix = strs[0].charAt(i);
            for(int j=1; j<strs.length; j++){
                if(i == strs[j].length())
                    return result;
                if(strs[j].charAt(i) != prefix){
                    if(result.length() > 0)
                        return result;
                    else
                        return "";
                }
            }
            result += prefix;
        }
        return result;
    }
}

// LINKED LIST --------------------------------------------------------------------------------------------------

// Delete Node in a Linked List
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

// Remove Nth Node From the end of a List
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = null;
        ListNode curr = head;
        int count = 1;
        int i = 1;
        
        if(head == null || head.next == null)
            return null;
        
        while(curr.next != null){
            curr = curr.next;
            count++;
        }
        
        curr = head;
        
        while(i < count-(n-1)){
            prev = curr;
            curr = curr.next;
            i++;
        }
        
        if(curr.next == null){
            prev.next = null;
            return head;
        }
        
        curr.val = curr.next.val;
        curr.next = curr.next.next;
        
        return head;
    }
}

// Reverse Linked List
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode newHead = null;
        
        if(head == null)
            return null;
        
        if(head.next == null)
            return head;
        
        Deque<ListNode> deque = new ArrayDeque<>();
        while(curr.next != null){
            prev = curr;
            deque.push(curr);
            curr = curr.next;
            prev.next = null;
        }
        deque.push(curr);
        
        newHead = deque.pop();
        curr = newHead;
        while(!deque.isEmpty()){
            curr.next = deque.pop();
            curr = curr.next;
        }
        
        return newHead;
    }
}

// Merge Two Sorted Lists
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        
        // *** NEEDS UPDATING - CHECK PHP FOR REFERENCE
        
        ListNode head = null;
        ListNode curr = null;
        ListNode nextl1 = l1;
        ListNode nextl2 = l2;
        
        if(l1 == null)
            return l2;
        if(l2 ==null)
            return l1;
        if(l1 == null && l2 == null)
            return null;
        
        if(l1.val <= l2.val){
            head = new ListNode(l1.val);
            curr = head;
            if(nextl1.next != null)
                nextl1 = nextl1.next;
            else{
                head.next = l2;
                return head;
            }
        }else{
            head = new ListNode(l2.val);
            curr = head;
            if(nextl2.next != null)
                nextl2 = nextl2.next;
            else{
                head.next = l1;
                return head;
            }
        }
        
        while(nextl1 != null && nextl2 != null){
            if(nextl1.val <= nextl2.val){
                curr.next = new ListNode(nextl1.val);
                curr = curr.next;
                if(nextl1.next != null)
                    nextl1 = nextl1.next;
                else{
                    while(nextl2 != null){
                        curr.next = new ListNode(nextl2.val);
                        curr = curr.next;
                        nextl2 = nextl2.next;
                    }
                }
            }else{
                curr.next = new ListNode(nextl2.val);
                curr = curr.next;
                if(nextl2.next != null)
                    nextl2 = nextl2.next;
                else{
                    while(nextl1 != null){
                        curr.next = new ListNode(nextl1.val);
                        curr = curr.next;
                        nextl1 = nextl1.next;
                    }
                }                 
            }
        }
        
        return head;
    }
}

// Palindrome Linked List
class Solution {
    public boolean isPalindrome(ListNode head) {
        String s = "";
        while(head!=null){
            s += String.valueOf(head.val);
            head = head.next;
        }
        return ispalindrome(s);
    }
    
    static boolean ispalindrome(String s) {
        s = s.toLowerCase();
        String[] words = s.split("[^0-9]+");
        s = "";
        
        for(String st : words)
            s += st;
        
        int start = 0;
        int end = s.length()-1;
        while(start<end){
            if(s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        
        return true;
    }
}

// Linked List Cycle
public class Solution {
    public boolean hasCycle(ListNode head) {
        Map<ListNode, ListNode> map = new HashMap<>();
        
        if(head == null)
            return false;
        if(head.next == null)
            return false;
        
        while(head!=null){
            if(head.next == null)
                return false;
            if(map.containsValue(head.next)){
                return true;
            }
            map.put(head, head.next);
            head = head.next;
        }
        return false;
    }
}

// TREES --------------------------------------------------------------------------------------------------

// Maximum Depth of Binary Tree
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        if (leftDepth > rightDepth) 
            return (leftDepth + 1); 
        else 
            return (rightDepth + 1);
    }
}

// Validate Binary Search Tree
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root != null){
            // check if the left and right sub-trees of the initial root is valid
            if(valid(root.left, root.val, true) && valid(root.right, root.val, false)){
                // then check if the left and right sub-trees of the left and right children are valid
                // because it's recursive, this will check sub-trees of sub-trees
                return isValidBST(root.left) && isValidBST(root.right);
            }else
                return false;
        }
        return true;
    }
    
    // given lessThan, this will check either the entire left or right sub-tree
    static boolean valid(TreeNode root, int nodeVal, boolean lessThan){
        if(root != null){
            if(lessThan){
                if(root.val >= nodeVal)
                    return false;
                return valid(root.left, nodeVal, lessThan) && valid(root.right, nodeVal, lessThan);
            }else{
                if(root.val <= nodeVal)
                    return false;
                return valid(root.left, nodeVal, lessThan) && valid(root.right, nodeVal, lessThan);
            }
        }
        return true;
    }
}

// Symmetric Tree
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        else
            return mirror(root.left, root.right);  
    }
    
    static boolean mirror(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        else if(left == null || right == null)
            return false;
        else{
            return left.val == right.val &&
                   mirror(left.left, right.right) &&
                   mirror(left.right, right.left);
        }
    }
}

// Binary Tree Level Order Traversal
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> nodeList = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<>();
        Deque<TreeNode> currentQ = new ArrayDeque<>();
        Deque<TreeNode> nextQ = new ArrayDeque<>();
        
        if(root == null)
            return nodeList;
        
        currentQ.push(root);
        
        while(!currentQ.isEmpty()){
            TreeNode node = currentQ.pollLast();
            
            if(node.left != null)
                nextQ.push(node.left);
            if(node.right != null)
                nextQ.push(node.right);
            
            nodeValues.add(node.val);
            
            if(currentQ.isEmpty()){
                currentQ = nextQ;
                nextQ = new ArrayDeque<TreeNode>();
                nodeList.add(nodeValues);
                nodeValues = new ArrayList<Integer>();
            }
        }
            
        return nodeList;
    }
}

// Convert Sorted Array to Binary Search Tree
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {        
        return insert(nums, 0, nums.length-1);
    }
    
    // A height balanced BST is in fact an AVL tree
    
    static TreeNode insert(int[] nums, int low, int high){
        
        if(low > high)
            return null;
        
        int mid = low + (high - low)/2;
        
        TreeNode root = new TreeNode(nums[mid]);
        
        // this builds the entire left sub-tree
        root.left = insert(nums, low, mid-1);
        
        // this builds the entire right sub-tree
        root.right = insert(nums, mid+1, high);
        
        return root;
    }
}

// SORTING AND SEARCHING --------------------------------------------------------------------------------------------------

// Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        // got this solution from: https://www.programcreek.com/2012/12/leetcode-merge-sorted-array-java/
        
        while(m>0 && n>0){
           if(nums1[m-1] > nums2[n-1]){
               nums1[m+n-1] = nums1[m-1];
               m--;
           }else{
               nums1[m+n-1] = nums2[n-1];
               n--;
           }
        }
        
        while(n > 0){
            nums1[m+n-1] = nums2[n-1];
            n--;
        }
        
    }
}

// First Bad Version
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        
        // got this solution from: https://letstalkalgorithms.com/first-bad-version-leetcode/
        
        int low = 0;
        int high = n;
        int mid = 0;
        
        while(low <= high){
            
            mid = low + (high-low)/2;
            
            if(!isBadVersion(mid)) // isBadVersion == false
                low = mid+1;
            else
                high = mid-1;
        
        }
        
        if(!isBadVersion(mid))
            return mid+1;
        
        return mid;
    }
}

// DYNAMINC PROGRAMMING --------------------------------------------------------------------------------------------------

// Climbing Stairs
class Solution {
    
    Map<Integer, Integer> map = new HashMap<>();
    
    public int climbStairs(int n) {
        
        if(n==0 || n==1)
            return 1;
        
        if(map.containsKey(n))
            return map.get(n);
        
        int result = climbStairs(n-1) + climbStairs(n-1-1);
        
        map.put(n, result);
        
        return result;
        
    }
}

// Best Time to Buy and Sell Stock
class Solution {
    public int maxProfit(int[] prices) {
        int currProfit = 0;
        int profit = 0;
        int buyPrice = 10001;
        int sellPrice = -1;
        
        for(int i=0; i<prices.length; i++){
            if(prices[i] < buyPrice)
                buyPrice = prices[i];
            else if(prices[i] - buyPrice > profit)
                profit = prices[i]-buyPrice;
        }
        
        return profit;
    }
}

// Maximum Subarray
class Solution {
    public int maxSubArray(int[] nums) {
        
        /* BRUTE FORCE SOLUTION - O(n^2)
        int maxSum = -10001;
        int currSum = 0;
        if(nums.length == 1)
            return nums[0];
        for(int i=0; i<nums.length; i++){
            currSum = nums[i];
            for(int j=i; j<nums.length; j++){
                if(i!=j)
                    currSum += nums[j];
                if(currSum > maxSum)
                    maxSum = currSum;
            }
        }
        return maxSum;*/
        
        // O(n)
        int maxSum = nums[0];
        int currSum = maxSum;
        
        if(nums.length == 1)
            return nums[0];
        
        for(int i=1; i<nums.length; i++){
            currSum = Math.max(currSum + nums[i], nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        
        return maxSum;
    }
}

// House Robber
class Solution {
    public int rob(int[] nums) {
        
        // got this solution from: https://www.youtube.com/watch?v=ZwDDLAeeBM0&ab_channel=NickWhite
        
        if(nums == null || nums.length == 0)
            return 0;
        
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        
        for(int i=1; i<nums.length; i++){
            dp[i+1] = Math.max(dp[i], dp[i-1]+nums[i]);
        }
        
        return dp[nums.length];
    }
}

// DESIGN --------------------------------------------------------------------------------------------------

// Shuffle an Array
class Solution {

    int[] nums = null;
    
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    public int[] reset() {
        return nums;
    }
    
    // using Fisher-Yates shuffle
    // https://bost.ocks.org/mike/shuffle/
    public int[] shuffle() {
        int[] copyNums = this.nums.clone();
        int m = copyNums.length;
        int temp = 0;
        int i = 0;
        while(m>0){
            i = (int) Math.floor(Math.random() * m--);
            temp = copyNums[m];
            copyNums[m] = copyNums[i];
            copyNums[i] = temp;
        }
        return copyNums;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

 // Min Stack
 class MinStack {

    List<Integer> list;
    int size;
    
    public MinStack() {
        list = new ArrayList<>();
        size = 0;
    }
    
    public void push(int val) {
        list.add(val);
        size++;
    }
    
    public void pop() {
        list.remove(size-1);
        size--;
    }
    
    public int top() {
        return list.get(size-1);
    }
    
    public int getMin() {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<list.size(); i++){
            if(list.get(i) < min)
                min = list.get(i);
        }
        return min;
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

 // MATH --------------------------------------------------------------------------------------------------

 // Fizz Buzz
 class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(i%3 == 0 && i%5 == 0)
                list.add("FizzBuzz");
            else if(i%3 == 0)
                list.add("Fizz");
            else if(i%5 == 0)
                list.add("Buzz");
            else
                list.add(String.valueOf(i));
        }
        return list;
    }
}

// Count Primes
class Solution {
    public int countPrimes(int n) {

        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++)
            isPrime[i] = true;

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i)
                isPrime[j] = false;
        }
        
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime[i]) count++;
        
        return count;
    }
}

// Power of Three
class Solution {
    public boolean isPowerOfThree(int n) {
        int result = (int) (Math.log(n) / Math.log(3) + 1e-10);
        return n==0 ? false : (int) Math.pow(3, result) == n;
    }
}

// Roman to Integer
class Solution {
    public int romanToInt(String s) {
        int result = 0;
        
        for(int i=s.length()-1; i>=0; i--){
            switch(s.charAt(i)){
                case 'M':
                    if(i!=0 && s.charAt(i-1) == 'C'){
                        result+=900;
                        i--;
                    }else
                        result+=1000;
                    break;
                case 'D':
                    if(i!=0 && s.charAt(i-1) == 'C'){
                        result+=400;
                        i--;
                    }else
                        result+=500;
                    break;
                case 'C':
                    if(i!=0 && s.charAt(i-1) == 'X'){
                        result+=90;
                        i--;
                    }else
                        result+=100;
                    break;
                case 'L':
                    if(i!=0 && s.charAt(i-1) == 'X'){
                        result+=40;
                        i--;
                    }else 
                        result+=50;
                    break;
                case 'X':
                    if(i!=0 && s.charAt(i-1) == 'I'){
                        result+=9;
                        i--;
                    }else
                        result+=10;
                    break;
                case 'V':
                    if(i!=0 && s.charAt(i-1) == 'I'){
                        result+=4;
                        i--;
                    }else
                        result+=5;
                    break;
                case 'I':
                    result+=1;
                    break;
            }
        }
        return result;
    }
}

// OTHER --------------------------------------------------------------------------------------------------

// Number of 1 Bits
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // Brute Force
        /*int result = 0;
        for(int i=0; i<32; i++){
            result += n&1;
            n >>= 1;
        }
        return result;*/
        
        // Brian Kernighan
        /*int result = 0;
        while(n!=0){
            n = n & (n-1);
            result++;
        }
        return result;*/
        
        // Bitmask
        n = (n & 0x55555555) + (n >>  1 & 0x55555555);
        n = (n & 0x33333333) + (n >>  2 & 0x33333333);
        n = (n & 0x0F0F0F0F) + (n >>  4 & 0x0F0F0F0F);
        n = (n & 0x00FF00FF) + (n >>  8 & 0x00FF00FF);
        n = (n & 0x0000FFFF) + (n >> 16 & 0x0000FFFF);
        return n;
    }
}

// Hamming Distance
class Solution {
    public int hammingDistance(int x, int y) {
        // Integer.bitCount returns the number of 1 bits
        // ^ is XOR
        return Integer.bitCount(x^y);
    }
}

// Reverse Bits
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // Brute Force
        int result = 0;
        for(int i=0; i<32; i++){
            result <<= 1;
            if((n&1) > 0) 
                result++;
            n >>= 1;
        }
        return result;
        
        // return Integer.reverse(n);
        
        //Bitmask
        //Java does not like this version, I assume bc of the way Java handles signed integers
        /*
        n = ((n & 0xffff0000) >> 16) | ((n & 0x0000ffff) << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;*/
    }
}

// Pascal's Triangle
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> returnList = new ArrayList<>();
        
        // List(i),j = List(i-1),j-1 + List(i-1),j
        
        for(int i=0; i<numRows; i++){
            returnList.add(new ArrayList<>());
            for(int j=0; j<=i; j++){
                if(j == 0 || i == j)
                    returnList.get(i).add(1);
                else
                    returnList.get(i).add(returnList.get(i-1).get(j-1) + returnList.get(i-1).get(j));
            }
        }
        
        return returnList;
    }
}

// Valid Paranthesis
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int length = s.length();
        stack.push(s.charAt(0));
        
        for(int i=1; i<length; i++){
            char temp = stack.peek();
            
            if(temp == '(' && s.charAt(i) == ')' ||
               temp == '{' && s.charAt(i) == '}' ||
               temp == '[' && s.charAt(i) == ']'){
                stack.pop();
                if(stack.isEmpty() && i != length-1){
                    stack.push(s.charAt(i+1));
                    i++;
                }
            }
            else
                stack.push(s.charAt(i));
        }
        
        return stack.isEmpty();
    }
}

// Missing Number
class Solution {
    public int missingNumber(int[] nums) {
        for(int i=0; i<nums.length; i++){
           for(int j=0; j<nums.length; j++){
               if(i == nums[j])
                   break;
               if(j == nums.length-1 && i != nums[j])
                   return i;
           }
        }
        return nums.length;
    }
}