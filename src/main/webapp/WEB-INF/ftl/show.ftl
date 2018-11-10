<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(function () {
            var msg="${msg!}"
            var mhref = "${mhref!}";
            var oshow=document.getElementById("show");
            function count(num,show){
                function done(){
                    show.innerHTML=msg+":"+num+"秒";
                    num=num-1;
                    if(num<0){
                        location.href=mhref;
                    }
                    setTimeout(done,1000);
                }
                done();
            }
            count(3,oshow);
        });
    </script>
</head>
<body>
<div id="show"></div>
</body>
</html>