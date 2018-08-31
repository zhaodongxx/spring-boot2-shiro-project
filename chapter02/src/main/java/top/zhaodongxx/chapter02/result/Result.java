package top.zhaodongxx.chapter02.result;

/**
 *
 * <p>封装统一的返回结果</p>
 * Created by zhaod on 2018/1/24 17:39
 *
 */
public class Result<T> {
    private ResultCodeEnum code;
    private String message;
    private  T data;

    public ResultCodeEnum getCode() {
        return code;
    }

    public Result() {
    }

    public Result setCode(ResultCodeEnum resultCode) {
        this.code = resultCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
