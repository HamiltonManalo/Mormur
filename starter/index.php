<?php 
  $path = $_SERVER['DOCUMENT_ROOT'];
  $path .= "/partials/header.php";
  include_once($path);
?>

  <body class="home">

<?php 
  $path = $_SERVER['DOCUMENT_ROOT'];
  $path .= "/partials/navbar.php";
  include_once($path);
?>

    <section class="content">
      <div class="container">
        <p>Hello World</p>
      </div><!-- /.container -->
    </section><!-- /.content -->

<?php 
  $path = $_SERVER['DOCUMENT_ROOT'];
  $path .= "/partials/footer.php";
  include_once($path);
?>

  </body>
</html>