    let index ={
    init: function(){
        $('#btn-save').on('click',()=>{
            this.save();
        });

    },


    save: function () {

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),

        };
        console.log(data);

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset= utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글쓰기 완료");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
    }

index.init();