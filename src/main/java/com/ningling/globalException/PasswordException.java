package com.ningling.globalException;

/**
 * 自定义异常类
 */
public class PasswordException extends RuntimeException{

    public PasswordException(){}

    //重写构造方法
    public PasswordException(String msg){
        super(msg);
    }
}
