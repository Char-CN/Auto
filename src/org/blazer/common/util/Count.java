package org.blazer.common.util;

public class Count {

	int count;

	int interval;

	int errorCount;

	public Count(int interval, int count) {
		init(interval, count);
	}

	public Count(int interval) {
		init(interval, 0);
	}

	public Count() {
		init(5000, 0);
	}

	public void init(int interval, int count) {
		this.interval = interval;
		this.count = count;
		this.errorCount = 0;
	}

	public int getCount() {
		return count;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public int getPrevious() {
		int pre = count - 1;
		return pre < 0 ? 0 : pre;
	}

	public void add(int number) {
		this.count += number;
	}

	public void addErrorCount(int number) {
		this.errorCount += number;
	}

	public int getInterval() {
		return interval;
	}

	public boolean modZero() {
		return this.count % this.interval == 0;
	}

}
