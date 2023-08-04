<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div align="center">
    <form action="${ pageContext.request.contextPath }/login/" method="post" onsubmit="return check(this)">
        <table>
            <tr>
                <th>ID</th>
                <td><input type="text" name="email" value="${id }"></td>
            </tr>
            <tr>
                <th>PW</th>
                <td><input type="password" name="userPassword"></td>
            </tr>
            <tr>
                <th colspan="2" align="right">
                    <span class="ckid">
                        <c:choose>
                            <c:when test="${check }">
                                <input type="checkbox" name="ckid" value="true" checked="checked">
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="ckid" value="true">
                            </c:otherwise>
                        </c:choose>
                        <font class="ckid_text">아이디 기억하기</font>
                    </span>
                    <input type="submit" value="Login">
                </th>
            </tr>
        </table>
    </form>
</div>
<a href="${ pageContext.request.contextPath }/login/findform?mode=id">ID찾기</a> | 
<a href="${ pageContext.request.contextPath }/login/findform?mode=pw">PW재설정</a> 
<script type="text/javascript">
	function check(f) {
		if(f.id.value == ""){
			alert("ID를 입력하십시오!");
			f.id.focus();
			return false;
		}else if(f.password.value == ""){
			alert("PW를 입력하십시오!");
			f.password.focus();
			return false;
		}
		
		return true;
	}
</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>