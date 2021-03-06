package org.blazer.dataupdate.handle.calcformula.impl;

import java.math.BigDecimal;

import org.blazer.dataupdate.handle.calcformula.CalcFormula;

/**
 * 减法计算
 * 
 * @author heyunyang
 * 
 */
public class Subtract implements CalcFormula {

	@Override
	public String execute(String a, String b) {
		// TODO Auto-generated method stub
		a = a == null || a.equals("") ? "0" : a;
		b = b == null || b.equals("") ? "0" : b;
		BigDecimal bd1 = new BigDecimal(a);
		BigDecimal bd2 = new BigDecimal(b);
		return bd1.subtract(bd2).toString();
	}

}
