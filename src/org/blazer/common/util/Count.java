package org.blazer.common.util;

public class Count {

	int count;

	int interval;

	public Count(int count, int interval) {
		this.count = count;
		this.interval = interval;
	}

	public Count(int count) {
		this.count = count;
		this.interval = 5000;
	}

	public int getCount() {
		return count;
	}

	public int getPrevious() {
		int pre = count - 1;
		return pre < 0 ? 0 : pre;
	}

	public void add(int number) {
		this.count += number;
	}

	public int getInterval() {
		return interval;
	}
	
	public boolean modZero() {
		return this.count % this.interval == 0;
	}

}
