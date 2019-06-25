package com.xin.admin.common.constant;
/**
 * 类功能描述: 定义常量
 *
 * @author Eternal
 * @date 2018/7/22 0:19
 */
public class CommonConstants {

	/**
	 * Result Code状态
	 */
	public static class ResultCodeStatus
	{
		/**
		 RESULT_SUCCESS*
		 */
		public final static int RESULT_SUCCESS = 0;
		/**
		 * RESULT_FAILURE
		 */
		public final static int RESULT_FAILURE = -1;

	}

	/**
	 * Result Message
	 */
	public static class ResultCodeMessage
	{
		/**
		 * RESULT_SUCCESS_MESSAGE
		 */
		public final static String RESULT_SUCCESS_MESSAGE = "Request is success !";
		/**
		 * RESULT_FAILURE_MESSAGE
		 */
		public final static String RESULT_FAILURE_MESSAGE = "Request is failure !";

	}

	/**
	 * Delete Code状态
	 */
	public static class DeleteCodeStatus
	{
		/**
		 * IS_NOT_DELETE
		 */
		public final static int IS_NOT_DELETE = 0;
		/**
		 * IS_DELETE
		 */
		public final static int IS_DELETE = 1;

	}

}
