<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>



    <script type="text/javascript" src="../js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#testAjax").click(function () {

                //Ajax调用处理
                $.ajax({
                    type: "POST",
                    url: "localhost:8080/user/addUser",
                    data: {'name': $('#name').val(), 'age': $('#age').val()},//提交的数据
                    success: function (data) {
                        $("#ppf").html('<h2>' + data + '</h2>');//交互成功回调
                    }
                });

            });
        });


    </script>
</head>
<body>


<form >
    姓名<input type="text" name="name" id="name"><br/>
    年龄<input type="text" name="age" id="age"><br/>
    <button id="testAjax" type="button">提交</button>

</form>
<div id="myDiv" name="queryResult" hidden="true" >
   <h1>加油学习吧。。。</h1>
</div>
<h3 id="ppf">获取结果：</h3> <br>

</body>
</html>