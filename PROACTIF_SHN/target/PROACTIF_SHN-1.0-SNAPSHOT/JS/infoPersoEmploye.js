function remplirDonneesPerso() {
    var champLogin = $('#champ-login').val();
    var champPassword = $('#champ-password').val();

    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'remplir_informations_perso',
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        
        civilite = response.civilite;
        updateCivilite(civilite);
        $('#nomEmploye').html(response.nom);
        $('#prenomEmploye').html(response.prenom);
        $('#dateNaissanceEmploye').html(response.dateNaissance);
        $('#emailEmploye').html(response.email);
        $('#telephoneEmploye').html(response.tel);
        $('#adresseEmploye').html(response.adresse);
        $('#codePostalEmploye').html(response.codePostal);
        $('#villeAdresseEmploye').html(response.ville);
       
    }).fail( function (error) { // Appel KO => erreur a gérer
         console.log("Fail remplir_informations_perso");          
        // Popup avec message d'erreur :
        alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
    });
}

function updateCivilite(civilite){
    console.log(civilite);
    if(civilite === 'M'){
        $('#ongletMr').attr("class","nav-item nav-link active");
        $('#ongletMme').attr("class","nav-item nav-link disabled");
        $('#ongletMlle').attr("class","nav-item nav-link disabled");
    }
    else if(civilite === 'Mme'){
        $('#ongletMr').attr("class","nav-item nav-link disabled");
        $('#ongletMme').attr("class","nav-item nav-link active");
        $('#ongletMlle').attr("class","nav-item nav-link disabled");
    }
    else if(civilite === 'Mlle'){
        $('#ongletMr').attr("class","nav-item nav-link disabled");
        $('#ongletMme').attr("class","nav-item nav-link disabled");
        $('#ongletMlle').attr("class","nav-item nav-link active");
    }
}

$(document).ready(function () {
    remplirDonneesPerso();
});




