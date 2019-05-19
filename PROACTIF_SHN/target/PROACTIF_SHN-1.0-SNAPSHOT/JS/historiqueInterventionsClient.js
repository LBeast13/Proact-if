/**
 * Met en forme la ligne représentant l'intervention et l'ajoute au tableau du DOM
 * @param {type} intervention qu'on doit ajouter dans le tableau
 * @returns {undefined}
 */
function ajouter(intervention){
    var row = "<tr><th scope=\"row\" onclick='voirDetail("+intervention.numero+")'>" + intervention.numero 
            + "</th><td><b>"+ intervention.type + "</b></td><td><b>"
            + intervention.date + "</b></td><td><b>"
            + intervention.client + "</b></td><td><b>"
            + intervention.statut + "</b></td></tr>";       
    
    $("#tableBody").append(row);
}

/**
 * Fonction de click sur le numéro d'intervention.
 * @param {type} numeroIntervention le paramètre ajouté dans l'URL
 * @returns {undefined}
 */
function voirDetail(numeroIntervention){
    window.location = "detail-intervention-client.html?numInterv=" + numeroIntervention;
}

/**
 * Fonction Ajax qui met à jours le tableau des interventions de 
 * l'employé connecté.
 * @param {String} dropdownState 
 * @param {String} checkboxState
 * @returns {undefined}
 */
function remplirTableauHistorique(dropdownState, checkboxState) {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_historique_interventions_client',
            type : dropdownState,
            enCours : checkboxState    
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        $('#tableBody').empty();
        var interventions = response.interventions;
        
        if(interventions !== "Vide"){
            for (var i = 0; i < interventions.length; i++) {
                ajouter(interventions[i]);
            } 
        }
        
        
    }).fail(function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_historique_interventions_employe");
        if (error.status === 403) {
            window.location = "connexion-client.html";
        } else {
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        }
    });
}

/**
 * Fonction appellée lors d'un changement dans les filtres
 * @returns {undefined}
 */
function filtrage(){
    var checkBox = $("#enCoursCheck");
    var dropdown = $("#typeSelect");
    
    checkboxState = getCheckBoxState(checkBox);
    dropdownState = getDropdownState(dropdown);
    
    remplirTableauHistorique(dropdownState, checkboxState);
}

/**
 * Get the checkBox state and returns it.
 * @param {type} checkBox the checkbox to be checked
 * @returns {undefined} the state of the checkbox
 */
function getCheckBoxState(checkBox){
    if(checkBox.prop("checked") === true){ // Checked
        console.log("Checked");
        return "true";
    }else if(checkBox.prop("checked") === false){ // Not Checked
        console.log("Not Checked");
        return "false";
    }else{ // Erreur checkbox
        console.log("Erreur checkbox");
    }
}

/**
 * Get the Dropdown state and returns it.
 * @param {type} dropdown the dropdown to be checked
 * @returns {String} the state of the dropdown
 */
function getDropdownState(dropdown){
    type = dropdown.val();
    if(type === "1"){
        console.log("Animal");
        return "Animal";
    } else if(type === "3"){
        console.log("Livraison");
        return "Livraison";
    } else if(type === "2"){
        console.log("Incident");
        return "Incident";
    } else if(type === "Type"){
        console.log("Type");
        return "Type";
    }
}

$(document).ready(function () {
    remplirTableauHistorique("Type","false");
});