/**
 * Fonction Ajax qui met à jours le DOM avec les données personnelles du
 * client connecté.
 * @returns {undefined}
 */
function remplirDonneesPerso() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_informations_perso_client'
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        civilite = response.civilite;
        updateCivilite(civilite);
        $('#nomClient').html(response.nom);
        $('#prenomClient').html(response.prenom);
        $('#dateNaissanceClient').html(response.dateNaissance);
        $('#emailClient').html(response.email);
        $('#telephoneClient').html(response.tel);
        $('#adresseClient').html(response.adresse);
        $('#codePostalClient').html(response.codePostal);
        $('#villeAdresseClient').html(response.ville);
       
    }).fail(function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_informations_perso_client");
        if (error.status === 403) {
            window.location = "connexion-client.html";
        } else {
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        }
    });
}

/**
 * Met à jour la Tab du DOM pour la civilité
 * @param {String} civilite du client connecté
 * @returns {undefined}
 */
function updateCivilite(civilite){
    if(civilite === 'M'){
        $('#ongletMr').prop("disabled", false);
        $('#ongletMme').prop("disabled", true);
        $('#ongletMlle').prop("disabled", true);
    }
    else if(civilite === 'Mme'){
        $('#ongletMr').prop("disabled", true);
        $('#ongletMme').prop("disabled", false);
        $('#ongletMlle').prop("disabled", true);
    }
    else if(civilite === 'Mlle'){
        $('#ongletMr').prop("disabled", true);
        $('#ongletMme').prop("disabled", true);
        $('#ongletMlle').prop("disabled", false);
    }
}


$(document).ready(function () {
    console.log("Test infoPersoClient.js");
    remplirDonneesPerso();
});



