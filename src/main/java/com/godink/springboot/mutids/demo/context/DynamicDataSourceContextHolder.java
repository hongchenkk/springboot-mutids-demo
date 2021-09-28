package com.godink.springboot.mutids.demo.context;

import java.util.ArrayList;
import java.util.List;

import com.godink.springboot.mutids.demo.enums.DataSourceKey;

public class DynamicDataSourceContextHolder {
	
	private static ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal
			.withInitial(() -> DataSourceKey.MASTER.getName());
	
	public static List<Object> dataSourceKeys = new ArrayList<>();

	public static void setDataSourceKey(String key) {
		CONTEXT_HOLDER.set(key);
	}
	
	public static Object getDataSourceKey() {
		return CONTEXT_HOLDER.get();
	}
	
	public static void clearDataSourceKey() {
		CONTEXT_HOLDER.remove();
	}
	
	public static boolean containsDataSourceKey(String key) {
		return dataSourceKeys.contains(key);
	}
}
