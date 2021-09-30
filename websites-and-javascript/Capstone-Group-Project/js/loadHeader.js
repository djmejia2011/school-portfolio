if (!sessionStorage.getItem('user') || (sessionStorage.getItem('user') === '')) {
    window.location = "login.html";
}
else {
    getHeader();
    $(document).ready(function () {
        $('h3.h3').html("<em>Hello " + sessionStorage.getItem('name') + "</em>");
    })
}

function getHeader() {
    $.get('template.html', function (response) {
        $('#template').html(response)
    });
}