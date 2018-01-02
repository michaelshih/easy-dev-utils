package com.itoolshub.easy.convert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.function.Function;

/**
 * @author Quding Ding
 * @since 2017/12/1
 */
public class FuncitionConvertUtil {

  private static FastDateFormat YMDHMS_ = FastDateFormat.getInstance("yyyy-MM-dd hh:MM:ss");

  /**
   * date转yyyy-MM-dd hh:MM:ss形式转换器
   */
  public static Function date2String = v -> {
    if (v instanceof Date) {
      return YMDHMS_.format(v);
    }
    return String.valueOf(v);
  };

  /**
   * 小写转大写过程
   */
  public static Function upperString = v -> {
    final String value = String.valueOf(v);
    if (v instanceof String) {
      return StringUtils.upperCase(value);
    }
    return value;
  };

}
