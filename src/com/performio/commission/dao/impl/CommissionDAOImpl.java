package com.performio.commission.dao.impl;

import java.io.File;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.performio.commission.dao.CommissionDAO;
import com.performio.commission.entity.CommissionEntity;

public class CommissionDAOImpl implements CommissionDAO 
{
	/**
	 * This Method Calculate Commission based on set parameters actual, Target and MOTC
	 */
	public CommissionEntity calculateCommission(CommissionEntity commissionEntity)  throws Exception
	{
		commissionEntity.validate();
		if(commissionEntity!=null && commissionEntity.getError() == null)
		{
			commissionEntity = updateBaseAndRateValue(commissionEntity);
			if(commissionEntity.getBase()==-1)
			{
				commissionEntity.setError("Achievement ranges are not available. Please contact admin.");
			}
			else
			{
				double commissionRate = commissionEntity.getBase() + ((commissionEntity.getAchevement()-commissionEntity.getFromValue())*commissionEntity.getRate());
				double commission = commissionRate * commissionEntity.getMotc();
				commissionEntity.setCommission(new BigDecimal(commission));
				commissionEntity.setError(null);
			}
		}
		return commissionEntity;
	}
	
	
	/**
	 * This method read data from Achievement Ranges XML and UPdate base and rate fields value in CommissionEntity object
	 * @param commissionEntity
	 * @return
	 * @throws Exception
	 */
	private CommissionEntity updateBaseAndRateValue(CommissionEntity commissionEntity) throws Exception
	{
		File fXmlFile = new File(commissionEntity.getAchievementFilePath());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		if(doc!=null && commissionEntity!=null)
		{
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("range");
			if(nList!=null && nList.getLength()>0)
			{
				for (int i = 0; i < nList.getLength(); i++)
				{
					Node nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
						Element eElement = (Element) nNode;
						double minValue = Double.valueOf(eElement.getElementsByTagName("minValue").item(0).getTextContent());
						double maxValue = Double.valueOf(eElement.getElementsByTagName("maxValue").item(0).getTextContent());
						if(commissionEntity.getAchevement()>=minValue && commissionEntity.getAchevement()<maxValue)
						{
							double base = Double.valueOf(eElement.getElementsByTagName("base").item(0).getTextContent());
							double rate = Double.valueOf(eElement.getElementsByTagName("rate").item(0).getTextContent());
							commissionEntity.setBase(base);
							commissionEntity.setRate(rate);
							commissionEntity.setFromValue(minValue);
						}
					}

				}
			}
		}
		return commissionEntity;
	}
}
