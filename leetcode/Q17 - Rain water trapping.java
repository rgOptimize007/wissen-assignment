/*
42. Trapping Rain Water
Hard

23051

314

Add to List

Share
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105

*/


/*

Faster approach

class Solution {
    public int trap(int[] height) {
        int leftMax = 0;
        int rightMax =0;
        
        int left = 0;
        int right = height.length -1;
        
        int res = 0;
        
        while (left < right) {
            
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            
            if (leftMax < rightMax) {
                res = res + (leftMax - height[left]);
                left++;
            } else {
                res = res + (rightMax - height[right]);
                right--;
            }
        }
        return res;
    }
}


*/


class Solution {
    public int trap(int[] height) {
        
        int i = 0;
        int totalWater = 0;
        int left = 0;
        int right = height.length-1;
        int preHigh = 0;
        
        while(left < right){
           while(left < right && height[left] <= preHigh)
               left++;
            
           if(left >= right)
               break;
            
           while(left < right && height[right] <= preHigh)
               right--;
    
           if(left >= right)
               break;
            
           int totalBlocks = right-left-1;
           int minIdx = (height[left]< height[right])?left:right;
           int sum = findSum(height,left+1,right-1,minIdx,preHigh);
           totalWater += (height[minIdx]*totalBlocks) - sum;
           preHigh = height[minIdx];
        }
      return totalWater;
    }
    
    public int findSum(int[] height , int i , int n,int minIdx,int preHigh){
        int sum = 0;
        for(int j=i;j<=n;j++){
            if(height[j] <= height[minIdx]){
              if(height[j] < preHigh)
                  sum += preHigh;
              else
               sum += height[j];
            }
            else
               sum += height[minIdx];
        }
        return sum;
    }
}