    let index ={
    init: function(){
        $('#btn-save').on('click',()=>{   //function >사용이유> ()=> this를 바인딩하기 위하여
            this.save();
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
            url: "/auth/joinProc",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset= utf-8",
            dataType: "json"
        }).done(function (resp) {
            if(resp.status ==500) {
                alert("회원가입 실패");
            }
            else            {
                alert("회원가입 완료");
                location.href = "/";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
    }

index.init();