/**
 * Fonction qui inscrit le nouveau client si les données renseignées sont 
 * correctes
 * @returns {undefined}
 */
function inscriptionClient() {
    inputCivilite = $("input[name='civilite']:checked").val();
    inputNom = $("#nom").val();
    inputPrenom = $("#prenom").val();
    inputDate = $("#dateNaissance").val();
    inputEmail = $("#email").val();
    inputMdp = $("#motDePasse").val();
    inputTel = $("#telephone").val();
    inputAdresse = $("#adresse").val();
    inputCodePostal = $("#codePostal").val();
    inputVille = $("#ville").val();
    
    /* //USED FOR DEBUG 
    console.log(inputCivilite);
    console.log(inputNom);
    console.log(inputPrenom);
    console.log(inputDate);
    console.log(inputEmail);
    console.log(inputTel);
    console.log(inputAdresse);
    console.log(inputCodePostal);
    console.log(inputVille);*/

    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'inscrire_client',
            civilite : inputCivilite,
            nom : inputNom,
            prenom : inputPrenom,
            date_naissance : inputDate,
            email : inputEmail,
            telephone : inputTel,
            mdp : inputMdp,
            adresse : inputAdresse,
            code_postal : inputCodePostal,
            ville : inputVille
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        setTimeout(function () {  // Redirection avec délai de 3s
            window.location = "info-perso-client.html";
        }, 2000);

    }).fail(function (error) { // Appel KO => erreur a gérer
        console.log("Fail inscrire_client");
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

/**
 * 
 * @returns {undefined}
 */
function cloturerIntervention(){
    console.log("Cloturer intervention");
    if(checkChampsValides()){   // Si les champs sont valides
        console.log("Champ valides");
    }
    else{  // Si les champs sont incorrects
        $('#myModal').modal('show');    // Affiche la Pop-Up d'erreur

        setTimeout(function () {  // Fermeture au bout de 5s
            $('#myModal').modal('hide');
        }, 5000);
    }
}

/**
 * Vérification de la validité des champs de texte
 * @returns {Boolean}
 */
function checkChampsValides(){
    statut = $("#inputStatut").val();
    date = $("#inputDateCloture").val();
    commentaire = $("#inputCommentaire").val();
    
    console.log("statut: " + statut );
    console.log("date: " + date);
    console.log("commentaire: " + commentaire);
    
    if(statut === "" || date === "" | commentaire === ""){
        console.log("Erreur Champ vide");
        return false;
    } else if(statut !== "Echec" || statut !== "Succès"){
        console.log("Erreur statut non valide");
        return false;
    } else if(!isValidDate(date)){
        console.log("Erreur date non valide");
        return false;
    }
    
    return true;
}

/**
 * Validation du format de la date entrée sous la forme JJ/MM/AAAA
 * @param {type} dateString
 * @returns {Boolean} */
function isValidDate(dateString)
{
    console.log("Test de validité de la date");
    // Première vérification du format
    if(!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
        return false;

    // Conversion des parties de la date sous forme d'entiers
    var parts = dateString.split("/");
    var day = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[2], 10);
    
    // Conversion des parties de la date de demande
    var partsDemande = $("#dateDemande").val().split("/");
    var dayDemande = parseInt(partsDemande[0], 10);
    var monthDemande = parseInt(partsDemande[1], 10);
    var yearDemande = parseInt(partsDemande[2], 10);
    
    console.log( day + "/" + month + "/" + year);
    console.log("Date demande :"+ dayDemande + "/" + monthDemande + "/" + yearDemande);
    
    // Vérification de l'année, du mois et du jour
    if(year < yearDemande || year > 3000 || month === 0 || month > 12){
        
        // Cas mois < mois demande
        if(year === yearDemande 
           && month < monthDemande){
            return false;
        }
        // Cas jour < jour demande
        else if(year === yearDemande 
                && month === monthDemande
                && day < dayDemande){
            return false;
        }
    }

    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

    // Ajustement pour les années bisextiles
    if(year%400 === 0 || (year%100 !== 0 && year%4 === 0))
        monthLength[1] = 29;

    // On vérifie le jour du mois
    return day > 0 && day <= monthLength[month - 1];
};

$(document).ready(function () {
    
    // Click bouton valider
    $('#bouton-inscription').on('click', function () { 
        inscriptionClient();
    });
    
    // Click bouton annuler
    $('#bouton-annuler').on('click', function () { 
        window.location = "connexion-client.html";
    });
   
});