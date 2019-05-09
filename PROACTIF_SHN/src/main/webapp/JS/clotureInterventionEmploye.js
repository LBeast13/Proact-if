$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
};

/**
 * Fonction Ajax qui met à jours la fiche de l'intervention prête à Être clôturée
 * @returns {undefined}
 */
function remplirInfoCloture() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_intervention_en_cours',
            numero_interv: $.urlParam('numInterv')
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        $('#numInterv').html(response.num_interv);
        $('#numClient').html(response.numero_client);
        $('#numEmploye').html(response.num_employe);
        $('#dateDemande').html(response.date_demande_interv);
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_intervention_a_cloturer");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

/**
 * 
 * @returns {undefined}
 */
function cloturerIntervention(){
    if(checkChampsValides()){   // Si les champs sont valides
        
    }
    else{  // Si les champs sont incorrects
        $('#myModal').modal('show');    // Affiche la Pop-Up d'erreur

        setTimeout(function () {  // Fermeture au bout de 5s
            $('#myModal').modal('hide');
        }, 5000);
    }
}

function checkChampsValides(){
    return false;
}

$(document).ready(function () {
    console.log("TODO : gérer les affichages des cases en fonction du type d'intervention");
    
    // Click bouton valider
    $('#bouton-valider').on('click', function () { 
        cloturerIntervention();
    });
    
    // Click bouton annuler
    $('#bouton-annuler').on('click', function () { 
        window.location = "intervention-en-cours.html"
    });
    
    remplirInfoCloture();
});






