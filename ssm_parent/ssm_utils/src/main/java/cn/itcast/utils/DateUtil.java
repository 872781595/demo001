package cn.itcast.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String formatDateToStr(Date date) {
		
		//使用SimpleDateFormat格式化传递进来的date参数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

}
