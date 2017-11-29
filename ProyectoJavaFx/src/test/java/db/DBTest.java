package db;
import java.sql.ResultSet;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import db.MySQLDriver;

public class DBTest {
@Rule public ContiPerfRule i=new ContiPerfRule();
static MySQLDriver mysql;
	@BeforeClass
	public static void initialize() {
		mysql=new MySQLDriver();
	}
	@Test 
	@PerfTest(invocations=100,threads=20)
	@Required(max=20000,average=3000)
	public void prueba() throws InterruptedException{
		String playedTournaments="select count(*) from panenka_db.contests_contest where MONTH(close_date) = MONTH(CURDATE()) AND MONTH(created_date) = MONTH(CURDATE()) ;";
		ResultSet type1=mysql.runQuery(playedTournaments);
	}
	@Test 
	@PerfTest(invocations=20,threads=20)
	@Required(max=20000,average=3000)
	public void prueba2() throws InterruptedException{
		String usersMonth="SELECT count(DISTINCT \"user_id\") FROM panenka_db.users_login where MONTH(login_date)=MONTH(CURDATE())";
		ResultSet type5=mysql.runQuery(usersMonth);
		Double usersThisMonthParcial=new Double(0);
	}
}
