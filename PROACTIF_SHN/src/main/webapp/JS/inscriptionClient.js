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
    inputConfirmation = $("#confirmation").val();
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

    if (checkChampsValides(inputCivilite, inputNom, inputPrenom, inputDate,
            inputEmail, inputMdp, inputConfirmation, inputTel,
            inputAdresse, inputCodePostal, inputVille)) {

        $.ajax({
            url: '../../ActionServlet',
            method: 'POST',
            data: {
                todo: 'inscrire_client',
                civilite: inputCivilite,
                nom: inputNom,
                prenom: inputPrenom,
                date_naissance: inputDate,
                email: inputEmail,
                telephone: inputTel,
                mdp: inputMdp,
                adresse: inputAdresse,
                code_postal: inputCodePostal,
                ville: inputVille
            },
            dataType: 'json'
        }).done(function (response) { // Appel OK

            setTimeout(function () {  // Redirection avec délai de 3s
                window.location = "connexion-client.html";
            }, 2000);

        }).fail(function (error) { // Appel KO => erreur a gérer
            console.log("Fail inscrire_client");
            // Popup avec message d'erreur :
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        });
    }
    else{
         $('#myModal').modal('show');    // Affiche la Pop-Up d'erreur

        setTimeout(function () {  // Fermeture au bout de 5s
            $('#myModal').modal('hide');
        }, 8000);
    }

}

/**
 * Vérification de la validité des champs de texte d'inscription
 * @param {type} civilite
 * @param {type} nom
 * @param {type} prenom
 * @param {type} date
 * @param {type} email
 * @param {type} mdp
 * @param {type} confirmation
 * @param {type} tel
 * @param {type} adresse
 * @param {type} codePostal
 * @param {type} ville
 * @returns {Boolean}
 */
function checkChampsValides(civilite, nom, prenom, date, email, mdp, confirmation, 
                            tel, adresse, codePostal, ville){
    
    // Vérification des champs non nuls
    if(civilite === "" || civilite === null ||
       nom === "" || nom === null ||
       prenom === "" || prenom === null ||
       date === "" || date === null ||
       email === "" || email === null ||
       mdp === "" || mdp === null ||
       confirmation === "" || confirmation === null ||
       tel === "" || tel === null ||
       adresse === "" || adresse === null ||
       codePostal === "" || codePostal === null ||
       ville === "" || ville === null){
   
       return false;
    }
    // Vérification mdp différent de confirmation
    else if(mdp !== confirmation){
        return false;
    }
    // Vérification que le code postal et le numéro de tel sont des nombres
    else if(isNaN(codePostal) || isNaN(tel)){
        return false;
    }
    
    return true;
}

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