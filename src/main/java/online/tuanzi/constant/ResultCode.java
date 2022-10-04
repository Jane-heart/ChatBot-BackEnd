package online.tuanzi.constant;

/**
 * @ClassName: ResultCode
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:37
 * @Description: 想想此类的作用，写在这里吧。
 */
public enum ResultCode {
    /**
     * 广义通用
     */
    SUCCESS(200, "请求成功"),
    ERROR(-1, "错误"),
    NOT_FOUND(-404, "没有找到"),
    NOT_EMPTY(-200, "字段不能为空"),
    DUPLICATE_EXCEPTION(-3, "重复异常");

    private int value;

    private String msg;

    ResultCode(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
