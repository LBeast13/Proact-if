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
            todo: 'remplir_detail_intervention',
            numero_interv: $.urlParam('numInterv')
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        console.log(response.type_interv);
        
        if (response.type_interv === "Intervention Animal") {   // Animal
            // Affichage et masquage des éléments
            $('#conteneurAnimal').removeAttr("hidden");
            $('#conteneurObjet').attr("hidden",true);
            $('#conteneurEntreprise').attr("hidden",true);

            $('#animal').html(response.animal_interv);
            
            $('#radioAnimal').attr("checked",true);
            
        } else if (response.type_interv === "Intervention Livraison") { // Livraison
            // Affichage et masquage des éléments
            $('#conteneurAnimal').attr("hidden",true);
            $('#conteneurObjet').removeAttr("hidden");
            $('#conteneurEntreprise').removeAttr("hidden");
            
            $('#objet').html(response.objet_interv);
            $('#entreprise').html(response.entreprise_interv);
            
            $('#radioLivraison').attr("checked",true);
            
        } else if (response.type_interv === "Intervention Incident") { // Incident
            // Affichage et masquage des éléments
            $('#conteneurAnimal').attr("hidden",true);
            $('#conteneurObjet').attr("hidden",true);
            $('#conteneurEntreprise').attr("hidden",true);

            $('#radioIncident').attr("checked",true);
        }   
        
        // Données indépendantes du type d'intervention
        $('#numInterv').html(response.numero_interv);
        $('#numClient').html(response.numero_client);
        $('#numEmploye').html(response.numero_employe);
        $('#statut').html(response.statut_interv);
        $('#dateDemande').html(response.date_demande);
        $('#dateCloture').html(response.date_cloture);
        $('#description').html(response.description_interv);
        $('#commentaire').html(response.commentaire_interv);
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_detail_intervention_employe");          
        // Popup avec message d'erreur :
        if(error.status === 403){
            window.location = "connexion-client.html";
        }
        else{
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        }
    });
}

$(document).ready(function () {
    // Click bouton retour
    $('#bouton-retour').on('click', function () { 
        window.location = "historique-interventions-client.html";
    });
    
    remplirDetailIntervention();
});


