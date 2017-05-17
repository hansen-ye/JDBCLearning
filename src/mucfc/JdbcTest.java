package mucfc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	public void CreateTableTest() {
		// 獲取連接
		Connection conn2 = SqlDB.getConnection();
		Statement stmt = null;
		try {
			stmt = conn2.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 準備sql語句
		String sql = "CREATE TABLE student(sid INT PRIMARY KEY,sname VARCHAR(20),age INT)";
		System.out.println("CREATE TABLE student......");

		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SqlDB.close(stmt, conn2);
	}

	public void InsertTest() {
		// 獲取連接
		Connection conn2 = SqlDB.getConnection();
		Statement stmt = null;
		try {
			stmt = conn2.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 執行插入操作
		System.out.println("Inserting records into the table...");

		String sql = "INSERT INTO student " + "VALUES (100, '小文', 18)";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		sql = "INSERT INTO student " + "VALUES (101, '大林', 25)";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		sql = "INSERT INTO student " + "VALUES (102, '阿白',  30)";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		sql = "INSERT INTO student " + "VALUES(103, '小小', 28)";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.println("Inserted records into the table...");
		SqlDB.close(stmt, conn2);
	}

	public void SelectTest() {
		// 獲取連接
		Connection conn2 = SqlDB.getConnection();
		Statement stmt = null;
		try {
			stmt = conn2.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 準備SQL語句
		String sql = "select * from student";
		// 調用executeQuery執行查詢語句
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 查詢結束後res會指向表頭，想要獲取數據必須不斷地指向查詢結果的下一行，當沒有下一行數據時，返回0.
		System.err.println("select  records from the table...");

		try {
			while (rs.next()) {
				// 先獲取數據
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				int age = rs.getInt("age");
				// 打印結果
				System.out.print("sid:" + sid);
				System.out.print(" sname:" + sname);
				System.out.println(" age:" + age);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SqlDB.close(stmt, conn2);
	}

	public static void main(String[] args) {
		JdbcTest jdbcTest = new JdbcTest();
		jdbcTest.CreateTableTest();
		jdbcTest.InsertTest();
		jdbcTest.SelectTest();
	}

}
