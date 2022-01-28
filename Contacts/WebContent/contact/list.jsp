<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/header.jsp" />
	<div class="container">
		<div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">
			연락처 리스트
		</div>
        
		<div class="mb-3">
			<button type="button" class="btn btn-primary btn-add" data-toggle="modal" data-target="#modal-add-update">
				추 가 
			</button>
		</div>
        
        <table class="table table-hover">
        	<thead>
            	<tr>
                	<th>#</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>연락처</th>
                    <th width="5%">&nbsp;</th>
                    <th width="5%">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="contact" items="${contacts}">
            	<tr>
                	<td><c:out value="${contact.id}" /></td>
                    <td><c:out value="${contact.name}" /></td>
                    <td><c:out value="${contact.email}" /></td>
                    <td><c:out value="${contact.phone}" /></td>
                    <td>
                    	<button type="button" class="btn btn-info btn-sm btn-edit" data-id="<c:out value='${contact.id}' />">
                    		수정
                    	</button>
                    </td>
                    <td>
                    	<button type="button" class="btn btn-danger btn-sm btn-delete" data-id="<c:out value='${contact.id}' />" data-toggle="modal" data-target="#modal-delete">
                                                        삭제
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="modal fade" id="modal-add-update" tabindex="-1" aria-labelledby="addUpdateLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 id="title-add-upd" class="modal-title"></h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <form autocomplete="nope">
		            <div class="modal-body">
	                	<div class="form-group">
		                    <label for="name">name</label>
		                    <input type="text" class="form-control" id="name" name="name" required>
		                </div>
		
		                <div class="form-group">
		                    <label for="email">Email</label>
		                    <input type="email" class="form-control" id="email" name="email" required>
		                </div>
		
		                <div class="form-group">
		                    <label for="phone">phone</label>
		                    <input type="text" class="form-control" id="phone" name="phone" required maxlength="15">
		                </div>
		            </div>
		            <div class="modal-footer">
		            	<button type="submit" class="btn btn-success btn-action">저장</button>
		                <button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">취소</button>
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
    
    <div class="modal fade" id="modal-delete" tabindex="-1" aria-labelledby="deleteLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title">delete</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <p>정말로 삭제할까요?</p>
	            </div>
	            <div class="modal-footer">
	                <form id="frm-delete">
	                	<input type="hidden" name="cmd" value="del">
	                	<input type="hidden" name="id" value="">
	                    <button type="submit" class="btn btn-danger btn-action">삭제</button>
	                </form>
	                <button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">취소</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" crossorigin></script>
	<script src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js" crossorigin></script>
	<script>
		$('.nav-link').removeClass('active');
		$('#m-contacts').addClass('active');
		var path = '<%= request.getContextPath() %>';
	</script>
	<script src="assets/js/contact.js"></script>
<jsp:include page="/includes/footer.jsp" />