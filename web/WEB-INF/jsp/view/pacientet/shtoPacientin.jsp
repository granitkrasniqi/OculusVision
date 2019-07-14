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
    <title>OculusVision - Shto Antarin (Staf) </title>
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
                        <h4 class="page-title">Shto Pacientin</h4> </div>
                        <ol class="breadcrumb">
                            <li><a href="dashboard">Klinika</a></li>
                            <li class="active">Shto Pacientin</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                
                <!-- row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <h3 class="box-title">Informatat</h3>
                            <c:choose>
                                <c:when test="${success == true}">
                                        <div class="alert alert-success">Pacienti u regjistrua me sukses</div>
                                </c:when>
                                <c:when test="${errors != null}">
                                        <div class="alert alert-danger">
                                            <ul>
                                            <c:forEach items="${errors}" var="errMsg">
                                                <li><c:out value="${errMsg}"/></li>
                                            </c:forEach>
                                            </ul>
                                        </div>
                                </c:when>
                            </c:choose>
                                        <form class="form-material form-horizontal" action="pacienti" method="POST">
                                <input type="hidden" name="mainAction" value="mainShto" />
                                <div class="form-group">
                                    <label class="col-md-12" for="emri">Emri</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="emri" name="emri" class="form-control" value="${emri}" required> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="mbiemri">Mbiemri</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="mbiemri" name="mbiemri" class="form-control" value="${mbiemri}" required> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="gjinia">Gjinia</span>
                                    </label>
                                    <div class="col-md-12">
                                        <select id="gjinia" name="gjinia" class="form-control" required>
                                            <option value="F">Femër</option>
                                            <option value="M">Mashkull</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="dataLindjes" >Data Lindjes</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="dataLindjes" name="dataLindjes" class="form-control mydatepicker" placeholder="dd/mm/yyyy" value="${dataLindjes}" required> </div>
                                    </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="adresa">Adresa</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="adresa" name="adresa" class="form-control" value="${adresa}" required> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="email">Email:</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="email" id="email" name="email" class="form-control" value="${email}" required> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="numriTelefonit">Numri Telefonit:</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="numriTelefonit" name="numriTelefonit" class="form-control" value="${numriTelefonit}" required> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Përshkrimi</label>
                                    <div class="col-md-12">
                                        <textarea class="form-control" rows="3" name="pershkrimi" required>${pershkrimi}</textarea>
                                    </div>
                                </div>
                                        <c:choose>
                                            <c:when test="${success == true}">
                                                <button type="submit" class="btn btn-info waves-effect waves-light m-r-10" hidden="true">Regjistro</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-info waves-effect waves-light m-r-10">Regjistro</button>
                                            </c:otherwise>
                                        </c:choose>
                                <button type="submit" class="btn btn-inverse waves-effect waves-light">Anulo</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .right-sidebar -->
                <!-- nuk ka nevoje per momentin -->
                <!-- /.right-sidebar -->
            </div>
            <!-- /.container-fluid -->
            <jsp:include page="../includes/footer.jsp" />