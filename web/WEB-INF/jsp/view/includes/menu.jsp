<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search hidden-sm hidden-md hidden-lg">
                        <!-- input-group -->
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search..."> <span class="input-group-btn">
            <button class="btn btn-default" type="button"> <i class="fa fa-search"></i> </button>
            </span> </div>
                        <!-- /input-group -->
                    </li>
                    <li class="user-pro">
                        <a href="#" class="waves-effect"><img src="plugins/images/users/d1.jpg" alt="user-img" class="img-circle"> <span class="hide-menu"><shiro:principal/><span class="fa arrow"></span></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a href="javascript:void(0)"><i class="ti-user"></i> My Profile</a></li>
                            <li><a href="javascript:void(0)"><i class="ti-email"></i> Inbox</a></li>
                            <li><a href="javascript:void(0)"><i class="ti-settings"></i> Account Setting</a></li>
                            <li><a href="javascript:void(0)"><i class="fa fa-power-off"></i> Logout</a></li>
                        </ul>
                    </li>
                    <li class="nav-small-cap m-t-10"></li>
                    <li> <a href="<c:url value="/dashboard" />" class="waves-effect active"><i class="linea-icon linea-basic fa-fw" data-icon="v"></i> <span class="hide-menu"> Dashboard </span></a></li>
                    <shiro:hasAnyRoles name="STAF,ADMIN">
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="ti-calendar p-r-10"></i> <span class="hide-menu"> Terminet <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="<c:url value="/termini">
                                        <c:param name="action" value="listo"></c:param>
                                          </c:url>">Shiko Terminet</a></li>
                            <li> <a href="<c:url value="/termini">
                                        <c:param name="action" value="shto"></c:param>
                                        </c:url>">Shto Terminin</a></li>
                        </ul>
                    </li>
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="icon-people p-r-10"></i> <span class="hide-menu"> Pacientët <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="<c:url value="/pacienti">
                                        <c:param name="action" value="listo"></c:param>
                                          </c:url>">Shiko Pacientët</a></li>
                            <li> <a href="<c:url value="/pacienti">
                                        <c:param name="action" value="shto"></c:param>
                                        </c:url>">Shto Pacientin</a></li>
                        </ul>
                    </li>
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="icon-people p-r-10"></i> <span class="hide-menu"> Komunikimi <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="<c:url value="/chat">
                                          </c:url>">Chat</a></li>
                        </ul>
                    </li>
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="icon-chart p-r-10"></i> <span class="hide-menu"> Raportet <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="TerminetSotmetReportServlet">Raporti i Termineve te Sotme</a></li>
                        </ul>
                    </li>
                    </shiro:hasAnyRoles>
                    <shiro:hasRole name="ADMIN">
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-university p-r-10"></i> <span class="hide-menu">Kualifikimet <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="<c:url value="/kualifikimi">
                                <c:param name="action" value="listo"></c:param>
                            </c:url>">Shiko Kualifikimet</a> </li>
                            <li> <a href="<c:url value="/kualifikimi">
                                <c:param name="action" value="shto"></c:param>
                            </c:url>">Shto Kualifikimin</a> </li>
                        </ul>
                    </li>
                    </shiro:hasRole>
                    <shiro:hasRole name="ADMIN">
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-user-md p-r-10"></i> <span class="hide-menu">Stafi <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="<c:url value="/stafi">
                                <c:param name="action" value="listo"></c:param>
                            </c:url>">Shiko Stafin</a> </li>
                            <li> <a href="<c:url value="/stafi">
                                <c:param name="action" value="shto"></c:param>
                            </c:url>">Shto Antarin</a> </li>
                            <li> <a href="<c:url value="/rolistafit">
                                <c:param name="action" value="shto"></c:param>
                            </c:url>">Shto Rolin (Stafi) </a> </li>
                            <li> <a href="<c:url value="/rolistafit">
                                <c:param name="action" value="listo"></c:param>
                            </c:url>">Shiko Rolet (Stafi)</a> </li>
                        </ul>
                    </li>
                    </shiro:hasRole>
                    <shiro:hasRole name="ADMIN">
                    <li> <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-hospital-o p-r-10"></i> <span class="hide-menu"> Klinika <span class="fa arrow"></span></span></a>
                        <ul class="nav nav-second-level">
                            <li> <a href="<c:url value="/klinika">
                                <c:param name="action" value="listo"></c:param>
                            </c:url>">Shiko Klinikat</a> </li>
                            <li> <a href="<c:url value="/klinika">
                                <c:param name="action" value="shto"></c:param>
                            </c:url>">Shto Kliniken</a> </li>
                        </ul>
                    </li>
                    </shiro:hasRole>
                    <li class="nav-small-cap"></li>
                    <li><a href="<c:url value="/logout">
                    </c:url>" class="waves-effect"><i class="icon-logout fa-fw"></i> <span class="hide-menu">Log out</span></a></li>
                </ul>
            </div>
        </div>
        <!-- Left navbar-header end -->