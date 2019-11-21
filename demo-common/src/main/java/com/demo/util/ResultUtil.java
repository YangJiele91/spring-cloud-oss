package com.demo.util;


import com.demo.model.Result;

/**
 * 统一接口响应结果封装工具
 */
public class ResultUtil {

	/**
	 * 响应成功 (不需要返回业务数据)
	 * @return
	 */
	public static<T> Result<T> success() {
		return new Result<T>(0, null, null);
	}

	/**
	 * 响应成功 (需要返回业务数据)
	 * @param <T> 业务数据类型
	 * @param data 业务数据
	 * @return
	 */
	public static<T> Result<T> success(T data) {
		return new Result<T>(0, null, data);
	}

	/**
	 * 响应失败 (不需要返回业务数据)
	 * @param code 错误码
	 * @param errMsg 错误信息
	 * @return
	 */
	public static<T> Result<T> error(Integer code, String errMsg) {
		return new Result<T>(code, errMsg, null);
	}

	/**
	 * 响应失败 (需要返回业务数据)
	 * @param code 错误码
	 * @param errMsg 错误信息
	 * @param <T> 业务数据类型
	 * @param data 业务数据
	 * @return
	 */
	public static<T> Result<T> error(Integer code, String errMsg, T data) {
		return new Result<T>(code, errMsg, data);
	}
}
