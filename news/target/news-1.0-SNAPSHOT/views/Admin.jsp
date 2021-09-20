<%@ page import="com.nguyenmauhuy.util.SessionUtil" %>
<%@ page import="com.nguyenmauhuy.entity.User" %>
<%@ page import="com.nguyenmauhuy.service.impl.UserServiceImpl" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Blank Page - Ace Admin</title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value="/template/assets/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/template/assets/font-awesome/4.5.0/css/font-awesome.min.css"/>"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value="/template/assets/css/fonts.googleapis.com.css"/>"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value="/template/assets/css/ace.min.css"/>" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<c:url value="/template/assets/css/ace-part2.min.css"/>" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="<c:url value="/template/assets/css/ace-skins.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/template/assets/css/ace-rtl.min.css"/>"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<c:url value="/template/assets/css/ace-ie.min.css"/>" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="<c:url value="/template/assets/js/ace-extra.min.js"/>"></script>
    <script src="<c:url value='/template/assets/js/jquery-2.1.4.min.js'/>"></script>
    <%--    <script src="<c:url value="/template/assets/js/jquery-1.11.3.min.js"/> "></script>--%>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value='/template/assets/js/paging/jquery.twbsPagination.js' />"></script>
    <%--    <script src="<c:url value="/template/assets/js/admin/user/list.js" />"></script>--%>
    <!-- jQuery UI 1.11.4 -->
    <%--    <script src="<c:url value='/plugins/jquery-ui/jquery-ui.min.js'/>"></script>--%>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="<c:url value="/template/assets/js/html5shiv.min.js"/>"></script>
    <script src="<c:url value="/template/assets/js/respond.min.js"/>"></script>
    <![endif]-->

    <script>
        $(document).ready(function () {
            $.ajax({
                url: "/api/user",
                type: "GET",
                contentType: "application/json",
                success: function (page) {
                    console.log(page.data);
                    var data = page.data;
                    var string1 = " <table id=\"simple-table\" class=\"table  table-bordered table-hover\">"
                        + "<thead>"
                        + "<tr>"
                        + "<th class=\"center\">"
                        + "<label class=\"pos-rel\">"
                        + "<input type=\"checkbox\" class=\"ace\"/>"
                        + "<span class=\"lbl\"></span>"
                        + "</label>"
                        + "</th>"
                        + "<th>UserName</th>"
                        + "<th>Phone</th>"
                        + "<th>email</th>"
                        + "<th>Action</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>";

                    var string2 = "</tbody>"
                        + "</table>"
                        + "<ul class=\"pagination\" id=\"pagination\">" + "</ul>"
                        + "<input type=\"hidden\" value=\"1\" id=\"page\" name=\"pageIndex\">"
                        + "<input type=\"hidden\" value=\"10\" id=\"size\" name=\"pageSize\">";

                    var string3 = "";
                    $.each(data, (index, item) => {
                        var href = "/form/user?action=update&id=" + item.id;
                        string3 = string3 + "<tr>"
                            + "<td>" + "</td>"
                            + "<td>" + item.userName + "</td>"
                            + "<td>" + item.phone + "</td>"
                            + "<td>" + item.email + "</td>"
                            + "<th align=\"center\">"
                            + "<a class=\"btn btn-sm btn-primary btn-edit\" data-toggle=\"tooltip\" title=\"update user\" href=" + href + ">"
                            + "<i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i>"
                            + "<a class=\"btn btn-sm btn-primary btn-edit\" data-toggle=\"tooltip\" title=\"insert user\" href=\"/form/user?action=insert\">"
                            + "<i class=\"fa fa-plus-circle\" aria-hidden=\"true\"></i>"
                            + "</a>"
                            + "<a class=\"btn btn-sm btn-primary btn-edit itemDelete\" id=\"dt\" data-toggle=\"tooltip\" title=\"delete user\"  value=\""+item.id+"\">"
                            + "<i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i>"
                            + "</a>"
                            + "</th>"
                            + "</tr>"
                    });
                    var table = string1 + string3 + string2;
                    var totalPage = page.totalPage;
                    var currentPage = page.index;
                    var limit = page.size;

                    // $(function () {
                    //     window.pagObj = $('#pagination').twbsPagination({
                    //         totalPages: totalPage,
                    //         visiblePages: 10,
                    //         startPage: currentPage,
                    //         limit: limit,
                    //         onPageClick: function (event, page) {
                    //             if (currentPage != page) {
                    //                 $('#size').val(limit);
                    //                 $("#page").val(page);
                    //             }
                    //         }
                    //     })
                    // });
                    $(function (){
                        $('#pagination').twbsPagination({
                            totalPages: totalPage,
                            visiblePages: 7,
                            onPageClick: function (event, page) {
                                $('#page-content').text('Page ' + page);
                            }
                        });
                    });
                    $('#userTable').html(table);
                    $(".itemDelete").click(function (){
                        console.log("alo alo");
                        var id = $(".itemDelete").val();
                        $.ajax({
                            url: 'http://localhost:8037/api/user'+id,
                            type: "DELETE",
                            data: {
                                id : id,
                            },
                            success: function (data) {
                                alert(data);

                            },
                            error: function (error) {
                                alert(error);
                            }
                        })
                    });
                },
                error: function (e) {
                    console.log("lỗi");
                }
            });

        });


    </script>

