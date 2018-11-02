package LeetCode08;

class nextElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        for(int i=0; i < nums1.length; i++) {
            int index = 0;
            int element = nums1[i];
            nums1[i] = -1;
            
            for(int j=0; j < nums2.length; j++) {
                if(element == nums2[j]){
                    index = j;
                    break;
                }
            }
            for(int j=index; j < nums2.length; j++) {
                if(element < nums2[j]){
                    nums1[i] = nums2[j];
                    break;
                }
            }
        }
        return nums1;
    }
}