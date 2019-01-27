//STEP 1. Import required packages
import java.sql.*;

public class DAO_Demo {
	public static void main(String[] args) {
		try{
			DAO_Factory daoFactory = new DAO_Factory();

			daoFactory.activateConnection();

			StudentDAO sdao = daoFactory.getStudentDAO();
			Student s = sdao.getStudentByKey(1);
			s.print();

			daoFactory.deactivateConnection();
		}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}//end main
}//end FirstExample
