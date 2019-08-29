package com.thienhlt.HibernateTutorial.query;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thienhlt.HibernateTutorial.beans.ShortEmpInfo;
import com.thienhlt.HibernateTutorial.entities.Employee;
import com.thienhlt.HibernateTutorial.entities.HibernateUtils;

public class ShortEmpInfoQueryDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Sử dụng cấu tử của Class ShortEmpInfo
			String sql = "Select new " + ShortEmpInfo.class.getName() + " (e.empId, e.empNo, e.empName)"
					+ " from " + Employee.class.getName() + " e";

			Query<ShortEmpInfo> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng ShortEmpInfo
			List<ShortEmpInfo> datas = query.getResultList();

			for (ShortEmpInfo emp : datas) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName() );
				
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
