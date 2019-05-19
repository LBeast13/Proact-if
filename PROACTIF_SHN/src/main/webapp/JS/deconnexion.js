/**
 * Fonction de deconnexion pour un client ou un employé
 * @returns {undefined}
 */
function deconnexion(){
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'deconnexion'
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail remplir_detail_intervention_employe");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}