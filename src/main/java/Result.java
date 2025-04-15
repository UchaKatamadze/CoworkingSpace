public class Result<T> {
    private final T data;
    private final boolean success;
    private final String message;

    private Result(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, true, "");
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(null, false, message);
    }

    // Getters

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}