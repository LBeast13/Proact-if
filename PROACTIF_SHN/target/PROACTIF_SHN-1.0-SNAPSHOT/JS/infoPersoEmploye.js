/**
 * Fonction Ajax qui met à jours le DOM avec les données personnelles de 
 * l'employé connecté.
 * @returns {undefined}
 */
function remplirDonneesPerso() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_informations_perso_employe',
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        civilite = response.civilite;
        updateCivilite(civilite);
        $('#nomEmploye').html(response.nom);
        $('#prenomEmploye').html(response.prenom);
        $('#dateNaissanceEmploye').html(response.dateNaissance);
        $('#emailEmploye').html(response.email);
        $('#telephoneEmploye').html(response.tel);
        $('#adresseEmploye').html(response.adresse);
        $('#codePostalEmploye').html(response.codePostal);
        $('#villeAdresseEmploye').html(response.ville);
       
    }).fail(function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_informations_perso");
        if (error.status === 403) {
            window.location = "../../index.html";
        } else {
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        }
    });
}

/**
 * Met à jour la Tab du DOM pour la civilité
 * @param {String} civilite de l'employé connecté
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
    remplirDonneesPerso();
});




