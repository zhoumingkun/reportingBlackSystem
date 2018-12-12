package com.toughguy.reportingSystem.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.toughguy.reportingSystem.model.business.Information;

public class ListSortUtil {

	/**
	 * 按创建时间排序
	 * @param list
	 */
	public static void ListSort(List<Information> list) {
		Collections.sort(list, new Comparator<Information>() {
			@Override
			public int compare(Information o1, Information o2) {
			    try {
			        Date dt1 = o1.getCreateTime();
			        Date dt2 = o2.getCreateTime();
			        if (dt1.getTime() > dt2.getTime()) {
			            return 1;
			        } else if (dt1.getTime() < dt2.getTime()) {
			            return -1;
			        } else {
			            return 0;
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			    return 0;
			}
		});
	}
}
