package com.farerboy.oa.comparator;

import com.farerboy.oa.model.SystemMenu;
import java.util.Comparator;


/**
 * 菜单排序
 * 
 */
public class SystemMenuComparator implements Comparator<SystemMenu> {

	@Override
	public int compare(SystemMenu o1, SystemMenu o2) {
		int i1 = o1.getSeq() != null ? o1.getSeq().intValue() : -1;
		int i2 = o2.getSeq() != null ? o2.getSeq().intValue() : -1;
		return i1 - i2;
	}

}
