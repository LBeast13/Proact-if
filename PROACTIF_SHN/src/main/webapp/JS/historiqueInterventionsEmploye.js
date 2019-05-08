function ajouter(intervention){
    var row = "<tr>\n\
              <th scope=\"row\">" + intervention.numero + "</th>\n\
              <td>"+ intervention.type + "</td>\n\
              <td>"+ intervention.date + "</td>\n\
              <td>"+ intervention.client + "</td>\n\
              <td>"+ intervention.statut + "</td>\n\
              </tr>";       
    console.log(row);
    
    $("#liste-personnes").append(row);
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
    remplirTableauHistorique();
});






