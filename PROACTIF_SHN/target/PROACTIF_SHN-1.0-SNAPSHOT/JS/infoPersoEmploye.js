function remplirDonneesPerso() {
    var champLogin = $('#champ-login').val();
    var champPassword = $('#champ-password').val();

    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_informations_perso',
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        console.log("OK");  
        console.log(response.nom);
        $('#nomEmploye').html(response.nom);
        $('#prenomEmploye').html(response.prenom);
        $('#dateNaissanceEmploye').html(response.dateNaissance);
        $('#emailEmploye').html(response.email);
        $('#telephoneEmploye').html(response.tel);
        $('#adresseEmploye').html(response.adresse);
        $('#codePostalEmploye').html(response.codePostal);
        $('#villeAdresseEmploye').html(response.ville);
       
    }).fail( function (error) { // Appel KO => erreur a gérer
         console.log("Erreur");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

$(document).ready(function () {
    remplirDonneesPerso();
});




