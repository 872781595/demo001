<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
  
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p>
							<security:authentication property="principal.username"/>
						</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                    </div>
                </div>
                <!-- search form -->
                <!--<form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="搜索...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>-->
                <!-- /.search form -->


                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li class="header">菜单</li>

                    <li id="admin-index"><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> <span>首页</span></a></li>

                    <!-- 菜单 -->



                    <li class="treeview">
                        <a href="#">
                    <i class="fa fa-folder"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                        <ul class="treeview-menu">

                            <li id="admin-login">
                                <a href="${pageContext.request.contextPath}/sysUser/findAllUser">
                            <i class="fa fa-circle-o"></i> 用户管理
                        </a>
                            </li>

                            <li id="admin-register">
                                <a href="${pageContext.request.contextPath}/role/findAllRole">
                            <i class="fa fa-circle-o"></i> 角色管理
                        </a>
                            </li>

                            <li id="admin-404">
                                <a href="${pageContext.request.contextPath}/permission/findAllPermission">
                            <i class="fa fa-circle-o"></i> 权限管理
                        </a>
                            </li>

                           
                        </ul>
                    </li>



                    <li class="treeview">
                        <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>数据管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                        <ul class="treeview-menu">

                            <li id="charts-chartjs">
                                <a href="${pageContext.request.contextPath }/order/findAllOrder">
                            <i class="fa fa-circle-o"></i> 订单列表
                        </a>
                            </li>

                            <li id="charts-morris">
                                <a href="${pageContext.request.contextPath }/product/findAllProduct">
                            <i class="fa fa-circle-o"></i> 产品列表
                        </a>
                            </li>

                        </ul>
                    </li>




                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
       