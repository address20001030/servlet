<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4" style="height: 100%">
    <!-- Brand Logo -->
    <a href="/admin" class="brand-link">
        <%--<img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">--%>
        <span class="brand-text font-weight-light"> OHANA TRAVEL</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <%--<img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">--%>
            </div>
            <div class="info">
                <a href="#" class="d-block">ADMIN</a>
            </div>
        </div>

        <!-- SidebarSearch Form -->
        <%--<div class="form-inline">--%>
            <%--<div class="input-group" data-widget="sidebar-search">--%>
                <%--<input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">--%>
                <%--<div class="input-group-append">--%>
                    <%--<button class="btn btn-sidebar">--%>
                        <%--<i class="fas fa-search fa-fw"></i>--%>
                    <%--</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item menu-open">
                    <a href="/admin/user-list" class="nav-link active">
                        <i class="fas fa-user"></i>
                        <p>
                            Danh sách user
                        </p>
                    </a>
                    <a href="/admin/blog-list" class="nav-link active">
                        <i class="fas fa-blog"></i>
                        <p>
                            Danh sách blog
                        </p>
                    </a>
                    <a href="/admin/tour-list" class="nav-link active">
                        <i class="fab fa-elementor"></i>
                        <p>
                             Danh sách tour
                        </p>
                    </a>
                    <a href="/admin/hotel-list" class="nav-link active">
                        <i class="fas fa-hotel"></i>
                        <p>
                            Danh sách khách sạn
                        </p>
                    </a>
                    <a href="/admin/customer-list-tour" class="nav-link active">
                        <i class="fas fa-users"></i>
                        <p>
                            Khách hàng book tour
                        </p>
                    </a>
                    <a href="/admin/customer-list-hotel" class="nav-link active">
                        <i class="fas fa-users"></i>
                        <p>
                            Khách hàng book hotel
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>

