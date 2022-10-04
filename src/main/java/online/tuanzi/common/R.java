package online.tuanzi.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import online.tuanzi.constant.ResultCode;

/**
 * @ClassName: Result
 * @Author: 团子tz
 * @CreateTime: 2022/10/03 17:33
 * @Description: 全局统一返回结果集
 */
@Data
public class R<T> {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    //把构造方法私有,其他人不能创建新的对象
    private R() {}

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getValue());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR.getValue());
        r.setMessage(ResultCode.ERROR.getMsg());
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(T data){
        this.data = data;
        return this;
    }
}
