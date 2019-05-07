function connexion() {
    var champLogin = $('#champ-login').val();
    var champPassword = $('#champ-password').val();

    //$('#message').html('Connexion en cours...');

    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'connecterEmploye',
            login: champLogin,
            password: champPassword
        },
        dataType: 'json'
    }).done(function (data) {
        // ici votre code...
        // 
        // si connexion ok, aller sur la page suivante :
        window.location.href = "./info-perso-employe.html";
        // si connexion pas ok afficher un texte dans la div message :
        console.log('Erreur de connexion');
        // $('#message').html('Echec de la connexion');
    }).fail(function(response){// Appel KO
        console.log('Erreur');
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


