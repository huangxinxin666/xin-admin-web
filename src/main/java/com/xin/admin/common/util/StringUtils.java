package com.xin.admin.common.util;

/**
 * 类功能描述:　String工具类
 *
 * @author Eternal
 * @date 2019/5/12 2:47
 */

public class StringUtils {

    /**
      * 方法功能描述: 截取字符串
      *
      * @param str 目标字符串
      * @param strStart 开始位置
      * @param strEnd 结束位置
      * @return String 截取后字符串
      */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在该字符串里面的位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex;
        if(strStart.equals(strEnd)){
            strEndIndex = str.lastIndexOf(strEnd);
        }else {
            strEndIndex = str.indexOf(strEnd);
        }

        /* index 为负数 即表示该字符串中没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }

        return str.substring(strStartIndex, strEndIndex).substring(strStart.length());
    }

}
