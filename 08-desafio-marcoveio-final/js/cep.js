var timer;
 $('#find').on('click', function() {
        var idCep = $('#cep'),
        result = $('#comochegar'),
        webServiceDomain = "https://viacep.com.br/ws/",
        typeData = "/json/?callback=?";
        searchCEP(idCep, result, webServiceDomain, typeData);
    });

function searchCEP(idCep, result, webServiceDomain, typeData) {
	clearInterval(timer);
    function clean_form_cep() {
       $("#comochegar").val(" ");
    }
    var cep = idCep.val().replace(/\D/g, '');
    if (cep != "") {
        var validatecep = /^[0-9]{8}$/;
        if(validatecep.test(cep)) {
            result.val("...");
            $.getJSON(webServiceDomain+ cep +typeData, function(dados) {
                if (!("erro" in dados)) {
                    result.val("Para chegar ao CEP "+ cep+", pegue um avião em direção ao estado de " + dados.uf+" , um ônibus para a cidade de "+dados.localidade+" e encontre de carro no bairro "+dados.bairro+"a rua "+dados.logradouro+".");
                }
                else {
                   timer= setInterval(function() {clean_form_cep(); clearInterval(timer);}, 3000);
            	   result.val("CEP não encontrado");
                }
            });
        } 
        else {
            timer= setInterval(function() {clean_form_cep(); clearInterval(timer);}, 3000);
            result.val("CEP não encontrado");
        } 
    }
    else {
        clean_form_cep();
    }  
}


