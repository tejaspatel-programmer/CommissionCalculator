package com.performio.commission.formBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CommissionFormBean extends org.apache.struts.action.ActionForm
{


	private static final long serialVersionUID = 342476697563114620L;
	private double actual;
	private double target;
	private double motc;
	private String commission;

	public CommissionFormBean()
	{
		super();
	}
	 /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
    {
    	ActionErrors errors = new ActionErrors();
    	if (this.target <=0) 
    	{
            errors.add("target", new ActionMessage("error.target.required"));
        }
    	if (this.actual <0) 
    	{
            errors.add("actual", new ActionMessage("error.actual.required"));
        }
    	if (this.motc <=0 || this.motc>10000) 
    	{
            errors.add("motc", new ActionMessage("error.motc.required"));
        }
    	return errors;
    }
	public double getActual() {
		return actual;
	}
	public void setActual(double actual) {
		this.actual = actual;
	}
	public double getTarget() {
		return target;
	}
	public void setTarget(double target) {
		this.target = target;
	}
	public double getMotc() {
		return motc;
	}
	public void setMotc(double motc) {
		this.motc = motc;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
}
