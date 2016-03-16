package org.blazer.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.blazer.common.util.ApplicationUtil;
import org.blazer.common.util.SourceUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class InitDaykey {

	public static void main(String[] args) throws ParseException {
		ApplicationUtil.init(SourceUtil.resource + "logback_autoload.xml");

		JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationUtil.getBean("jdbcTemplate");
		System.out.println(jdbcTemplate.queryForList("show databases"));
		Calendar c = Calendar.getInstance();
		String beginDateStr = "2015-01-01";
		c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(beginDateStr));
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 365 * 10; i++) {
			String s1 = sdf1.format(c.getTime());
			String s2 = sdf2.format(c.getTime());
			
			jdbcTemplate.update("insert into test.Dim_DayKey(DayKey, `yyyy-MM-dd`) values ('"+s1+"', '"+s2+"')");
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
	}

}
