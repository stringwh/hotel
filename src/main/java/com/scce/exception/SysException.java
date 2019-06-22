package com.scce.exception;

/**
 * @program: IdeaProjects
 * @description 自定义异常类
 * @author: Lxy
 * @create: 2019-05-07 10:07
 **/
public class SysException  extends  Exception{

    private String  message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public SysException(String message) {
        this.message = message;
    }

    public SysException() {
    }
}
