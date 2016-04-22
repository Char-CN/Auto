package org.blazer.common.util.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FReader {

	BufferedReader br;
	FileReader fr;

	String c = null;

	public FReader(String path) throws FileNotFoundException {
		this.fr = new FileReader(path);
		this.br = new BufferedReader(this.fr);
	}

	public boolean hasNext() throws IOException {
		return (c = br.readLine()) != null;
	}

	public String next() {
		return c;
	}

	public void each(FEach ii) throws IOException {
		while (hasNext()) {
			ii.handle(next());
		}
		try {
			this.br.close();
		} catch (Exception e) {
		}
		try {
			this.fr.close();
		} catch (Exception e) {
		}
	}

}
