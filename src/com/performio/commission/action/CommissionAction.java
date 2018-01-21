package com.performio.commission.action;

import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.performio.commission.dao.CommissionDAO;
import com.performio.commission.dao.impl.CommissionDAOImpl;
import com.performio.commission.entity.CommissionEntity;
import com.performio.commission.formBean.CommissionFormBean;

public class CommissionAction extends org.apache.struts.action.Action 
{

	private final static String SUCCESS = "success";
	private CommissionEntity commissionEntity=null;
	private CommissionDAO commissionDAO =null;
	
	public CommissionAction()
	{
		commissionDAO = new CommissionDAOImpl();
	}
	/**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
    {    	
    	if(form!=null)
    	{
    		CommissionFormBean commissionFormBean = (CommissionFormBean) form;
    		commissionEntity = new CommissionEntity();
    		commissionEntity.setActual(commissionFormBean.getActual());
    		commissionEntity.setTarget(commissionFormBean.getTarget());
    		commissionEntity.setMotc(commissionFormBean.getMotc());
    		String achievementFilePath = getServlet().getServletContext().getRealPath("/WEB-INF/achievementRange.xml");
    		commissionEntity.setAchievementFilePath(achievementFilePath);
    		//Calculate Commission
    		commissionEntity = commissionDAO.calculateCommission(commissionEntity);
    		// 2 decimal point value
    		if(commissionEntity.getError()!=null)
    		{
    			request.setAttribute("calculationError",commissionEntity.getError());
    		}
    		else
    		{
    			commissionFormBean.setCommission(String.valueOf(commissionEntity.getCommission().setScale(2, RoundingMode.HALF_UP).doubleValue()));
    			request.setAttribute("commissionFormBean", commissionFormBean);
    		}
    	}
        return mapping.findForward(SUCCESS);
    }

}
