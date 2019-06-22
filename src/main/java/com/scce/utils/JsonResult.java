package com.scce.utils;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-03 10:49
 **/

import java.io.Serializable;
import java.util.List;

/**
 * 用于封装AJAX调用以后的JSON返回值
 * 其中正确返回值:
 * {state:0, data:返回数据, message:错误消息}
 * 错误返回值:
 * {state:1, data:null, message:错误消息}
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -3644950655568598241L;
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    /**
     * 返回是否成功的状态, 0表示成功,
     * 1或其他值 表示失败
     */
    private int state;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String message;
    /**
    *返回集合
     * * */
    private List rows;
    /**
    * 返回bootstarp的total值
    * */

    private Long total;




    public JsonResult() {
    }

    public JsonResult(int state, Object data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(int state, List rows, Long total) {
        this.state = state;
        this.rows = rows;
        this.total = total;
    }

    public JsonResult(int state, Object data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public JsonResult(Throwable e) {
        state = ERROR;
        data = null;
        message = e.getMessage();
    }

    public JsonResult(Object data) {
        state = SUCCESS;
        this.data = data;
        message = "";
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
    }

}