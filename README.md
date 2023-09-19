# Cryptographer
This class houses the encoding/decoding code in methods. Contains a no-parameter private constructor containing no code.
### Static methods include:
* encode – a public method that accepts two String parameters, the text to be encoded and an
encrypting key. It returns a String representing the encoded text.
* decode – a public method that accepts two String parameters, the text to be decoded and an
encrypting key. It returns a String representing the decoded text.
* keyToOffset – a private method that accepts one String, the encrypting key, and returns an ASCII
offset to be added to each character during encoding. To do its work, it adds up the ASCII value of each character in the key, then uses modulo math to ensure the offset range is 0 to 255. It also makes sure that if the offset is too small (under 32)—and therefore more easily guessed—it adds 32 to it before returning it.

# Patient
A simple class holding patient data including id, name, and date of birth (all Strings), with a constructor that takes all three pieces of data as parameters. 
* It contains typical accessors, but no mutators.
* It has a toString method that produces a simple CSV-type string, e.g., “456789,Pat Perez,10/23/1998”.
* It has a static fromString method that accepts that type of CSV string and returns a Patient object reference.
* This class also has support for sorting via Comparable/compareTo; we sort patients by their IDs.

# PatientListManager
This class contains methods to support the patient list. Its no-parameter constructor creates an empty ArrayList of Patient object references. 
### The class contains these (instance) methods:
* clear – removes all patients from the list.
* add – accepts one parameter, a Patient, and adds the patient to the list.
* encodeToFile – accepts two parameters, a File reference (the file the client wants to write to), and the encrypting key to use. It writes all patient data in the list (CSV data for each patient, now encrypted) to the file. It returns a boolean parameter indicating whether the writing of the file was done successfully.
* decodeFromFile – same, except that it reads the file and adds Patient records to the list.
* sort – sorts the patient list using natural ordering. Has no parameters or returns. This is a one-statement method.
* toString – returns the patient list as a String consisting of CSV data for patients, one per line.

# Main
This class creates a PatientListManager object, adds a handful of sample Patients, sorts the list, then demonstrates the writing and reading of an encrypted file. It demonstrates reading in two scenarios, (1) using the correct decryption key, and (2) using an incorrect decryption key. If the writing of the file can’t be done, it tells the user that there was a problem and discontinue the demo.