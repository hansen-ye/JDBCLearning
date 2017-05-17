package mucfc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 數據庫操作類的一個簡單封裝 
 * 出處: http://blog.csdn.net/evankaka/article/details/45370609
 */
public class SqlDB {
	// 定義數據庫驅動程序
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	// 數據庫連接地址
	private static final String DBURL = "jdbc:mysql://localhost:3306/school";// school表示數據庫
	// 數據庫用戶名
	private static final String DBUSER = "root";
	// 電腦上的數據庫密碼
	private static final String DBPASS = "6789";

	/**
	 * 創建數據庫連接一初始操作
	 * 
	 * @return 數據庫連接的句柄
	 */
	@SuppressWarnings("unused")
	public static Connection getConnection() {
		Connection conn = null;
		// 1.註冊驅動
		try {
			Class.forName(DBDRIVER);
			// 2.獲取連接
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			// 3.創建Statement對象
			Statement stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 關閉資源
	 * 
	 * @param statement為執行命令的實例
	 *            ，connection為連接對象
	 */
	public static void close(Statement stmt, Connection conn) {
		{
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 創建一個數據庫表，注意這裡的表要事先存在的
	 */
	public void CreatTable() {
		// 1.獲取連接
		Connection connection = SqlDB.getConnection();
		// 2.準備sql語句
		String sql = "CREATE TABLE student(sid INT PRIMARY KEY,sname VARCHAR(20),age INT)";
		PreparedStatement preparedStatement = null;
		// 3.獲得對象
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 4.交給數據庫執行SQL
		int num = 0;
		try {
			num = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("有" + num + "條記錄受到了影響");
		SqlDB.close(preparedStatement, connection);
	}

	/**
	 * 執行插入操作
	 * 
	 * @param 相應的參數
	 */
	public void InsertData(int id, String name, int age) {
		// 1.獲取連接
		Connection connection = SqlDB.getConnection();
		// 2.準備sql語句
		String sql = "INSERT INTO student VALUES(?,?,?)";
		PreparedStatement preparedStatement = null;
		// 3.獲得對象
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 4.設置SQL參數 需要參數是第幾個，並且知道它的類型 下面第一句表示：SQL語句第一個參數是int類型，參數值設置為id，如此類推
		try {
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, age);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// 5.交給數據庫執行SQL
		int num = 0;
		try {
			num = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("有" + num + "條記錄受到了影響");
		SqlDB.close(preparedStatement, connection);

	}

	/**
	 * 執行查找操作
	 * 
	 * @param id
	 *            學生編號
	 */
	public void SelectDataWithId(int id) {
		// 1.獲取連接
		Connection connection = SqlDB.getConnection();
		// 2.準備sql語句
		String sql = "SELECT* FROM student where sid=?";
		PreparedStatement preparedStatement = null;
		ResultSet res = null;
		// 3.獲得對象
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * 4.設置SQL參數 需要參數是第幾個，並且知道它的類型 下面第一句表示：SQL語句第一個參數是int類型，參數值設置為id，如此類推
		 */
		try {
			preparedStatement.setInt(1, id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// 5.交給數據庫執行SQL
		try {
			res = preparedStatement.executeQuery();
			while (res.next()) {
				// 先獲取數據
				int sid = res.getInt("sid");
				String sname = res.getString("sname");
				int age = res.getInt("age");
				// 打印結果
				System.out.print("sid: " + sid);
				System.out.print("  sname: " + sname);
				System.out.println("  age: " + age);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		SqlDB.close(preparedStatement, connection);
	}
}
