package net.zeldadungeons.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.zeldadungeons.ZeldaDungeons;

public class Log 
{
	private static Logger logger;
	
	public static Logger getLogger() {
		
		if(logger == null)
		{
			logger = LogManager.getFormatterLogger(ZeldaDungeons.MODID);
		}
		return logger;
	}
	
	public static void logString(String str)
	{
		Log.getLogger().info(str);
	}
	
	public static void logInt(int i)
	{
		Log.getLogger().info(i);
	}
}
