package org.seckill.dto;

/**
 * Created by 中希 on 2016/8/15.
 * 封装一个ajax的返回结果
 */
public class AjaxResult<T> {

    private boolean success ;

    private T data ;

    private String err ;

    public AjaxResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public AjaxResult(boolean success, String err) {
        this.success = success;
        this.err = err;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
