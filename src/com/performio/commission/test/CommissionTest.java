package com.performio.commission.test;

import org.junit.Assert;
import org.junit.Test;

import com.performio.commission.dao.CommissionDAO;
import com.performio.commission.dao.impl.CommissionDAOImpl;
import com.performio.commission.entity.CommissionEntity;

public class CommissionTest 
{

	CommissionEntity commissionEntity=null;
	CommissionDAO commissionDAO = null;
	
	public CommissionTest()
	{
		commissionDAO = new CommissionDAOImpl();
	}
	
	@Test
	public void happyTest1() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(210);
		commissionEntity.setTarget(100);
		commissionEntity.setMotc(5000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		double commission =12750;
		Assert.assertEquals(commission,commissionEntity.getCommission().doubleValue(),0);
	}
	
	@Test
	public void happyTest2() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(100);
		commissionEntity.setTarget(100);
		commissionEntity.setMotc(5000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		double commission =5000;
		Assert.assertEquals(commission,commissionEntity.getCommission().doubleValue(),0);
	}
	@Test
	public void happyTest3() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(200);
		commissionEntity.setTarget(100);
		commissionEntity.setMotc(5000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		double commission =12500;
		Assert.assertEquals(commission,commissionEntity.getCommission().doubleValue(),0);
	}
	
	@Test
	public void errorTest1() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(0);
		commissionEntity.setTarget(0);
		commissionEntity.setMotc(5000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Assert.assertEquals("Target field value must be greater then zero.",commissionEntity.getError());
	}
	
	@Test
	public void errorTest2() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(-2);
		commissionEntity.setTarget(100);
		commissionEntity.setMotc(5000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Assert.assertEquals("Actual field value must be greater or equal to zero.",commissionEntity.getError());
	}
	
	@Test
	public void errorTest3() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(10000);
		commissionEntity.setTarget(100);
		commissionEntity.setMotc(5000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Assert.assertEquals("Achievement cannot be greater or equal to 99.99",commissionEntity.getError());
	}
	
	@Test
	public void errorTest4() 
	{
		commissionEntity = new CommissionEntity();
		commissionEntity.setActual(50);
		commissionEntity.setTarget(100);
		commissionEntity.setMotc(100000);
		commissionEntity.setAchievementFilePath("./WebContent/WEB-INF/achievementRange.xml");
		try 
		{
			commissionEntity = commissionDAO.calculateCommission(commissionEntity);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Assert.assertEquals("MOTC field value must be greater than 0 and 1ess than or equal to 10,000",commissionEntity.getError());
	}

}
