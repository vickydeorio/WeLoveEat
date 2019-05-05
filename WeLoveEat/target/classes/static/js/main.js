var orderVO = {};

$(document).ready(function () {
    $("#edit").hide();
    $("#menuOptions").hide();

    $("#btnCircleEdit").click(function () {
        $("#menuOptions").hide();
        $("#edit").show();
        /*getIngredients();*/
        configEditPage();
    });


    $("#btnCircleRemove").click(function () {
        $("#edit").hide();
        $("#menuOptions").show();
    });

    $(".menuOption").click(function () {
        $(".welcomeMsg").remove();
        $("#menuOptions").show();
    })

    $("#option1").click(function () {
        getOrder($("#option1").text());
    });

    $("#option2").click(function () {
        getOrder($("#option2").text());
    });

    $("#option3").click(function () {
        getOrder($("#option3").text());
    });

    $("#option4").click(function () {
        getOrder($("#option4").text());
    });

    configOptions();
});

function configOptions() {
    /* Requisição para pegar todos os lanches do cardápio e configurar a barra de opções */
    $.get("/menuorders", function(resultado){
        $("#option1").text(resultado[0].name);
        $("#option2").text(resultado[1].name);
        $("#option3").text(resultado[2].name);
        $("#option4").text(resultado[3].name);
    });
}

function configEditPage() {
    $("#orderEditPrice").text("R$ "+ orderVO.price.toFixed(2));

    var ingredients = "Contém : ";
    for(var i = 0; i < orderVO.ingredientsList.length; i++){
        if(orderVO.ingredientsList[i].quantity > 0) {
            ingredients += orderVO.ingredientsList[i].ingredientVO.name + ", ";
        }

        var ingName = orderVO.ingredientsList[i].ingredientVO.name;
        var ingQuantity = orderVO.ingredientsList[i].quantity;
        $(".ingredient"+i).prepend(ingName+'<input type="number" id="'+i+'" name="'+ingName+'" value="'+ingQuantity+'" />');
    }
    ingredients += "feitos no capricho!";
    $("#orderIngredients").text(ingredients);
}

function updateOrder(order) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/getorder",
        data: JSON.stringify(order),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function success(data) {
            $("#orderName").replace("[name]", data.name);
            $("#menuIngredients").text(data.price);
        },
        error: function error (data) {
            alert("Algo deu errado!")
        }
    });
}

function getOrder(name) {

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/getorder",
        data: JSON.stringify(name),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function success(data) {
            orderVO = data;

            /* Adiciona o nome do pedido */
            $("#orderName").text(data.name);
            $("#orderName").append("<i class=\"fas fa-hamburger fa-5x\"></i>");

            /* Adiciona os ingredientes do pedido */
            var ingredients = "Contém : ";
            for(var i = 0; i < data.ingredientsList.length; i++){
                if(orderVO.ingredientsList[i].quantity > 0) {
                    ingredients += data.ingredientsList[i].ingredientVO.name + ", ";
                }
            }
            ingredients += "feitos no capricho!";
            $("#menuIngredients").text(ingredients);

            /* Adiciona o preço do pedido*/
            $("#orderPrice").text("R$ "+ data.price.toFixed(2))
        },
        error: function error (data) {
            alert("Algo deu errado!")
        }
    });

}