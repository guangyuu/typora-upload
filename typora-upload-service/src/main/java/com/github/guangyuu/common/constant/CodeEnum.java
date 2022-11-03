package com.github.guangyuu.common.constant;

/**
 * @Author Guangyu
 * @Date 2022/10/8
 * @Description 编码枚举类
 **/
public enum CodeEnum implements Code {
    /**
     * {DESC}
     */
    SUCCESS(0, "操作成功"),
    AUTH_SUCCESS(0, "认证成功"),
    FAIL(1, "操作失败"),
    AUTH_FAILED(2, "认证失败"),
    FIRST_LOGIN(3, "认证成功,请修改密码"),
    SYSTEM_ERROR(10000, "系统异常");
    /**
     * 编码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return super.toString() + " {" + "code=" + code + ", msg='" + msg + '\'' + "} ";
    }

    /**
     * 编码
     *
     * @return code
     */
    @Override
    public Integer code() {
        return this.code;
    }

    /**
     * 消息
     *
     * @return msg
     */
    @Override
    public String msg() {
        return this.msg;
    }
}

interface Code {
    /**
     * 编码
     *
     * @return code
     */
    Integer code();

    /**
     * 消息
     *
     * @return msg
     */
    String msg();

}

