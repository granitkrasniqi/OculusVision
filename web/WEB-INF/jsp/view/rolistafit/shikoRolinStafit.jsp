<%-- 
    Document   : dashboard2
    Created on : Mar 22, 2018, 8:22:37 PM
    Author     : Granit Krasniqi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
        <title>OculusVision - Shiko Rolet e stafit</title>
        <!-- Bootstrap Core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="plugins/bower_components/bootstrap-extension/css/bootstrap-extension.css" rel="stylesheet">
        <!-- Menu CSS -->
        <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
        <!-- morris CSS -->
        <link href="plugins/bower_components/morrisjs/morris.css" rel="stylesheet">
        <!-- animation CSS -->
        <link href="css/animate.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/style.min.css" rel="stylesheet">

        <!-- color CSS -->
        <link href="css/colors/megna.css" id="theme" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>

    <body>
        <!-- Preloader -->
        <div class="preloader">
            <div class="cssload-speeding-wheel"></div>
        </div>
        <div id="wrapper">
            <!-- Top Navigation -->
            <nav class="navbar navbar-default navbar-static-top m-b-0">
                <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
                    <div class="top-left-part"><a class="logo" href="index.html"><b><img src="plugins/images/eliteadmin-logo.png" alt="home" /></b><span class="hidden-xs"><strong>Oculus</strong>Vision</span></a></div>
                    <ul class="nav navbar-top-links navbar-left hidden-xs">
                        <li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
                        <li>
                            <form role="search" class="app-search hidden-xs">
                                <input type="text" placeholder="Search..." class="form-control"> <a href=""><i class="fa fa-search"></i></a> </form>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-header -->
                <!-- /.navbar-top-links -->
                <!-- /.navbar-static-side -->
            </nav>
            <!-- End Top Navigation -->
            <!-- Left navbar-header -->
            <c:import url="../includes/menu.jsp" />
            <!-- Left navbar-header end -->
            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row bg-title">
                        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                            <h4 class="page-title">Shiko rolet e stafit</h4> </div>
                        <ol class="breadcrumb">
                            <li><a href="dashboard">Klinika</a></li>
                            <li class="active">Shiko rolet e stafit</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->

                    <!-- row -->
                    <div class="row">
                        <c:choose>
                                <c:when test="${successDelete == true}">
                                    <div class="col-md-12 col-sm-12">
                                        <div class="alert alert-success ">Roli u fshij me sukses</div><br/>
                                    </div>    
                                </c:when>
                                <c:when test="${successDelete == false}">
                                    <div class="col-md-12 col-sm-12">
                                        <div class="alert alert-success ">Ka ndodhur gabim gjate fshierjes - ${errorMsg}</div><br/>
                                    </div>
                                </c:when>
                            </c:choose>
                        <!-- .col -->
                        
                        <c:choose>
                            <c:when test="${rolet != null}">
                                    <div class="panel panel-default col-md-12 col-sm-12">
                                <div class="panel-heading">Rolet</div>
                                <div class="panel-wrapper collapse in">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-center">#</th>
                                                <th>Emri</th>
                                                <th>Pershkrimi</th>
                                                <th>Veprimet</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${rolet}" var="rolistafit">
                                                <tr>
                                                    <td align="center">${rolistafit.id}</td>
                                                    <td>${rolistafit.emriRolit}</td>
                                                    <td>${rolistafit.pershkrimiRolit}</td>
                                                    <td class="">
                                                        <form action="rolistafit" method="get" class="d-inline-block" >
                                                            <input type="hidden" name="action" value="edit" />
                                                            <input type="hidden" name="RolistafitId" value="${rolistafit.id}" />
                                                            <input type="submit" class="fcbtn btn btn-info btn-outline btn-1b" value="Edito" />
                                                        </form>
                                                            <form action="rolistafit" method="post" class="d-inline-block">
                                                            <input type="hidden" name="mainAction" value="mainFshij" />
                                                            <input type="hidden" name="RolistafitId" value="${rolistafit.id}" />
                                                            <input type="submit" class="fcbtn btn btn-warning btn-outline btn-1b" value="Fshij"/>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                                
                            </c:when>
                        </c:choose>
                        
                    </div>
                </div>
                <!-- /.row -->
                <!-- .right-sidebar -->
                <!-- nuk ka nevoje per momentin -->
                <!-- /.right-sidebar -->
            </div>
            <!-- /.container-fluid -->
            <jsp:include page="../includes/footer.jsp" />