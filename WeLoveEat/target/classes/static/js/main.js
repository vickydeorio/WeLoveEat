var orderVO = {};
var newOrderVO = {};

$(document).ready(function () {

    /* Página se inicia apenas com welcomeMensage e não a seção de pedido/edição */
    $("#edit").hide();
    $("#menuOptions").hide();

    /* Ao clicar no botão de edição, se esconde a pág de opções do menu e aparece a pagina de edição */
    $("#btnCircleEdit").click(function () {
        $("#menuOptions").hide();
        $("#edit").show();
        configEditPage(orderVO);
    });


    $("#btnCircleRemove").click(function () {
        $("#edit").hide();
        $("#menuOptions").show();
    });

    $(".menuOption").click(function () {
        $(".welcomeMsg").remove();
        $("#menuOptions").show();
    })

    /* Configura a aba de opções do cardápio */
    configOptions();

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

function test(val, name) {
    newOrderVO = orderVO;

    for(var i = 0; i < orderVO.ingredientsList.length; i++){
        if(orderVO.ingredientsList[i].ingredientVO.name == name)
            newOrderVO.ingredientsList[i].quantity = val;
    }

    updateOrder(newOrderVO);
}

function configEditPage(order) {
    $("#orderEditPrice").text("R$ "+ order.price.toFixed(2));

    var ingredients = "Contém : ";
    for(var i = 0; i < order.ingredientsList.length; i++){
        if(order.ingredientsList[i].quantity > 0) {
            ingredients += order.ingredientsList[i].ingredientVO.name + ", ";
        }

        /* Limpa qualquer conteúdo que possa estar na div, evitando duplicação de conteúdo */
        $(".ingredient"+i).empty();

        var ingName = order.ingredientsList[i].ingredientVO.name;
        var ingQuantity = order.ingredientsList[i].quantity;
        $(".ingredient"+i).prepend(ingName+'<input onchange="test(this.value, this.name);" type="number" id="'+i+'" name="'+ingName+'" value="'+ingQuantity+'" />');
    }
    ingredients += "feitos no capricho!";
    $("#orderIngredients").text(ingredients);
}

function updateOrder(order) {
    newOrderVO = order;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/updateorder",
        data: JSON.stringify(newOrderVO),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function success(data) {
            configEditPage(data);

            if(data.promotions.length > 0){
                for(var i = 0; i < data.promotions.length; i++){
                    alert("Você está participando da promoção: " + data.promotions[0]);
                }
            }
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