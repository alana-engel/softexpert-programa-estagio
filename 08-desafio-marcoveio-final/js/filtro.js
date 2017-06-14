
$('.btn-filter').on('click', function () {
    var $target = $(this).data('target');
    if ($target != 'all') {
        $('.table-filter tr').css('display', 'none');
        $('.table-filter tr[data-status="' + $target + '"]').fadeIn('slow');
    } else {
        $('.table-filter tr').css('display', 'none').fadeIn('slow');
    }
});