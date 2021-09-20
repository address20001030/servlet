<%@ page import="com.nguyenmauhuy.util.SessionUtil" %>
<%@ page import="com.nguyenmauhuy.entity.User" %>
<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="/admin?pageIndex=1&pageSize=10" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Shop-Admin
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<c:url value="/template/assets/images/avatars/user.jpg"/>"
                             alt="Jason's Photo"/>
                        <span class="user-info">
									<small>Welcome,</small>
									<%=((User) com.nguyenmauhuy.util.SessionUtil.get("USER", request)).getUserName()%>
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
                            <form action="/logout?action=logout" method="post">
                                <input type="submit" class="ace-icon fa fa-power-off" value="Logout">
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>