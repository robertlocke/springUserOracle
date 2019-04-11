$(document).ready(function () {

    var displayCount = 0;
    var findCount = 0


    $("#findUser").click(function (e) {
        var id = $("#userID").val();
        var table = $("#tableUser");
        var tbody = $("#tableUser tbody");


        if(id.length < 1){
            alert("Please Enter ID")
        }else{
            findCount ++
            fetch('/user/' + id)
                .then(function (response) {
                    return response.json();
                })
                .then(function (user) {
                    tbody.empty();
                    tbody.append(
                        `<tr>
                            <td><label>ID: </label></td> 
                            <td><h1>"${user.id}"</h1></td>
                            </tr>
                            <tr>
                            <td><label>Name: </label></td>
                            <td><input type="text" id="userName" value="${user.name}"></td>
                            </tr>
                            <tr>
                            <td><label>Salary: </label></td>
                            <td><input type="text" id="userSalary" value="${user.salary}"></td>
                            </tr>
                        `);

                    $("#display").removeClass("d-none");

                });

        }

    });


    $("#deleteUser").click(function (e){
        var id = $("#userID").val();

        if(id.length < 1){
            alert("Please Enter ID");
        }else{
            $.ajax({
                url: '/user/' + id,
                type: 'DELETE',
                success: function () {
                    alert("Delete Successful");
                    window.location.reload();
                }
            });
        }

    });


    $("#updateUser").click(function(e){
        var id  = $("#userID").val();
        var data = "{\"name\":\"" +  $("#userName").val() + "\",\"salary\":" + $("#userSalary").val() + "}"
        var table = $("#tableUser");
        var tbody = $("#tableUser tbody");

        $.ajax({
            url: '/user/' + id,
            type: 'PUT',
            data: data,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function(result){
                alert("User Updated");
                tbody.empty();
                $("#display").addClass("d-none");
            }

        });

    });




    $("#showUsers").click(function (e) {


        var table = $("#tableUsers");
        var tbody = $("#tableUsers tbody");

        table.toggleClass("d-none");

        fetch('/user/getUsers')
            .then(function(response) {
                displayCount++;
                return response.json();
            })
            .then(function(json) {
                tbody.empty();
                json.forEach(function(user) {
                    tbody.append(
                    `<tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.salary}</td>
                    </tr>
                    `);


                });

            });

    });



    $('#addUser').click(function(e){
       var name = $('#name').val();
       var salary = parseInt($('#salary').val());
        var data = {name: name, salary: salary};

        if (name.length < 1 || salary.length < 1){
                alert("Enter Valid Name or Salary");
        }else {
            $.ajax({
                type: "POST",
                url: "/user/addUser",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                dataType: "json"

            });

            window.location.reload();
        }


    });

   ;


    /*$('#addUser').click(function(e){
        var name = $('#name').val();
        var salary = parseInt($('#salary').val());
        $.post('/user/addUser',
         {
                "\"name\"": name ,
                "\"salary\"": salary
            },
            function(){
            console.log("Success")
            });
    });  */





    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var search = {}
    search["id"] = $("#id").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/getUsers",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function showUsers(id) {
    var list = document.getElementsByClassName("users");
    for(var i = 0; i < list.length; i++) {
        list[i].classList.add('hide');
    }

    var ele = document.getElementById(id);
    ele.classList.remove('hide');
}