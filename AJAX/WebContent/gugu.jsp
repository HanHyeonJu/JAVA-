<%
	int n = Integer.parseInt(request.getParameter("val"));
		
	for(int i = 1; i<=9 ; i++){
	
		String s = String.format("%d X %d = %d <br>", n, i, n*i);
				//out.print(n + "X" + i + "=" + i * n + "<br>");
		out.print(s);
	}
	
%>