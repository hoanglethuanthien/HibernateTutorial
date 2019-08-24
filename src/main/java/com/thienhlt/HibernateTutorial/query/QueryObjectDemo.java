package com.thienhlt.HibernateTutorial.query;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thienhlt.HibernateTutorial.entities.Employee;
import com.thienhlt.HibernateTutorial.entities.HibernateUtils;

public class QueryObjectDemo {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
	          // Tất cả các lệnh hành động với DB thông qua Hibernate
	          // đều phải nằm trong 1 giao dịch (Transaction)
	          // Bắt đầu giao dịch
	          session.getTransaction().begin();
	 
	          // Tạo một câu lệnh HQL query object.
	          // Tương đương với Native SQL:
	          // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
	 
	          String sql = "Select e from " + Employee.class.getName() + " e "
	                  + " order by e.empName, e.empNo ";
	 
	          // Tạo đối tượng Query.
	          Query<Employee> query = session.createQuery(sql);
	 
	          // Thực hiện truy vấn.
	          List<Employee> employees = query.getResultList();
	 
	          for (Employee emp : employees) {
	              System.out.println("Emp: " + emp.getEmpNo() + " : "
	                      + emp.getEmpName());
	          }
	 
	          // Commit dữ liệu
	          session.getTransaction().commit();
	      } catch (Exception e) {
	          e.printStackTrace();
	          // Rollback trong trường hợp có lỗi xẩy ra.
	          session.getTransaction().rollback();
	      }
	} 
}
