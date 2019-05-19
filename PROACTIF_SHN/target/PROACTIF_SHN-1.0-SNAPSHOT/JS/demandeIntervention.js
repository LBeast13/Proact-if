function demanderIntervention(){
    
    typeInterv = $("input[name='radioInterv']:checked").val();
    animalInput = $("#champ-animal").val();
    objetInput = $("#champ-objet").val(); 
    entrepriseInput =  $("#champ-entreprise").val();
    descriptionInput = $("#inputDescription").val();
    
    if(checkChampsValides(typeInterv)) {
        $.ajax({
            url: '../../ActionServlet',
            method: 'POST',
            data: {
                todo: 'demander_intervention',
                type: typeInterv,
                animal: animalInput,
                objet: objetInput,
                entreprise: entrepriseInput,
                description: descriptionInput
            },
            dataType: 'json'
        }).done(function (response) { // Appel OK
            
            numeroIntervention = response.num_interv;
            console.log(numeroIntervention);
            
            window.location = "detail-intervention-client.html?numInterv=" + numeroIntervention;

        }).fail(function (error) { // Appel KO => erreur a gérer
            console.log("Fail remplir_detail_intervention_employe");
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
 * Vérifie la validité des champs de textes (chaînes vides) en fonction du type
 * d'intervention
 * @param {type} type le type de l'intervention selectionné
 * @returns {Boolean} si les champs pour le type sélectionné sont valides
 */
function checkChampsValides(type){
    switch(type){
        case "Animal":
            animal = $("#champ-animal").val();
            if(animal === "" || animal === null){
                return false;
            }
            break;
        case "Livraison":
            objet = $("#champ-objet").val();
            entreprise = $("#champ-entreprise").val();
            if(objet === "" || objet === null ||
               entreprise === "" || entreprise === null){
               
               return false;
            }
            break;
    }
    
    description = $("#inputDescription").val();

    if(description === "" || description === null){
        return false;
    }
    
    return true;
}

/**
 * Désactive/active les champs de textes en fonction du type d'intervention sélectionné
 * @returns {undefined}
 */
function setChamps() {
    var radioValue = $("input[name='radioInterv']:checked").val();
    
    if (radioValue === "Animal") {
       $("#champ-animal").removeAttr("disabled");
       
       $("#champ-entreprise").attr("disabled",true);
       $("#champ-objet").attr("disabled",true);
       
       $("#champ-entreprise").val("");
       $("#champ-objet").val("");
       
    } else if(radioValue === "Livraison"){
       $("#champ-animal").attr("disabled",true);
       
       $("#champ-entreprise").removeAttr("disabled");
       $("#champ-objet").removeAttr("disabled");
       
       $("#champ-animal").val("");
       
    } else if(radioValue === "Incident"){
       $("#champ-animal").attr("disabled",true);
       $("#champ-entreprise").attr("disabled",true);
       $("#champ-objet").attr("disabled",true); 
       
       $("#champ-animal").val("");
       $("#champ-entreprise").val("");
       $("#champ-objet").val("");
    }
}

$(document).ready(function () {

    setChamps();
    
    $('#bouton-valider').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Valider"');
        // appel de la fonction de modification des infos persos
        demanderIntervention();
    });

    $('#bouton-annuler').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Annuler"');

        window.location = "info-perso-client.html";
    });
});



