public class Cryptographer {
    private Cryptographer() {
    }

    public static String encode(String text, String key) {
        int offset = keyToOffset(key);
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '☀') {
                chars[i] = (char) ((chars[i] + offset) % 256);
            }
        }

        return new String(chars);
    }

    public static String decode(String text, String key) {
        int offset = keyToOffset(key);
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '☀') {
                chars[i] = (char) ((chars[i] - offset + 256) % 256);
            }
        }

        return new String(chars);
    }

    private static int keyToOffset(String key) {
        int sum = 0;

        for (char c : key.toCharArray()) {
            sum += c;
        }

        int offset = sum % 256;

        if (offset < 32) {
            offset += 32;
        }

        return offset;
    }
}
