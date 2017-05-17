package mucfc;

public class RunMain {

	public static void main(String[] args) {
		SqlDB sqlDB = new SqlDB();
		sqlDB.CreatTable();
		sqlDB.InsertData(309, "小紅", 12);
		sqlDB.InsertData(33, "小灰", 34);
		sqlDB.InsertData(23, "阿大", 145);
		sqlDB.SelectDataWithId(33);
	}

}
