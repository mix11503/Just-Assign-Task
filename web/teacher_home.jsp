<%-- 
    Document   : teacher_home
    Created on : Mar 18, 2017, 5:31:20 PM
    Author     : Mix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE 2 | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.2 -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />    
        <!-- FontAwesome 4.3.0 -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons 2.0.0 -->
        <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />    
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins 
             folder instead of downloading all of them to reduce the load. -->
        <link href="dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
        <!-- iCheck -->
        <link href="plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- Daterange picker -->
        <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <style>
            body {
                font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial, sans-serif;
                font-weight: 500;
            }
            .content-wrapper {
                padding: 0 10px;
                font-size: 14px;
            }
            .page-header {
                border: 0;        
                margin: 0 0 20px 0;
                padding: 0;
                position: relative;
                z-index: 1;
                font-size: 30px;
            }
            section[id] {
                padding: 20px 0 0 0;
            }
            #components > h3 {
                font-size: 25px;
                border-bottom: 1px solid #dedede;
                color: #000;
            }
            #components > h4 {
                font-size: 20px;
                color: #000;
            }
            .page-header span {
                z-index: 5;
                display: inline-block;
                background-color: #ecf0f5;
                padding-right: 10px;
            }
            .page-header::before {
                content: " ";
                display: block;
                background: #ccc;
                width: 100%;
                height: 1px;
                position: absolute;
                top: 50%;
                margin-top: 2px;
                z-index: -1;
            }
            .lead {
                font-size: 16px;
                font-weight: 400;
            }
            .eg{
                position: absolute;
                top: 0;
                left: 0;
                display: inline-block;
                background: #d2d6de;
                padding: 5px;
                border-bottom-right-radius: 3px;
                border-top-left-radius: 3px;
                border-bottom: 1px solid #d2d6dc;
                border-right: 1px solid #d2d6dc;
            }
            .eg + * {
                margin-top: 30px;
            }
            .content {
                padding: 10px 25px;
            }
            .hierarchy {
                background: #333;
                color: #fff;
            }
            .plugins-list li {
                width: 50%;
                float: left;
            }
            pre {
                border: none;
            }
            /* desert scheme ported from vim to google prettify */
            pre.prettyprint {display: block; background-color: #333; max-height: 300px; border: none!important; margin-bottom: 20px;}
            pre .nocode { background-color: none; color: #000 }
            pre .str { color: #ffa0a0;} /* string  - pink */
            pre .kwd { color: #f0e68c; font-weight: bold }
            pre .com { color: #87ceeb } /* comment - skyblue */
            pre .typ { color: #98fb98 } /* type    - lightgreen */
            pre .lit { color: #cd5c5c } /* literal - darkred */
            pre .pun { color: #fff }    /* punctuation */
            pre .pln { color: #fff }    /* plaintext */
            pre .tag { color: #f0e68c; font-weight: bold } /* html/xml tag    - lightyellow */
            pre .atn { color: #bdb76b; font-weight: bold } /* attribute name  - khaki */
            pre .atv { color: #ffa0a0 } /* attribute value - pink */
            pre .dec { color: #98fb98 } /* decimal         - lightgreen */

            /* Specify class=linenums on a pre to get line numbering */
            ol.linenums { margin-top: 0; margin-bottom: 0; color: #AEAEAE } /* IE indents via margin-left */
            li.L0,li.L1,li.L2,li.L3,li.L5,li.L6,li.L7,li.L8 { list-style-type: none }
            /* Alternate shading for lines */
            li.L1,li.L3,li.L5,li.L7,li.L9 { }

            @media print {
                pre.prettyprint { background-color: none }
                pre .str, code .str { color: #060 }
                pre .kwd, code .kwd { color: #006; font-weight: bold }
                pre .com, code .com { color: #600; font-style: italic }
                pre .typ, code .typ { color: #404; font-weight: bold }
                pre .lit, code .lit { color: #044 }
                pre .pun, code .pun { color: #440 }
                pre .pln, code .pln { color: #000 }
                pre .tag, code .tag { color: #006; font-weight: bold }
                pre .atn, code .atn { color: #404 }
                pre .atv, code .atv { color: #060 }
            }
            .sidebar {
                margin-top: 0;
                padding-top: 0!important;
            }
            .box .main-header {
                z-index: 1000;
                position: relative;
            }
            .treeview .nav li a:hover,
            .treeview .nav li a:active {
                background: transparent;
            }
        </style>
    </head>
    <body class="skin-blue">
        <div class="wrapper">

            <header class="main-header">
                <!-- Logo -->
                <a href="index2.html" class="logo"><b>JAT</b></a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-pencil"></i>
                                    <span class="hidden-xs">Assign New Task</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li>
                            <a href="#">
                                <i class="fa fa-tasks"></i> <span>All Task</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-book"></i> <span>My Subject</span> <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="index.html"><i class="fa fa-circle-o"></i> INT 301 dfsdfsfdsf</a></li>
                                <li><a href="index2.html"><i class="fa fa-circle-o"></i> INT 555 sdasdafdfs </a></li>
                                <li><a href="index2.html"><i class="fa fa-plus-circle"></i> Create New Subject... </a></li>
                            </ul>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        All Task
                        <small>Control panel</small>
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Responsive Hover Table</h3>
                                    <div class="box-tools">
                                        <div class="input-group">
                                            <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>Task ID</th>
                                            <th>Topic</th>
                                            <th>Subject</th>
                                            <th>Start</th>
                                            <th>Deadline</th>
                                            <th>Status</th>
                                        </tr>
                                        <tr>
                                            <td>001</td>
                                            <td>รายงานความคืบหน้าโปรเจค</td>
                                            <td>INT201 Java Programing</td>
                                            <td>19-Mar-2017</td>
                                            <td><font color="red">26-Mar-2017</font></td>
                                            <td><span class="label label-primary">Waiting</span></td>
                                        </tr>
                                        <tr>
                                            <td>001</td>
                                            <td>รายงานความคืบหน้าโปรเจค</td>
                                            <td>INT201 Java Programing</td>
                                            <td>19-Mar-2017</td>
                                            <td><font color="red">26-Mar-2017</font></td>
                                            <td><span class="label label-danger">Late</span></td>
                                        </tr>
                                        
                                    </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.0
                </div>
                <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved.
            </footer>
        </div><!-- ./wrapper -->

        <!-- jQuery 2.1.3 -->
        <script src="plugins/jQuery/jQuery-2.1.3.min.js"></script>
        <!-- jQuery UI 1.11.2 -->
        <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
            $.widget.bridge('uibutton', $.ui.button);
        </script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>    
        <!-- Morris.js charts -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="plugins/morris/morris.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="plugins/knob/jquery.knob.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!-- datepicker -->
        <script src="plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- Slimscroll -->
        <script src="plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <!-- FastClick -->
        <script src='plugins/fastclick/fastclick.min.js'></script>
        <!-- AdminLTE App -->
        <script src="dist/js/app.min.js" type="text/javascript"></script>

        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="dist/js/pages/dashboard.js" type="text/javascript"></script>

        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js" type="text/javascript"></script>
    </body>
</html>