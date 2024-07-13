import java.util.Random;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left);
        
        pivotIndex = partition(nums, left, right, pivotIndex);
        
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;
        
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        
        swap(nums, right, storeIndex);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();
        
        int[] nums1 = {3,2,1,5,6,4};
        int k1 = 2;
        System.out.println("The " + k1 + "th largest element is: " + solution.findKthLargest(nums1, k1)); // Output: 5
        
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;
        System.out.println("The " + k2 + "th largest element is: " + solution.findKthLargest(nums2, k2)); // Output: 4
    }
}
