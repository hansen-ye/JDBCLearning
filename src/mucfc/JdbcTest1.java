package mucfc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * JDBC 基本流程順序
 */
public class JdbcTest1 {
	// 定義數據庫驅動程序
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	// 數據庫連接地址
	private static final String DBURL = "jdbc:mysql://localhost:3306/school";//school表示數據庫 
	// 數據庫用戶名
	private static final String DBUSER = "root";
	// 電腦上的數據庫密碼
	private static final String DBPASS = "6789";

	public void testDDL() {
		try {
			// 1.註冊驅動
			Class.forName(DBDRIVER);
			// 2.獲取連接
			Connection conn = DriverManager
					.getConnection(DBURL, DBUSER, DBPASS);
			// 3.創建Statement對象
			Statement stmt = conn.createStatement();
			// 4.準備sql語句
			String sql = "CREATE TABLE student(sid INT PRIMARY KEY,sname VARCHAR(20),age INT)";
			// 5.通過statement對象發送sql語句,返回執行結果
			int count = stmt.executeUpdate(sql);
			System.out.println("CREATE TABLE student......");
			// 6.打印執行結果
			System.out.println("影響了" + count + "條記錄");

			// 執行插入操作
			System.out.println("Inserting records into the table...");
			sql = "INSERT INTO student " +  
                    "VALUES (100, '小文', 18)";  
			stmt.executeUpdate(sql);  
			sql = "INSERT INTO student " +  
                    "VALUES (101, '大林', 25)";  
			stmt.executeUpdate(sql);  
			sql = "INSERT INTO student " +  
                    "VALUES (102, '阿白',  30)";  
			stmt.executeUpdate(sql);  
			sql = "INSERT INTO student " +  
                    "VALUES(103, '小小', 28)";  
			stmt.executeUpdate(sql);  
			System.out.println("Inserted records into the table...");

			// 執行查找操作
			sql = "SELECT* FROM student";
			System.out.println("SELECT records FROM the table...");
			ResultSet rs = stmt.executeQuery(sql);
			// 輸出查找結果
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
			rs.close();
			// 7.關閉資源
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		JdbcTest1 jdbcTest = new JdbcTest1();
		jdbcTest.testDDL();
		
	}

}
