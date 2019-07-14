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
    <title>OculusVision - Shto Mjekun</title>
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
        <c:import url="includes/menu.jsp" />
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Shto Mjekun</h4> </div>
                        <ol class="breadcrumb">
                            <li><a href="index.html">Mjekët</a></li>
                            <li class="active">Shto Mjekun</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                
                <!-- row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <h3 class="box-title">Informatat Bazike</h3>
                            <div class="alert alert-danger"> Lorem ipsum dolor sit amet, consectetur adipisicing elit. </div>
                            <form class="form-material form-horizontal" action="mjeku" method="POST">
                                <input type="hidden" name="mainAction" value="mainShto" />
                                <div class="form-group">
                                    <label class="col-md-12" for="emri">Emri</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="emri" name="emri" class="form-control" > </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="mbiemri">Mbiemri</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="mbiemri" name="mbiemri" class="form-control" > </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="bdate">Ditëlindja</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="bdate" name="bdate" class="form-control mydatepicker"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Gjinia</label>
                                    <div class="col-sm-12">
                                        <select class="form-control">
                                            <option>Zgjedh gjininë</option>
                                            <option>Mashkull</option>
                                            <option>Femer</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Fotografia</label>
                                    <div class="col-sm-12">
                                        <div class="fileinput fileinput-new input-group" data-provides="fileinput">
                                            <div class="form-control" data-trigger="fileinput"> <i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div> <span class="input-group-addon btn btn-default btn-file"> <span class="fileinput-new"></span> <span class="fileinput-exists">Change</span>
                                            <input type="file" name="..."> </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a> </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="special">Specializimi</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="special" name="special" class="form-control" > </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Përshkrimi</label>
                                    <div class="col-md-12">
                                        <textarea class="form-control" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="url">Webfaqja</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="url" name="url" class="form-control" > </div>
                                </div>
                                <h3 class="box-title">Informacionet për llogarinë</h3>
                                <div class="form-group">
                                    <label class="col-md-12" for="email-address">Email Adresa</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="email" id="email-address" name="email-address" class="form-control"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="telephone-nr">Numri i telefonit</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="text" id="telephone-nr" name="telephone-nr" class="form-control" data-mask="(999) 999-9999"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="pwd">Fjalëkalimi</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="password" id="pwd" name="pwd" class="form-control"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12" for="cpwd">Konfirmimi i fjalëkalimit</span>
                                    </label>
                                    <div class="col-md-12">
                                        <input type="password" id="cpwd" name="cpwd" class="form-control"> </div>
                                </div>
                                <button type="submit" class="btn btn-info waves-effect waves-light m-r-10">Regjistro</button>
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
            <jsp:include page="includes/footer.jsp" />