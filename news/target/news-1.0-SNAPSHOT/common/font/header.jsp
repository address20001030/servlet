    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!--======== SEARCH-OVERLAY =========-->
     <!-- end columns -->

        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
        <div id="links">
        <ul class="list-unstyled list-inline">
        <li><a href="login.html"><span><i class="fa fa-lock"></i></span>Login</a></li>
        <li><a href="registration.html"><span><i class="fa fa-plus"></i></span>Sign Up</a></li>
        <li>
        <form>
        <ul class="list-inline">
        <li>
        <div class="form-group currency">
        <span><i class="fa fa-angle-down"></i></span>
        <select class="form-control">
        <option value="">$</option>
        <option value="">£</option>
        </select>
        </div><!-- end form-group -->
        </li>

        <li>
        <div class="form-group language">
        <span><i class="fa fa-angle-down"></i></span>
        <select class="form-control">
        <option value="">EN</option>
        <option value="">UR</option>
        <option value="">FR</option>
        <option value="">IT</option>
        </select>
        </div><!-- end form-group -->
        </li>
        </ul>
        </form>
        </li>
        </ul>
        </div><!-- end links -->
        </div><!-- end columns -->
        </div><!-- end row -->
        </div><!-- end container -->
        </div><!-- end top-bar -->

        <nav class="navbar navbar-default main-navbar navbar-custom navbar-colored" id="mynavbar">
        <div class="container" style="width: 1270px">
        <div class="navbar-header">
        <button type="button" class="navbar-toggle" id="menu-button">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        </button>
        <div class="header-search hidden-lg">
        <a href="javascript:void(0)" class="search-button"><span><i class="fa fa-search"></i></span></a>
        </div>
        <a href="/page/home" class="navbar-brand"><span><i class="fa fa-plane"></i>OHANA</span></a>
        </div><!-- end navbar-header -->

        <div class="collapse navbar-collapse" id="myNavbar1">
        <ul class="nav navbar-nav navbar-right navbar-search-link">
        <li class="dropdown active"><a href="/page/home">Trang chủ</a>
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Tour trong nước<span><i class="fa fa-angle-down"></i></span></a>
        <ul class="dropdown-menu " id="trong-nuoc">

        </ul>
        </li>

        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Tours nước ngoài<span><i class="fa fa-angle-down"></i></span></a>
        <ul class="dropdown-menu " id="nuoc-ngoai">

        </ul>
        </li>
        <li ><a href="/page/hotel-all" >Hotel</a></li>
        <li ><a href="/page/blog-all" >Blog</a></li>
        <%--<li ><a href="#" >Dịch vụ du lịch</a></li>--%>
        <%--<li ><a href="#" >Liên hệ</a></li>--%>


        <li></li>
        </ul>
        </div>
        </div>
        </nav>
