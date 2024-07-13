public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Test cases
        int[] heights1 = {1,8,6,2,5,4,8,3,7};
        int[] heights2 = {1,1};
        int[] heights3 = {4,3,2,1,4};
        int[] heights4 = {1,2,1};

        System.out.println("Max area for heights1: " + solution.maxArea(heights1)); // 49
        System.out.println("Max area for heights2: " + solution.maxArea(heights2)); // 1
        System.out.println("Max area for heights3: " + solution.maxArea(heights3)); // 16
        System.out.println("Max area for heights4: " + solution.maxArea(heights4)); // 2
    }
}
