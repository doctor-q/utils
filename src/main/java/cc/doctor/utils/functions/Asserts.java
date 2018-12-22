package cc.doctor.utils.functions;

public class Asserts {

    private Asserts() {
    }

    public static <T> void notNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
    }

    public static <T> void notNull(T t, String msg) {
        if (t == null) {
            throw new NullPointerException(msg);
        }
    }

    public static <T> void notNull(T t, Exception e) {
        if (t == null) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void notEmpty(Iterable t) {
        if (t == null || !t.iterator().hasNext()) {
            throw new IllegalStateException();
        }
    }

    public static void notEmpty(Iterable t, String msg) {
        if (t == null || !t.iterator().hasNext()) {
            throw new IllegalStateException(msg);
        }
    }

    public static void notEmpty(Iterable t, Exception e) {
        if (t == null || !t.iterator().hasNext()) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> void assertTrue(T t) {
        if (t == null) {
            throw new IllegalStateException();
        }
    }

    public static <T> void assertTrue(T t, String msg) {
        if (t == null) {
            throw new IllegalStateException(msg);
        }
    }

    public static <T> void assertTrue(T t, Exception e) {
        if (t == null) {
            throw new IllegalStateException(e);
        }
    }
}
