var timer;
 $('#find').on('click', function() {
        searchCEP();
    });
function searchCEP() {
	clearInterval(timer);
    function clean_form_cep() {
       $("#comochegar").val(" ");
    }
    var cep = $("#cep").val().replace(/\D/g, '');
    if (cep != "") {
        var validatecep = /^[0-9]{8}$/;
        if(validatecep.test(cep)) {
            $("#comochegar").val("...");
            $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
                if (!("erro" in dados)) {
                    $("#comochegar").val("Para chegar ao CEP "+ cep+", pegue um avião em direção ao estado de " + dados.uf+" , um ônibus para a cidade de "+dados.localidade+" e encontre de carro no bairro "+dados.bairro+"a rua "+dados.logradouro+".");
                }
                else {
                   timer= setInterval(function() {clean_form_cep(); clearInterval(timer);}, 3000);
            	   $("#comochegar").val("CEP não encontrado");
                }
            });
        } 
        else {
            timer= setInterval(function() {clean_form_cep(); clearInterval(timer);}, 3000);
            $("#comochegar").val("CEP não encontrado");
        } 
    }
    else {
        clean_form_cep();
    }  
}