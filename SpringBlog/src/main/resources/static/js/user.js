    let index ={
    init: function(){
        $('#btn-save').on('click',()=>{   //function >사용이유> ()=> this를 바인딩하기 위하여
            this.save();
        });
        $('#btn-login').on('click',()=>{   //function >사용이유> ()=> this를 바인딩하기 위하여
            this.login();
        });
    },


    save: function () {
        //alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        console.log(data);

        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset= utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("회원가입 완료");
            location.href = "/blog";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
        login: function () {
            //alert('user의 save함수 호출됨');
            let data = {
                username: $("#username").val(),
                password: $("#password").val(),
            };
            console.log(data);

            $.ajax({
                type: "POST",
                url: "/blog/api/user/login",
                data: JSON.stringify(data), //http body 데이터
                contentType: "application/json; charset= utf-8",
                dataType: "json"
            }).done(function (resp) {
                alert("로그인 완료");
                location.href = "/blog";
            }).fail(function (error) {
                alert(JSON.stringify(error))
            });
        }




    }

index.init();