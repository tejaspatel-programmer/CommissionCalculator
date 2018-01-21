<%-- 
    Document   : commisionCalc.jsp
    Author     : Tejas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML>

<html>
    <head>
     <script type="text/javascript">
      function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode;
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
      </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Commission Calculator</title>
        <link rel="stylesheet" type="text/css" href="./css/commission.css"/>
    </head>
    <body>
        <html:form action="/commission" >
        <table>
            <tr>
                <th colspan="3"><h1>Commission Calculator</h1></th>
            </tr>
            <tr>
                <td width="45%">Actual <strong style='color: red;'>*</strong> <br/><small>(The number of items a sales person sold this month) :</small> </td>
                <td width="20%"><html:text name="commissionFormBean" property="actual" maxlength="10" onkeypress='isNumberKey(event)' title="The number of items a sales person sold this month"/></td>
                <td width="35%"><strong style='color: red;'><html:errors property="actual" /></strong></td>
            </tr>
            <tr>
                <td width="45%">Target <strong style='color: red;'>*</strong> <br/><small>(The number of items the company wants the person to sell to get the agreed MOTC)" :</small></td>
                <td width="20%"><html:text name="commissionFormBean" property="target" maxlength="10" title = "The number of items the company wants the person to sell to get the agreed MOTC"/></td>
                <td width="35%"><strong style='color: red;'><html:errors property="target" /></strong></td>
            </tr>
             <tr>
                <td width="45%">MOTC (AUD) $: <strong style='color: red;'>*</strong> <br/><small>(Monthly On-Target Commission)" :</small></td>
                <td width="20%"><html:text name="commissionFormBean" property="motc" title = "Monthly On-Target Commission" maxlength="7"/></td>
                <td width="35%"><strong style='color: red;'><html:errors property="motc" /></strong></td>
            </tr>
            <tr>
                <td width="45%"><strong>Commission (AUD): $</strong></td>
                <td width="20%"><html:text name="commissionFormBean" property="commission" disabled="true"/></td>
                 <td width="35%"><strong style='color: red;'><%=request.getAttribute("calculationError")==null?"":request.getAttribute("calculationError")%></strong></td>
            </tr>
            <tr>
                <td colspan="3" align="center"><html:submit styleClass="commissionButton" value="Calculate Commission" onclick = "return confirm('Are you sure,do you want proceed?');"/></td>
            </tr>
        </table>
        <html:messages id="result"/>
        </html:form>
    </body>
</html>
