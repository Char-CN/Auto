package org.blazer.dataload.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtil {

	public static List<HashMap.Entry<String, String>> sorts(Set<Entry<String, String>> sets) {
		List<HashMap.Entry<String, String>> sorts = new ArrayList<HashMap.Entry<String, String>>(sets);
		Collections.sort(sorts, new Comparator<HashMap.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		return sorts;
	}

}
