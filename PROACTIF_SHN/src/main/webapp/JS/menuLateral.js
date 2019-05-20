$(document).ready(function () {

    $.ajax({
            url: '../../ActionServlet',
            method: 'POST',
            data: {
                todo: 'recuperer_nom_prenom',
            },
            dataType: 'json'
        }).done(function (response) { // Appel OK
            
            nom = response.nom;
            prenom = response.prenom;
            $("#nomMenu").text(prenom + " " + nom);

        }).fail(function (error) { // Appel KO => erreur a gérer
            console.log("Fail récupérer_nom_prenom");
            if (error.status === 403) {
                window.location = "../../index.html";
            } else {
                alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
            }
        });
});


