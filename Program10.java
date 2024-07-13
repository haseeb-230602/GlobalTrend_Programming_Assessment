public class PalindromeChecker {

    public boolean isPalindrome(String s) {
        // Normalize the string: convert to lowercase and remove spaces and punctuation
        String normalized = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        // Check if the normalized string is a palindrome
        int left = 0;
        int right = normalized.length() - 1;

        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();

        // Test cases
        String str1 = "A man, a plan, a canal, Panama";
        String str2 = "race a car";
        String str3 = "Madam";
        String str4 = "No 'x' in Nixon";
        String str5 = "Was it a car or a cat I saw?";

        System.out.println("\"" + str1 + "\" is a palindrome? " + checker.isPalindrome(str1)); // true
        System.out.println("\"" + str2 + "\" is a palindrome? " + checker.isPalindrome(str2)); // false
        System.out.println("\"" + str3 + "\" is a palindrome? " + checker.isPalindrome(str3)); // true
        System.out.println("\"" + str4 + "\" is a palindrome? " + checker.isPalindrome(str4)); // true
        System.out.println("\"" + str5 + "\" is a palindrome? " + checker.isPalindrome(str5)); // true
    }
}
