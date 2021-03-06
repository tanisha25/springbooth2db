package com.demo.app.utils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author tanisha
 *
 */
public class Utils {

	public static long getCurrentTimeInUTC()
	{
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(Constants.UTC));
		return zonedDateTime.toInstant().toEpochMilli();
	}
	
	public static <T> Boolean isEmpty(T object)
	{
		if(object != null && !"".equals(object))
		   return false;
		return true;
	}
	
	public static <T> Boolean isEmpty(String str)
	{
		if(str != null && str.length() > 0 && !str.trim().equals(""))
		   return false;
		return true;
	}
	
	public static <T> Boolean isEmpty(List<T> objects)
	{
		if(objects != null && objects.size() > 0)
			return false;
		return true;
	}
	
	
	public static int getCurrentYear(long timestamp) {
		Date dateTime = new Date(timestamp);
        SimpleDateFormat df2 = new SimpleDateFormat(Constants.YEAR_FORMAT);
        return Integer.valueOf(df2.format(dateTime));

	}

}
