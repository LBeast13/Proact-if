/**
 * Met en forme la ligne représentant l'intervention et l'ajoute au tableau du DOM
 * @param {type} intervention qu'on doit ajouter dans le tableau
 * @returns {undefined}
 */
function ajouter(intervention){
    var row = "<tr><th scope=\"row\" onclick='voirDetail("+intervention.numero+")'>" + intervention.numero 
            + "</th><td>"+ intervention.type + "</td><td>"
            + intervention.date + "</td><td>"
            + intervention.client + "</td><td>"
            + intervention.statut + "</td></tr>";       
    
    $("#tableBody").append(row);
}

/**
 * Fonction de click sur le numéro d'intervention.
 * @param {type} numeroIntervention le paramètre ajouté dans l'URL
 * @returns {undefined}
 */
function voirDetail(numeroIntervention){
    window.location = "detail-intervention.html?numInterv=" + numeroIntervention;
}

/**
 * Fonction Ajax qui met à jours le tableau des interventions de 
 * l'employé connecté.
 * @returns {undefined}
 */
function remplirTableauHistorique() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_historique_interventions_employe',
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        $('#tableBody').empty();
        var interventions = response.interventions;
        
        for (var i = 0; i < interventions.length; i++) {
            ajouter(interventions[i]);
        }
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_historique_interventions_employe");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

$(document).ready(function () {
    console.log("TODO : Résoudre le double chargement de la page")
    
    remplirTableauHistorique();
});