</head>

<body class="no-skin">
<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="index.html" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Ace Admin
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<c:url value="/template/assets/images/avatars/user.jpg"/> "
                             alt="Jason's Photo"/>
                        <span class="user-info">
									<small>Welcome,</small>
									<%=((User) SessionUtil.get("USER", request)).getUserName()%>
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <button id="btnLogout">
                                <i  class="ace-icon fa fa-power-off" ></i>
                                Logout
                            </button>

                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.loadState('main-container')
        } catch (e) {
        }
    </script>

    <div id="sidebar" class="sidebar           responsive                    ace-save-state">
        <script type="text/javascript">
            try {
                ace.settings.loadState('sidebar')
            } catch (e) {
            }
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->

        <ul class="nav nav-list">
            <li class="">
                <a href="index.html">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> Dashboard </span>
                </a>

                <b class="arrow"></b>
            </li>

        </ul><!-- /.nav-list -->

        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
               data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
    </div>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>

                    <li>
                        <a href="#">Other Pages</a>
                    </li>
                    <li class="active">User Page</li>
                </ul><!-- /.breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input"
                                           id="nav-search-input" autocomplete="off"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- /.nav-search -->
            </div>

            <div class="page-content">
                <div class="ace-settings-container" id="ace-settings-container">
                    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                        <i class="ace-icon fa fa-cog bigger-130"></i>
                    </div>

                    <div class="ace-settings-box clearfix" id="ace-settings-box">
                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <div class="pull-left">
                                    <select id="skin-colorpicker" class="hide">
                                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                    </select>
                                </div>
                                <span>&nbsp; Choose Skin</span>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                       id="ace-settings-navbar" autocomplete="off"/>
                                <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                       id="ace-settings-sidebar" autocomplete="off"/>
                                <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                       id="ace-settings-breadcrumbs" autocomplete="off"/>
                                <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"
                                       autocomplete="off"/>
                                <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2 ace-save-state"
                                       id="ace-settings-add-container" autocomplete="off"/>
                                <label class="lbl" for="ace-settings-add-container">
                                    Inside
                                    <b>.container</b>
                                </label>
                            </div>
                        </div><!-- /.pull-left -->

                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"
                                       autocomplete="off"/>
                                <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"
                                       autocomplete="off"/>
                                <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"
                                       autocomplete="off"/>
                                <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                            </div>
                        </div><!-- /.pull-left -->
                    </div><!-- /.ace-settings-box -->
                </div><!-- /.ace-settings-container -->

                <div class="row">
                    <div class="col-xs-12" >
                    <%-- SEARCH                       --%>
                        <span class="block input-icon input-icon-right">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="Username"/>
                            <i class="ace-icon fa fa-user"></i>
                        </span>
                        <span class="block input-icon input-icon-right">
                            <input type="text" class="form-control" id="fullName" name="fullName" placeholder="fullName"/>
                            <i class="ace-icon fa fa-user"></i>
                        </span>
                        <span class="block input-icon input-icon-right">
                            <input type="text" class="form-control" id="phone" name="phone" placeholder="phone"/>
                            <i class="ace-icon fa fa-user"></i>
                        </span>
                        <button id="search" type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                            <i class="ace-icon fa fa-search"></i>
                            <span class="bigger-110">search</span>
                        </button>
                    </div>
                    <div class="col-xs-12" id="userTable">

                        <!-- PAGE CONTENT BEGINS -->

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

                &nbsp; &nbsp;
                <span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='<c:url value="/template/assets/js/jquery.mobile.custom.min.js"/>'>" + "<" + "/script>");
