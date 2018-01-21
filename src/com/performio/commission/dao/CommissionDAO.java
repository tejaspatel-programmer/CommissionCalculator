package com.performio.commission.dao;

import com.performio.commission.entity.CommissionEntity;

public interface CommissionDAO 
{
	public CommissionEntity calculateCommission(CommissionEntity commissionEntity)  throws Exception;
}
