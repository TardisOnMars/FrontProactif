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
            todo: 'verification'
        },
        dataType: 'json'
    }).done(function (data) {
        console.log(data.type);
        if(data.type === "employe" || data.type === undefined){
            console.log(data.type);
            deconnexion();
            window.location = "index.html";
        }
    });
}

function verificationEmploye() {
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'verification'
        },
        dataType: 'json'
    }).done(function (data) {
        if(data.type === "client" || data.type === undefined){
            deconnexion();
            window.location = "index.html";
        }
    });
}