</script>
<script src="<c:url value="/template/assets/js/bootstrap.min.js"/>"></script>

<script src="<c:url value="/template/assets/js/ace-elements.min.js"/>"></script>
<script src="<c:url value="/template/assets/js/ace.min.js"/>"></script>


<script>
    $('#btnLogout').click(function (e) {
        e.preventDefault();
        $.ajax({
            url: "/api/logout",
            type: "POST",
            success: function () {
                window.location.href = "http://localhost:8080/form/login";
            }
        });
    });


    $("#search").click(function (e){
        e.preventDefault();
        var userName = $('#userName').val() === undefined ? "" : userName;
        var phone = $('#phone').val() === undefined ? "" : userName;
        $.ajax({
            url: "/api/user?userName"+userName+"&phone="+phone,
            type: "GET",
            success: function (page) {
                console.log(page.data);
                var data = page.data;
                var string1 = " <table id=\"simple-table\" class=\"table  table-bordered table-hover\">"
                    + "<thead>"
                    + "<tr>"
                    + "<th class=\"center\">"
                    + "<label class=\"pos-rel\">"
                    + "<input type=\"checkbox\" class=\"ace\"/>"
                    + "<span class=\"lbl\"></span>"
                    + "</label>"
                    + "</th>"
                    + "<th>UserName</th>"
                    + "<th>Phone</th>"
                    + "<th>email</th>"
                    + "<th>Action</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>";

                var string2 = "</tbody>"
                    + "</table>"
                    + "<ul class=\"pagination\" id=\"pagination\">" + "</ul>"
                    + "<input type=\"hidden\" value=\"1\" id=\"page\" name=\"pageIndex\">"
                    + "<input type=\"hidden\" value=\"10\" id=\"size\" name=\"pageSize\">";

                var string3 = "";
                $.each(data, (index, item) => {
                    var href = "/form/user?action=update&id=" + item.id;
                    string3 = string3 + "<tr>"
                        + "<td>" + "</td>"
                        + "<td>" + item.userName + "</td>"
                        + "<td>" + item.phone + "</td>"
                        + "<td>" + item.email + "</td>"
                        + "<th align=\"center\">"
                        + "<a class=\"btn btn-sm btn-primary btn-edit\" data-toggle=\"tooltip\" title=\"update user\" href=" + href + ">"
                        + "<i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i>"
                        + "<a class=\"btn btn-sm btn-primary btn-edit\" data-toggle=\"tooltip\" title=\"insert user\" href=\"/form/user?action=insert\">"
                        + "<i class=\"fa fa-plus-circle\" aria-hidden=\"true\"></i>"
                        + "</a>"
                        + "<a class=\"btn btn-sm btn-primary btn-edit itemDelete\" data-toggle=\"tooltip\" title=\"delete user\" value=\"item.id\" >"
                        + "<i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i>"
                        + "</a>"
                        + "</th>"
                        + "</tr>"
                });
                var table = string1 + string3 + string2;
                var totalPage = page.totalPage;
                var currentPage = page.index;
                var limit = page.size;
                $(function () {
                    window.pagObj = $('#pagination').twbsPagination({
                        totalPages: totalPage,
                        visiblePages: 10,
                        startPage: currentPage,
                        limit: limit,
                        onPageClick: function (event, page) {
                            currentPage = page;
                            setTimeout(event,200);
                            if (currentPage != page) {
                                $('#size').val(limit);
                                $("#page").val(page);
                            }
                        }
                    })
                });
                $('#userTable').html(table);

            },
            error: function (e) {
                console.log("lỗi");
            }
        })
    });
</script>
</body>
</html>
