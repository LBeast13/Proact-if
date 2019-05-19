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
            
            todo: 'remplir_informations_perso_client',
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK

        civilite = response.civilite;
        updateCivilite(civilite);
        $('#champ-nom').val(response.nom);
        $('#champ-prenom').val(response.prenom);
        $('#dateNaissanceClient').html(response.dateNaissance);
        $('#emailClient').html(response.email);
        $('#champ-tel').val(response.tel);
        $('#champ-adresse').val(response.adresse);
        $('#champ-codepostal').val(response.codePostal);
        $('#champ-ville').val(response.ville);
       
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_informations_perso");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

/**
 * Fonction Ajax qui prend en compte les modifications
 * @returns {undefined}
 */
function ModifierInfoPerso() {
    
    nomInput = $("#champ-nom").val();
    console.log(nomInput);
    prenomInput = $("#champ-prenom").val();
    numeroInput = $("#champ-tel").val();
    adresseInput = $("#champ-adresse").val();
    codePostalInput = $("#champ-codepostal").val();
    ville = $("#champ-ville").val();
    
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data:{
            todo: "modifer_info_perso_client",
            nom : nomInput,
            prenom : prenomInput,
            numero : numeroInput,
            adresse : adresseInput,
            codePostal : codePostalInput,  
            ville : ville
        }

    }).done(function () { // Appel OK
        
        window.location = "info-perso-client.html";
       
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
    
    $('#bouton-valider').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Modifier"');
        // appel de la fonction de modification des infos persos
        ModifierInfoPerso();
    });
    
    $('#bouton-annuler').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Annuler"');
        
        window.location = "info-perso-client.html";
    });
});


