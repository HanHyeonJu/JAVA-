<%
	int n = Integer.parseInt(request.getParameter("val"));
	int total = 0;
	for(int i = 1; i<=n ; i++){
		total += i;
	}
	out.print(total);
%>