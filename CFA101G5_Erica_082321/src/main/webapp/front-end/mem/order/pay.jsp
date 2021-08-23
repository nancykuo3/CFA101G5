<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<form post="method" action="<%=request.getContextPath()%>/order/memOrd.do">
信用卡卡號:
<input type="text"><br>
訂單編號: ${ordId}<br>
付款金額: ${amount}<br>
<input type="hidden" name="requestURL" value="${requestURL}">
<input type="hidden" name="whichPage" value="${whichPage}">
<input type="hidden" name="ordId" value="${ordId}">
<button type="submit" name="action" value="pay_finish">付款</button>

</form>


</head>
<body>

</body>
</html>