public record Patient(String id, String name, String dateOfBirth) implements Comparable<Patient> {

    public static Patient fromString(String csv) {
        String[] fields = csv.split("☀");
        return new Patient(fields[0], fields[1], fields[2]);
    }

    @Override
    public String toString() {
        return id + "☀" + name + "☀" + dateOfBirth;
    }

    @Override
    public int compareTo(Patient other) {
        return this.id.compareTo(other.id);
    }
}
