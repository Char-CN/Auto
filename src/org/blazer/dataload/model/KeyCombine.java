package org.blazer.dataload.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyCombine {

	private String keyLower = "";

	private List<String> keyList;

	private List<String> keyPointList;

	public List<String> getKeyPointList() {
		return keyPointList;
	}

	public void setKeyPointList(List<String> keyPointList) {
		this.keyPointList = keyPointList;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
		StringBuilder sb = new StringBuilder("");
		if (keyList != null) {
			for (String str : this.keyList) {
				sb.append(str == null ? null : str.toLowerCase());
			}
		}
		keyLower = sb.toString();
	}

	@Override
	public int hashCode() {
		return keyLower.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// 进入equals一定会先check通过hashCode,因此该for循环里的get(i)不会抛出indexoutofbounds异常
		KeyCombine kc = (KeyCombine) obj;
		for (int i = 0; i < keyList.size(); i++) {
			boolean flag1 = keyList.get(i) == null;
			boolean flag2 = kc.keyList.get(i) == null;
			if (flag1 && flag2) {
				// 都为null
				continue;
			} else if (flag1 || flag2) {
				// 有一个为null
				return false;
			} else if (!keyList.get(i).equalsIgnoreCase(kc.keyList.get(i))) {
				// 没有为null的, 不区分大小写匹配值是否相等(Mysql不区分大小写), 不相等返回false
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("keyPoint[");
		for (String keyPoint : keyPointList) {
			sb.append(keyPoint + ",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("],");
		sb.append("keyList[");
		for (String key : keyList) {
			sb.append(key + ",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {

		testValidity();

		System.out.println();
		testSpeed();

	}

	public static void testValidity() {
		List<String> pointList = new ArrayList<String>();
		pointList.add("#A#");
		pointList.add("#B#");
		pointList.add("#C#");
		pointList.add("#D#");

		HashMap<KeyCombine, String> map = new HashMap<KeyCombine, String>();
		KeyCombine kc1 = new KeyCombine();
		List<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("null");
		list1.add(null);
		list1.add("a");
		kc1.setKeyList(list1);
		kc1.setKeyPointList(pointList);
		map.put(kc1, null);

		KeyCombine kc2 = new KeyCombine();
		List<String> list2 = new ArrayList<String>();
		list2.add("1");
		list2.add("null");
		list2.add(null);
		list2.add("a");
		kc2.setKeyList(list2);
		kc2.setKeyPointList(pointList);

		KeyCombine kc3 = new KeyCombine();
		List<String> list3 = new ArrayList<String>();
		list3.add("1");
		list3.add("null");
		list3.add("null");
		list3.add("a");
		kc3.setKeyList(list3);
		kc3.setKeyPointList(pointList);

		KeyCombine kc4 = new KeyCombine();
		List<String> list4 = new ArrayList<String>();
		list4.add("1");
		list4.add("null");
		list4.add(null);
		list4.add("A");
		kc4.setKeyList(list4);
		kc4.setKeyPointList(pointList);

		KeyCombine kc5 = new KeyCombine();
		List<String> list5 = new ArrayList<String>();
		list5.add("11");
		list5.add("hehe");
		list5.add("HYY");
		list5.add("Aaa");
		kc5.setKeyList(list5);
		kc5.setKeyPointList(pointList);

		System.out.println("map.containsKey(kc2) true: " + map.containsKey(kc2));
		System.out.println("map.containsKey(kc3) false: " + map.containsKey(kc3));
		System.out.println("map.containsKey(kc4) true: " + map.containsKey(kc4));
		System.out.println("map.containsKey(kc5) false: " + map.containsKey(kc5));
	}

	@SuppressWarnings("unused")
	public static void testSpeed() {
		long begin = System.currentTimeMillis();
		List<String> pointList = new ArrayList<String>();
		pointList.add("#A#");
		pointList.add("#B#");
		pointList.add("#C#");
		pointList.add("#D#");
		HashMap<KeyCombine, String> map = new HashMap<KeyCombine, String>();
		for (int i = 0; i < 1000000; i++) {
			long l1 = System.currentTimeMillis();
			KeyCombine kc = new KeyCombine();
			List<String> list = new ArrayList<String>();
			list.add("key" + i);
			list.add("null");
			list.add(null);
			list.add("a");
			kc.setKeyList(list);
			kc.setKeyPointList(pointList);
			map.put(kc, null);
			long l2 = System.currentTimeMillis();
			// System.out.println("count " + i + ":" + (l2 -l1));
		}
		long end = System.currentTimeMillis();
		System.out.println(map.size() + " count waster time : " + (end - begin) + " ms");
	}

}
