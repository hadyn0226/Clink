<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <div align="cencter">
      <form action="${ pageContext.request.contextPath }/join" method="post">
        <table>
          <tr>
            <th>성</th>
            <td><input type="text" name="userFirstname" /></td>
          </tr>
          <tr>
            <th>이름</th>
            <td><input type="text" name="userLastname" /></td>
          </tr>
          <tr>
            <th>이메일</th>
            <td><input type="text" name="email" onchange="emailFalse()" /></td>
            <th>
              <button type="button" onclick="checkId(this.form)">
                중복확인
              </button>
            </th>
          </tr>
          <tr id="checkEmail"></tr>
          <tr>
            <th>비밀번호</th>
            <td><input type="password" name="userPassword" /></td>
          </tr>
          <tr>
            <th>비밀번호 확인</th>
            <td><input type="password" name="userRePassword" /></td>
          </tr>
          <tr>
            <th>생년월일</th>
            <td><input type="date" name="userBirth" /></td>
          </tr>
          <tr>
            <td>
              <button type="button" onclick="checkAllInput(this.form)">
                회원가입
              </button>
            </td>
          </tr>
        </table>
      </form>
    </div>
    <script type="text/javascript">
    const checkExist = document.getElementById("checkEmail");
    let emailChecker = false;
    let notChecker = true;
    function checkId(f) {
      if (f.email.value == "") {
        alert("아이디를 입력하십시오!");
        return;
      }
      notChecker = false;
      var url = "${pageContext.request.contextPath}/checkId";
      var param = "id=" + encodeURIComponent(f.email.value);

      sendRequest(url, param, resultFn, "POST");
    }

    function checkAllInput(f) {
      if (f.userFirstname.value == "") {
        alert("성을 입력해 주세요");
        f.userFirstname.focus();
        return;
      } else if (f.userLastname.value == "") {
        alert("이름을 입력해 주세요");
        f.userLastname.focus();
        return;
      } else if (f.email.value == "") {
        alert("이메일을 입력해 주세요");
        f.email.focus();
        return;
      } else if (!emailChecker) {
        alert("이메일 중복확인을 해주세요");
        f.email.focus();
        return;
      } else if (f.userPassword.value == "") {
        alert("비밀번호를 입력해 주세요");
        f.userPassword.focus();
        return;
      } else if (f.userRePassword.value == "") {
        alert("비밀번호 확인을 입력해 주세요");
        f.userRePassword.focus();
        return;
      } else if (f.userPassword.value != f.userRePassword.value) {
        alert("비밀번호를 다시 확인해 주세요");
        f.userRePassword.focus();
        return;
      } else if (f.userBirth.value == "") {
        alert("생년월일을 입력해 주세요");
        f.userBirth.focus();
        return;
      }
      f.submit();
    }

    function resultFn() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        //도착된 데이터를 읽어오기
        var data = xhr.responseText;
        const join = document.getElementById("join");
        const check = document.getElementById("check");
        const id = document.getElementById("uuserId");
        console.log("data : ", data);

        if (data == "1") {
          checkExist.innerHTML = "<td></td><td>사용가능한 이메일입니다.</td>";
          emailChecker = true;
        } else {
          checkExist.innerHTML =
            "<td></td><td>이미 존재하는 이메일입니다.</td>";
          emailChecker = false;
        }
      }
    }

    function emailFalse() {
      emailChecker = false;
      if (!notChecker) {
        checkExist.innerHTML = "<td></td><td>다시 중복확인해 주세요</td>";
      }
    }
  </script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
