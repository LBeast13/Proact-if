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

        if (response.type_interv === "Intervention Animal") {   // Animal
            // Affichage et masquage des éléments
            $('#animalConteneur').removeAttr("hidden");
            $('#objetConteneur').attr("hidden",true);
            $('#entrepriseConteneur').attr("hidden",true);

            $('#animal').html(response.animal_interv);
            
            $('#radioAnimal').attr("checked",true);
            
        } else if (response.type_interv === "Intervention Livraison") { // Livraison
            // Affichage et masquage des éléments
            $('#animalConteneur').attr("hidden",true);
            $('#objetConteneur').removeAttr("hidden");
            $('#entrepriseConteneur').removeAttr("hidden");
            
            $('#objet').html(response.objet_interv);
            $('#entreprise').html(response.entreprise_interv);
            
            $('#radioLivraison').attr("checked",true);
            
        } else if (response.type_interv === "Intervention Incident") { // Incident
            // Affichage et masquage des éléments
            $('#animalConteneur').attr("hidden",true);
            $('#objetConteneur').attr("hidden",true);
            $('#entrepriseConteneur').attr("hidden",true);

            $('#radioIncident').attr("checked",true);
        }
 
        // Données indépendentes du type d'intervention
        $('#codeClientIntervention').html(response.numero_client);
        $('#dateDemandeIntervention').html(response.date_demande_interv);
        $('#trajetIntervention').html(response.trajet_interv);
        $('#adresseIntervention').html(response.adresse_interv);
        $('#descriptionIntervention').html(response.description_interv);
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_intervention_en_cours");
        if (error.status === 403) {
            window.location = "../../index.html";
        } else {
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        }
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
    remplirInterventionEnCours();
});



