function ajouter(intervention){
    var row = "<tr><th scope=\"row\">" + intervention.numero 
            + "</th><td>"+ intervention.type + "</td><td>"
            + intervention.date + "</td><td>"
            + intervention.client + "</td><td>"
            + intervention.statut + "</td></tr>";       
    
    $("#tableBody").append(row);
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
    Console.log("TODO : Résoudre le double chargement de la page")
    
    remplirTableauHistorique();
});






