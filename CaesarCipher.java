import java.util.Scanner;

public class CaesarCipher {
    private int rotation;

    public CaesarCipher(int rotation) {
        this.rotation = rotation;
    }

    public String encrypt(String message) {
        return transform(message, rotation);
    }

    public String decrypt(String secret) {
        return transform(secret, 26 - rotation); // shifting backward
    }

    private String transform(String original, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : original.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                // A–Z (65–90 in ASCII)
                char newChar = (char) ('A' + (ch - 'A' + shift) % 26);
                result.append(newChar);
            } else if (Character.isLowerCase(ch)) {
                // a–z (97–122 in ASCII)
                char newChar = (char) ('a' + (ch - 'a' + shift) % 26);
                result.append(newChar);
            } else {
                // Non-alphabetic characters remain same
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CaesarCipher cipher = new CaesarCipher(3); // shift = 3

        System.out.println("Choose an option: ");
        System.out.println("1. Encrypt a message");
        System.out.println("2. Decrypt a message");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if (choice == 1) {
            System.out.println("Enter your plain message:");
            String message = sc.nextLine();
            String coded = cipher.encrypt(message);
            System.out.println("Secret: " + coded);
        } else if (choice == 2) {
            System.out.println("Enter your secret message:");
            String secret = sc.nextLine();
            String answer = cipher.decrypt(secret);
            System.out.println("Message: " + answer);
        } else {
            System.out.println("Invalid choice. Please select 1 or 2.");
        }

        sc.close();
    }
}
