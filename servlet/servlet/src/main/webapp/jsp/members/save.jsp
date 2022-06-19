<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="SpringMVC.servlet.domain.member.Member" %> <!--java 파일 밑부터 읽움-->
<%@ page import="SpringMVC.servlet.domain.member.MemberRepository" %>
<%//이표시가 있는 부분부터는 java 코드 파일이라고 생각하면 됨, 이 표시가 없는 부분이 http response에 담기게 됨
    //request, response 사용가능 ( 결국 jsp도 servlet으로 자동으로 변환되어서 사용되기 때문)
    MemberRepository memberRepository=MemberRepository.getInstance();

    String username=request.getParameter("username");
    int age=Integer.parseInt(request.getParameter("age"));

    Member member=new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li><!-- =이 붙으면 는 java부분 출력-->
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>