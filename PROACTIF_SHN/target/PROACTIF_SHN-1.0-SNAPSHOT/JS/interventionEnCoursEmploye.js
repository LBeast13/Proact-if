numIntervention = null;

/**
 * Fonction Ajax qui met à jours la fiche de l'intervention en cours
 * @returns {undefined}
 */
function remplirInterventionEnCours() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_intervention_en_cours'
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        numIntervention = response.num_interv;
        
        $('#typeIntervention').html(response.type_interv);
        $('#detailTypeIntervention').html(response.detail_type_interv);
        $('#codeClientIntervention').html(response.numero_client);
        $('#dateDemandeIntervention').html(response.date_demande_interv);
        $('#trajetIntervention').html(response.trajet_interv);
        $('#adresseIntervention').html(response.adresse_interv);
        $('#descriptionIntervention').html(response.description_interv);
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_intervention_en_cours");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

/**
 * Fonction de click sur le bouton cloturer
 * @returns {undefined}
 */
function cloturerIntervention(){
    window.location = "cloture-intervention.html?numInterv=" + numIntervention;
}

$(document).ready(function () {
    console.log("TODO : gérer les affichages des cases en fonction du type d'intervention");
    remplirInterventionEnCours();
});



