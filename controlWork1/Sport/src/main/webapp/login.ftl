<!DOCTYPE html>
<html lang="EN">

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js">
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js">
    </script>
</head>

<head>
    <meta name="viewport" content="width=device-width; initial-scale=1">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial;
            font-size: 17px;
        }

        #myVideo {
            position: fixed;
            width: auto;
            height: auto;
            max-height: 100%;
        }

        .content {
            position: fixed;
            background: rgba(0; 0; 0; 0.5);
            color: #f1f1f1;
            width: 100%;
            padding: 20px;
        }

        #myBtn {
            width: 200px;
            font-size: 18px;
            padding: 10px;
            border: none;
            background: #000;
            color: #fff;
            cursor: pointer;
        }

        #myBtn:hover {
            background: #ddd;
            color: black;
        }
    </style>
</head>

<style>
    .button-container-div {
        text-align: center;
    }
</style>

<body style="background-color:black;">
<!-- The video -->
<div class="content">
    <h1 class="text-center text-white">
        <br><br><br><br>
        Check your weather
    </h1>


    <div class="container">
        <div class="col-lg-8
		m-auto d-block">
            <form action="login" method="post">
                <div class="form-group">
                    <label for="_login">
                        Login:
                    </label>
                    <input type="text" name="_login" id="_login" class="form-control">
                    <h5 id="usercheck" style="color: red;">
                        *Login is missing
                    </h5>
                </div>

                <div class="form-group">
                    <label for="_password">
                        Password:
                    </label>
                    <input type="password" name="_password" id="_password" class="form-control">
                </div>

                <br>

                <div class="button-container-div">
                    <input type="submit" id="submitbtn" value="Login" class="btn btn-primary">
                </div>
                <br>

                <div class="button-container-div">
                    <font size="-1">
                        Don't have an account yet? <a href="/registration.html">Register now</a>
                    </font>
                </div>

                <br><br><br><br><br><br>
            </form>
        </div>
    </div>
</div>

<script src="app.js"></script>
</body>

</html>

