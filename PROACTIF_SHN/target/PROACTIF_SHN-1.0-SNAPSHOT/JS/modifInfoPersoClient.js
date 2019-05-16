/**
 * Fonction Ajax qui met à jours le DOM avec les données personnelles du
 *client connecté.
 * @returns {undefined}
 */
function remplirDonneesPerso() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            
            todo: 'remplir_informations_perso',
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        console.log("TODO : Régler le raffraichissement de la page lors de l'appel\n\
        AJAX de remplir d'info perso (on ne veut pas de raffraichissement pour pas avoir 2 appels)");
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
       
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_informations_perso");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}




/**
 * Met à jour la Tab du DOM pour la civilité
 * @param {String} civilite du client connecté
 * @returns {undefined}
 */
function updateCivilite(civilite){
    console.log(civilite);
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



/**
 * Fonction Ajax qui met à jours le DOM avec les données personnelles du
 *client connecté.
 * @returns {undefined}
 */
function ModifierInfoPerso() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: JSON.stringify(
                {
            "Nom" : $("#champ-nom").val,
            "Prenom" :$("#champ-prenom").val,
            "Numero" : $("#champ-number").val,
            "Adresse" : $("#champ-adresse").val,
            "CodePostal" : $("#champ-codepostal").val,  
            "Ville" : $("#champ-ville").val,   
            "todo": "modifer_info_perso_client"
                })
         
        ,
        dataType: "json"
    }).done(function (response) { // Appel OK
        console.log("TODO : Régler le raffraichissement de la page lors de l'appel\n\
        AJAX de remplir d'info perso (on ne veut pas de raffraichissement pour pas avoir 2 appels)");
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
       
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_informations_perso");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}