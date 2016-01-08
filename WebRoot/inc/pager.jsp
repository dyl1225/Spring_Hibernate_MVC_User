<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<pg:pager maxPageItems="1" items="${param.items }" export="curPage=pageNumber" url="${param.url }">
<!--  url要访问的页面地址，，Pager会在给出的URL后面加上"?page.offset="来标识当前偏移量，此时的url为:项目名称/user/users
maxPageItems 每页显示的记录数
items:总记录数
curPage:当前页码
pageNumber:总页码
url:url根地址user/user既为要显示用户列表的页面对应的映射地址
<c:forEach items="${param.params }" var="p">
	<pg:param name="${p }"/>
</c:forEach>
-->
<pg:last>
共${param.items }条记录,共${pageNumber }页,
</pg:last>
当前第${curPage }页
<pg:first>
	<a href="${pageUrl }">首页</a>
</pg:first>
<pg:prev>
	<a href="${pageUrl }">上一页</a>
</pg:prev>

<pg:pages>  
  
   <c:choose>  
  
       <c:when test="${curPage eq pageNumber }">  
  
           <font color="red">[${pageNumber }]</font>  
  
       </c:when>  
  
       <c:otherwise>  
  
          <a href="${pageUrl }">${pageNumber }</a>  
  
         </c:otherwise>  
  
     </c:choose>  
  
       </pg:pages>  
<pg:next>
	<a href="${pageUrl }">下一页</a>
</pg:next>
<pg:last>
	<a href="${pageUrl }">尾页</a>
</pg:last>
</pg:pager>