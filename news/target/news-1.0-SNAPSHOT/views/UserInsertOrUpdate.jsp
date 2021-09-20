<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/api/user"/>
<!DOCTYPE html>
<html>
<head>
    <title><dec:title default="Sửa user"/></title>
    <link rel="stylesheet" href="<c:url value='/template/assets/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/assets/font-awesome/4.5.0/css/font-awesome.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/assets/css/ace.min.css' />" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <script src="<c:url value='/template/assets/js/ace-extra.min.js' />"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <%--    <script type='text/javascript' src='<c:url value="/template/assets/js/jquery-2.2.3.min.js" />'></script>--%>
    <%--    <script src="<c:url value='/template/assets/js/jquery.2.1.1.min.js' />"></script>--%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body class="no-skin">
<!-- header -->
<%@ include file="/common/header.jsp" %>
<!-- header -->

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">User</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>
                    <form id="formUpdateOrCreate">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Quyền</label>
                            <div class="col-sm-9">
                                <c:forEach var="item" items="${roles}">
                                    <c:if test="${user.role_id == item.id}">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="role_id" value="${item.id}"
                                                   id="defaultCheck2" checked>
                                            <label class="form-check-label" for="defaultCheck2">
                                                    ${item.name}
                                            </label>
                                        </div>
                                    </c:if>
                                    <c:if test="${user.role_id != item.id}">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="role_id" value="${item.id}" id="defaultCheck3">
                                            <label class="form-check-label" for="defaultCheck3" >
                                                    ${item.name}
                                            </label>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên đăng nhập</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="thumbnail" name="userName"
                                       value="${user.userName}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Password</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="shortDescription" name="password"
                                       value="${user.password}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">email</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${user.email}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số điện thoại</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="phone" name="phone"
                                       value="${user.phone}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">

                            <input type="hidden" class="form-control" id="id" name="id" value="${user.id}"/>

                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty user.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Cập nhật user"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                                <c:if test="${empty user.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Thêm mới user"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#btnAddOrUpdate').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formUpdateOrCreate').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        var id = $('#id').val();
        if (id == '') {
            addNews(data);
        } else {
            updateNews(data);
        }
    });
    function addNews(data) {
        $.ajax({
            url: ('${ApiUrl}'),
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'html',
            success: function () {
                window.location.href = 'http://localhost:8080/form/admin';
            },
            error: function () {
            }
        });
    }
    function updateNews(data) {
        $.ajax({
            url: ('${ApiUrl}'),
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'html',
            success: function () {
                window.location.href = 'http://localhost:8080/form/admin';
            },
            error: function () {
            }
        });
    }
</script>
<!-- footer -->
<%@ include file="/common/footer.jsp" %>
<!-- footer -->

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>


<script src="<c:url value='/template/assets/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery-ui.custom.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.easypiechart.min.js' />"></script>
<%--<script src="<c:url value='/template/assets/js/jquery.sparkline.min.js' />"></script>--%>
<script src="<c:url value='/template/assets/js/jquery.flot.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.flot.pie.min.js' />"></script>
<script src="<c:url value='/template/assets/js/jquery.flot.resize.min.js' />"></script>
<script src="<c:url value='/template/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/assets/js/ace.min.js' />"></script>
<script src="<c:url value='/template/assets/js/bootstrap.min.js'/>"></script>
<%--<script src="<c:url value="/global/admin-global.js"/> "></script>--%>
<!-- page specific plugin scripts -->
<script src="<c:url value='/template/assets/js/jquery-ui.min.js'/>"></script>
</body>
</html>