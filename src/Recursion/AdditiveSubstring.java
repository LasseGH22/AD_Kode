package Recursion;

public class AdditiveSubstring {
    public static void main(String[] args) {
        System.out.println(additive("82842605"));
    }

    private static boolean additive(String s) {
        char[] chars = s.toCharArray();

        if (chars.length < 3) {
            return false;
        }

        if (Character.getNumericValue(chars[0]) + Character.getNumericValue(chars[1]) == Character.getNumericValue(chars[2])) {
            return true;
        }

        String returnString = "";
        for (int i = 1; i < chars.length; i++) {
            returnString += chars[i];
        }

        return additive(returnString);
    }
}
