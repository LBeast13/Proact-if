function connexion() {
    var champLogin = $('#champ-login').val();
    var champPassword = $('#champ-password').val();

    //$('#message').html('Connexion en cours...');

    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'connecterClient',
            login: champLogin,
            password: champPassword
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        if(response.connexion){     // Connexion ok, aller sur la page d'accueil
            
            setTimeout(function(){  // Redirection avec délai de 3s
                window.location = "info-perso-client.html";
            },2000);
        }
        else{   // Connexion pas ok POP UP d'erreur
            $('#myModal').modal('show');    // Affiche la Pop-Up d'erreur
            
            setTimeout(function(){  // Fermeture au bout de 5s
                $('#myModal').modal('hide');
            },8000);    
        }
    }).fail( function (error) { // Appel KO => erreur a gérer
                   
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

$(document).ready(function () {
    // ajout d'un "handler" sur le clic du bouton de connexion
    $('#bouton-connexion').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Se Connecter"');
        // appel de la fonction connexion
        connexion();
    });
});





