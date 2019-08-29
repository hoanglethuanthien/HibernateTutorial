package com.thienhlt.HibernateTutorial.query;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thienhlt.HibernateTutorial.entities.Employee;
import com.thienhlt.HibernateTutorial.entities.HibernateUtils;

public class QuerySomeColumnDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Query một vài cột.
			String sql = "Select e.empId, e.empNo, e.empName from " + Employee.class.getName() + " e";

			Query<Object[]> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng Object[]
			List<Object[]> datas = query.getResultList();

			for (Object[] emp : datas) {
				System.out.println("EmpId: " + emp[0]);
				System.out.println(" Emp No: " + emp[1]);
				System.out.println(" Emp Name: " + emp[2]);
			}
			// Comit dữ liệu
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollaback trong trường hợp có lỗi xảy ra.
			session.getTransaction().rollback();
		}

	}
}
