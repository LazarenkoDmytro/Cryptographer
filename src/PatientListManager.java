import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PatientListManager {
    private final ArrayList<Patient> patientList;

    public PatientListManager() {
        patientList = new ArrayList<>();
    }

    public void clear() {
        patientList.clear();
    }

    public void add(Patient patient) {
        patientList.add(patient);
    }

    public boolean encodeToFile(File file, String key) {
        try (PrintWriter writer = new PrintWriter(file)) {

            for (Patient patient : patientList) {
                String encoded = Cryptographer.encode(patient.toString(), key);
                writer.println(encoded);
            }

            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public boolean decodeFromFile(File file, String key) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                String decoded = Cryptographer.decode(encoded, key);
                Patient patient = Patient.fromString(decoded);
                add(patient);
            }

            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void sort() {
        Collections.sort(patientList);
    }

    @Override
    public String toString() {
        StringBuilder patientInfo = new StringBuilder();

        for (Patient patient : patientList) {
            String[] data = patient.toString().split("☀");

            patientInfo.append("Patient id            : ").append(data[0]).append("\n");
            patientInfo.append("Patient name          : ").append(data[1]).append("\n");
            patientInfo.append("Patient date of birth : ").append(data[2]).append("\n\n");
        }

        return patientInfo.toString();
    }
}