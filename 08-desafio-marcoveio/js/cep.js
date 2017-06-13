function findCep(elCep, elResult, webServiceDomain, typeData) {
    
    // Referencia - https://pt.stackoverflow.com/questions/121515/buscar-cep-por-rua-php/121690#121690
    // Referencia EXCELENTE - http://jsfiddle.net/brunoaugusto/6fV6C/

    var cep = elCep.val(),
        cepNumbers = cep.replace(/\.|\-/g, '');

    if (typeof cepNumbers === 'string') {
        alert('Não dibre o dibrador. Faça um fork e melhore o regex, você consegue!');
        $('body').html('<img src="https://img.r7.com/images/2015/02/11/638pe7ok9_6vmfg8w28p_file?dimensions=780x536&no_crop=true">');
    }

    if (cepNumbers != "") {
        var url = webServiceDomain + cepNumbers + typeData;

        elResult.val('...');

        $.getJSON(url, function(dados) {
            if ("erro" in dados) {
                elResult.val('Não foi possivel encontrar o cep: '+ cepNumbers);
            } else {
                elResult.val('Para chegar ao CEP '+ cep +', pegue um avião em direção ao estado de '+ dados.uf +' , um ônibus para a cidade de '+ dados.localidade +' e encontre de carro no bairro '+ dados.bairro +' a rua '+ dados.logradouro +'.');
            }
        });
    }
}

function startCep() {

    var elCep = $('#cep'),
        elResult = $('#findResult'),
        webServiceDomain = 'https://viacep.com.br/ws/',
        typeData = '/json';

    $('#find').on('click', function() {
        findCep(elCep, elResult, webServiceDomain, typeData);
    });

}