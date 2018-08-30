package top.zhaodongxx.result;

/**
 * 响应码 枚举，参考HTTP状态码的语义
 *
 * Created by zhaod on 2018/1/24 17:42
 */
public enum ResultCodeEnum {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    public int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
