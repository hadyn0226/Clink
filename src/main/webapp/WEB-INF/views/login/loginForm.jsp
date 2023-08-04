<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<div align="center">
        <table name="table">
            <tr>
                <th>ID</th>
                <td><input type="text" name="email" id="email" value="${id }"></td>
            </tr>
            <tr>
                <th>PW</th>
                <td><input type="password" id="userPassword" name="userPassword"></td>
            </tr>
            <tr>
                <th colspan="2" align="right">
                    <span class="ckid">
                        <c:choose>
                            <c:when test="${check }">
                                <input type="checkbox" name="ckid" id="ckid" value="true" checked="checked">
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="ckid" id="ckid" value="true">
                            </c:otherwise>
                        </c:choose>
                        <font class="ckid_text">아이디 기억하기</font>
                    </span>
                    <input type="button" value="Login" onclick="checkId()">
                </th>
            </tr>
        </table>
</div>
<a href="${ pageContext.request.contextPath }/login/findform?mode=id">ID찾기</a> | 
<a href="${ pageContext.request.contextPath }/login/findform?mode=pw">PW재설정</a> 
<script type="text/javascript">
    const loginID = document.getElementById("email");
    const loginPW = document.getElementById("userPassword");
    const loginCK = document.getElementById("ckid");
    function checkId() {
      if (loginID.value == "") {
        alert("아이디를 입력하십시오!");
        return;
      } else if(loginPW.value == ""){
        alert("비밀번호를 입력하십시오!");
        return;
      }
      var url = "${pageContext.request.contextPath}/login";
      var param = "email=" + encodeURIComponent(loginID.value) +"&&userPassword=" + encodeURIComponent(loginPW.value) + "&&ckid=" + encodeURIComponent(loginCK.value);

      sendRequest(url, param, resultFn, "POST");
    }

    function resultFn() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        //도착된 데이터를 읽어오기
        var data = xhr.responseText;
        if (data == "-1") {
        	alert("존재하지 않는 이메일 입니다")
        } else if(data == "0"){
        	alert("비밀번호가 틀리셨습니다.")
        }else{
        	location.href="${ pageContext.request.contextPath }"
        }
      }
    }

</script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>