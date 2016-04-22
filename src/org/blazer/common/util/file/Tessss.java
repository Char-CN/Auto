package org.blazer.common.util.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.blazer.common.util.SourceUtil;

public class Tessss {


	public static void main(String[] args) throws IOException {

		final List<String> list = new ArrayList<String>();
		new FReader(SourceUtil.resource + "orgs.csv").each(new FEach() {
			@Override
			public void handle(String row) throws IOException {
				// System.out.println("in ~~~");
				list.add(row);
				System.out.println(row);
				// System.out.println("out ~~");
			}
		});
		System.out.println(list.size());
	}

}
