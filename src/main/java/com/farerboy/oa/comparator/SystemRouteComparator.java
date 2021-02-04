package com.farerboy.oa.comparator;


import com.farerboy.oa.model.SystemRoute;

import java.util.Comparator;

/**
 * 权限排序
 * 
 */
public class SystemRouteComparator implements Comparator<SystemRoute> {

	@Override
	public int compare(SystemRoute o1, SystemRoute o2) {
		int i1 = o1.getSeq() != null ? o1.getSeq().intValue() : -1;
		int i2 = o2.getSeq() != null ? o2.getSeq().intValue() : -1;
		return i1 - i2;
	}

}
