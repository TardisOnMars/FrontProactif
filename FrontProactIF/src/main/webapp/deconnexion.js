function deconnexion() {
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'deconnexion'
        },
        dataType: 'json'
    }).done(function () {
        window.location = "index.html";
    });
}

function verificationClient() {
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'verificationClient'
        },
        dataType: 'json'
    }).fail(function(error){
        if(error.status === 403){
            deconnexion();
            window.location = "index.html";
            alert("Forbidden ! Vous n'avez pas le droit d'accéder à cette page");
        }
    });
}

function verificationEmploye() {
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'verificationEmploye'
        },
        dataType: 'json'
    }).fail(function(error){
        if(error.status === 403){
            deconnexion();
            window.location = "index.html";
            alert("Forbidden ! Vous n'avez pas le droit d'accéder à cette page");
        }
    });
}