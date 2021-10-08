<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>홈</h1>

	<sec:authorize access="isAnonymous()">
		<p>
			<a href="<c:url value="/auth/loginForm.do" />">로그인</a>
		</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="submit" value="로그아웃" />
		</form:form>
	</sec:authorize>

	<h3>
		<a href="<c:url value="/auth/registerForm.do" />">회원 가입</a> <a
			href="<c:url value="/egovSampleList.do" />">리스트</a> <a
			href="<c:url value="/admin/admin.do" />">관리자 홈</a>
	</h3>

</body>
</html>