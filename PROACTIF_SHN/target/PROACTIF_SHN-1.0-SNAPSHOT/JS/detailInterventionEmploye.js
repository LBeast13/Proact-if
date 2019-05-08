$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
};

/**
 * Fonction Ajax qui met à jours la fiche de détail de l'intervention
 * @returns {undefined}
 */
function remplirDetailIntervention() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_detail_intervention_employe',
            numero_interv: $.urlParam('numInterv')
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        $('#numInterv').html(response.numero_interv);
        $('#numClient').html(response.numero_client);
        $('#numEmploye').html(response.numero_employe);
        $('#statut').html(response.statut_interv);
        $('#objet').html(response.objet_interv);
        $('#entreprise').html(response.entreprise_interv);
        $('#dateDemande').html(response.date_demande);
        $('#dateCloture').html(response.date_cloture);
        $('#description').html(response.description_interv);
        $('#commentaire').html(response.commentaire_interv);
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_detail_intervention_employe");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

$(document).ready(function () {

    remplirDetailIntervention();
});
