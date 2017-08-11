package com.joonkim.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.joonkim.spring.config.MyBatisUtil;
import com.joonkim.spring.model.Customer;

@Component
public class CustomerDAO {

	public Customer getCustomerByID(int id){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Customer customer = session.selectOne("mapper.CustomerMapper.selectCustomer", id);
		// VillageMapper.xml안 <mapper namespace="">의 값.쿼리ID
		session.commit();
		session.close();
		return customer;
	}
	
	public List<Customer> getAllCustomers(){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Customer> list = session.selectList("mapper.CustomerMapper.selectAll");
		session.commit();
		session.close();
		return list;
	}
	
	public void saveCustomer(Customer customer){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("mapper.CustomerMapper.InsertCustomer", customer);
		session.commit();
		session.close();
	}
}
