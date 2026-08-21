class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = success ? data : null;
    }

    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(true, message, data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }

    @Override
    public String toString() {
        return String.format("Result[success=%b, message='%s', data=%s]", success, message, data);
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        Result<String> r1 = Result.ok("User_Alice", "查詢成功");
        Result<String> r2 = Result.fail("找不到該使用者");

        Result<Integer> r3 = Result.ok(100, "計算完成");
        Result<Integer> r4 = Result.fail("除數不可為零");

        System.out.println(r1);
        System.out.println("r1 取得資料: " + r1.getData());
        System.out.println(r2);
        System.out.println("r2 失敗資料: " + r2.getData());

        System.out.println(r3);
        System.out.println(r4);
    }
}