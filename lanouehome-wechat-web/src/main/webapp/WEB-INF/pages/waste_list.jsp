<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="waste-list">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a href="#"
					class="link back<c:if test="${wasteList[0].level==2}"> back_index</c:if>">
					<i class="icon icon-back"></i> <span class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">${parentWaste.name}</div>
		</div>
	</div>
	<div class="page-content">
		<div class="row no-gap" style="text-align: center">
			<c:forEach var="waste" items="${wasteList}"
				varStatus="wasteListVarStatus">
				<div class="col-25 waste-item2">
					<c:choose>
						<c:when test="${waste.isParent}">
							<a href="/waste/find_child/list/${waste.wasteId}/">
						</c:when>
						<c:otherwise>
							<a href="/waste/details/${waste.wasteId}/">
						</c:otherwise>
					</c:choose>
					<img class="waste-image2" src="${waste.pictureUrl}">
					<div class="waste-label">${waste.name}</div>
					</a>
				</div>
				<c:if test="${wasteListVarStatus.last}">
					<c:forEach var="i" begin="1"
						end="${3-wasteListVarStatus.count % 3}">
						<div class="col-25 waste-item2" style="opacity:0"></div>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>