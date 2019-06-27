package com.xin.admin.common.enums;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类功能描述:错误码枚举类
 *
 * @author Eternal
 * @date 2018/7/22 1:16
 */
public enum ErrorCodeEnum {

	/**
	 * 分段定义  xx/yy
	 *
	 * xx:代表模块：10系统模块
	 * yy:代表功能模块：
	 */
	/****************************** 通用系统错误 **************************************/
	SYS_ERR_GLOBAL(1000, "系统处理异常，请稍后重试"),
	SYS_ERR_CREATE_FAILED(1001,"新增数据失败"),
	SYS_ERR_UPDATE_FAILED(1002,"修改数据失败"),
	SYS_ERR_DELETE_FAILED(1003,"删除数据失败"),
	SYS_ERR_SEARCH_FAILED(1004,"查询数据失败"),
	SYS_ERR_COUNT_FAILED(1005,"查询数据总数失败"),
	SYS_ERR_DUPLICATED_DATA(1006,"数据已存在"),
	SYS_ERR_RECORD_NOT_FOUND(1007, "数据不存在"),

	/****************************** http请求错误 **************************************/
	SYS_ERR_HTTP_METHOD_NOT_ALLOWED(2001, "不支持当前请求方法，{0}"),
	SYS_ERR_HTTP_UNSUPPORTED_MEDIA_TYPE(2002, "不支持的媒体类型，{0}"),

	/****************************** 请求校验错误 **************************************/
	SYS_ERR_VALIDATION_MISSING_PARAMS(3001, "请求参数校验失败，缺少请求参数：{0}"),
	SYS_ERR_VALIDATION_PARAMS_ERROR(3002, "请求参数校验失败，{0}"),
	SYS_ERR_VALIDATION_PARAMS_TYPE_ERROR(3003, "请求参数校验失败，{0}:参数类型为{1}"),
	SYS_ERR_VALIDATION_PARAMS_JSON_TYPE_ERROR(3004, "请求参数校验失败，{0}：JSON格式不正确"),
	SYS_ERR_VALIDATION_BODY_JSON_TYPE_ERROR(3005, "请求参数校验失败，请求体JSON格式不正确"),








	SYS_ERR_FILE_DOWNLOAD(1011,"文件下载失败"),
	SYS_ERR_FILE_UPLOAD(1012,"文件上传失败");



	/****************************** 分隔符 **************************************/

	private Integer code;

	private String msg;

	private Object[] params;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return MessageFormat.format(msg, params);
	}

	ErrorCodeEnum(Integer code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}


	public ErrorCodeEnum setParam(Object... param)
	{
		List<Object> list = new ArrayList<>(param.length);
		list.addAll(Arrays.asList(param));
		this.params = list.toArray(new Object[param.length]);
		return this;
	}

}
