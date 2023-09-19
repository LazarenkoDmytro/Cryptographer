import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PatientListManager manager = new PatientListManager();
        manager.add(new Patient("456789", "Pat Perez", "10/23/1998"));
        manager.add(new Patient("123456", "John Doe", "02/12/1980"));
        manager.add(new Patient("789012", "Jane Smith", "03/25/1995"));

        manager.sort();
        System.out.println("Original patient list:\n" + manager);

        String key = "SecretKey";
        File file = new File("patients.txt");

        if (manager.encodeToFile(file, key)) {
            System.out.println("Encoded patient list with right key");

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while (reader.ready()) {
                    System.out.println(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();

            manager.clear();
            if (manager.decodeFromFile(file, key)) {
                System.out.println("Decoded patient list with right key:\n" + manager);
            } else {
                System.out.println("Unable to read file with right key.");
            }

            manager.clear();
            if (manager.decodeFromFile(file, "WrongKey")) {
                System.out.println("Decoded patient list with wrong key:\n" + manager);
            } else {
                System.out.println("Unable to read file with wrong key.");
            }
        } else {
            System.out.println("Unable to write file.");
        }

        Patient patient = new Patient("436892", "Jack Gray", "30/04/2001"); // Sample of patient
        String rightKey = "RightKey", wrongKey = "WrongKey";
        String encoded = Cryptographer.encode(patient.toString(), rightKey);

        System.out.println("encode(\"" + patient + "\", \"" + rightKey + "\") -- > \"" + encoded);
        System.out.println("decode(\"" + encoded + "\", \"" + rightKey + "\") -- > \"" + Cryptographer.decode(encoded, rightKey));
        System.out.println("decode(\"" + encoded + "\", \"" + wrongKey + "\") -- > \"" + Cryptographer.decode(encoded, wrongKey) + "\n");

        System.out.print("keyToOffset(\"" + rightKey + "\") -- > " + (int) (rightKey.charAt(0)));
        int sum = rightKey.charAt(0);

        for (int i = 1; i < rightKey.length(); i++) {
            System.out.print(" + " + (int) (rightKey.charAt(i)));
            sum += rightKey.charAt(i);
        }

        int newSum = (sum % 256);
        System.out.println(" = " + sum + " % 256 = " + newSum + "\n");

        char character = patient.toString().charAt(7);

        System.out.print(character + "  " + (int) (character) + "  +  " + newSum + "  =  " + ((int) (character) + newSum) + "  " + (char) ((int) (character) + newSum));
    }
}
