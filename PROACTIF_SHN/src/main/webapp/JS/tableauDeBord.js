var googleMapInstance = null;

function makeInfoWindow(title) {
    return new google.maps.InfoWindow({
        content: '<div>Information: ' + title + '</div>'
    });
}

function attachInfoWindow(marker, infowindow, htmlDescription) {
    marker.addListener('click', function () {

        infowindow.setContent(htmlDescription);
        infowindow.open(googleMapInstance, this);
    });
}

function initMap() {
    console.log("TODO : Régler le problème de la liste d'intervention vide dans action")

    googleMapInstance = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 45.7601424, lng: 4.8961779},
        zoom: 13
    });

    var infowindow = makeInfoWindow('');

    var marker = new google.maps.Marker({
        map: googleMapInstance,
        position: {lat: 45.782122, lng: 4.872735},
        title: "Département IF, INSA de Lyon",
        icon: {url: './image/bird.png', scaledSize: new google.maps.Size(32, 32)}
    });

    marker.addListener('click', function () {

        infowindow.setContent('<div>Information: ' + marker.title + '</div>');
        infowindow.open(googleMapInstance, marker);
    });

    var marker2 = new google.maps.Marker({
        map: googleMapInstance,
        position: {lat: 45.782592, lng: 4.878238},
        title: "Entrée principale, INSA de Lyon",
        icon: {url: './image/snake.png', scaledSize: new google.maps.Size(32, 32)}
    });

    marker2.addListener('click', function () {

        infowindow.setContent('<div>Information: ' + marker2.title + '</div>');
        infowindow.open(googleMapInstance, marker2);
    });

    // Simuler un appel AJAX (attente 5s)
    /*setTimeout(
            generateMarkers,
            5000
            );*/
    
    // Appel Ajax
    getEmplacementsAjax();
}

/**
 * Fonction Ajax qui met à jours la fiche de l'intervention prête à Être clôturée
 * @returns {undefined}
 */
function getEmplacementsAjax() {
    $.ajax({
        url: '../../ActionServlet',
        method: 'POST',
        data: {
            todo: 'recuperer_emplacements_interventions'
        },
        dataType: 'json'
    }).done(function (response) { // Appel OK
        console.log("TODO : Customiser fenetre pop up et image");
        // Petite popup Google Maps
        var infowindow = makeInfoWindow('');
        iconAnimal_echec = {url: '../../assets/img/animal.png', scaledSize: new google.maps.Size(32, 32)};
        iconLivraison_echec = {url: '../../assets/img/livraison.png', scaledSize: new google.maps.Size(32, 32)};
        iconIncident_echec = {url: '../../assets/img/incident.png', scaledSize: new google.maps.Size(32, 32)}
        
        iconAnimal_succes = {url: '../../assets/img/animalOK.png', scaledSize: new google.maps.Size(32, 32)};
        iconLivraison_succes = {url: '../../assets/img/livraisonOK.png', scaledSize: new google.maps.Size(32, 32)};
        iconIncident_succes = {url: '../../assets/img/incidentOK.png', scaledSize: new google.maps.Size(32, 32)}
        
        iconAnimal_enCours = {url: '../../assets/img/animalCours.png', scaledSize: new google.maps.Size(32, 32)};
        iconLivraison_enCours = {url: '../../assets/img/livraisonCours.png', scaledSize: new google.maps.Size(32, 32)};
        iconIncident_enCours = {url: '../../assets/img/incidentCours.png', scaledSize: new google.maps.Size(32, 32)}
        
        
        interventions = response.interventions;

        interventions.forEach(function (element) {
            
            coordParts = element.coordInterv.split(",");
            var position = {lat: parseFloat(coordParts[0]), lng: parseFloat(coordParts[1])};
            iconAnimal = null;
            iconLivraison = null;
            iconIncident = null;
            
            type = element.type;          
            numeroInterv = element.numero;
            description = element.description;
            statut = element.statut;
            
            if(statut === "Succès"){
                iconAnimal = iconAnimal_succes;
                iconLivraison = iconLivraison_succes;
                iconIncident = iconIncident_succes;
            } else if(statut === "Echec"){
                iconAnimal = iconAnimal_echec;
                iconLivraison = iconLivraison_echec;
                iconIncident = iconIncident_echec;
            } else{
                iconAnimal = iconAnimal_enCours;
                iconLivraison = iconLivraison_enCours;
                iconIncident = iconIncident_enCours;
            }
            
            if(type === "Animal"){
                var marker = new google.maps.Marker({
                    map: googleMapInstance,
                    position: {lat: position.lat, lng: position.lng},
                    title: 'Intervention Animale',
                    icon: iconAnimal
                });
            } else if(type === "Livraison"){
                var marker = new google.maps.Marker({
                    map: googleMapInstance,
                    position: {lat: position.lat, lng: position.lng},
                    title: 'Intervention Livraison',
                    icon: iconLivraison
                });
            } else if(type === "Incident"){
                var marker = new google.maps.Marker({
                    map: googleMapInstance,
                    position: {lat: position.lat, lng: position.lng},
                    title: 'Intervention Incident',
                    icon: iconIncident
                });
            }
            
            attachInfoWindow( marker, infowindow,
                '<div><strong><a href="detail-intervention.html?numInterv='+ numeroInterv +'" >Intervention #' + numeroInterv + '</a></strong><br/>Description : ' + description +' <br/></div>'
                );
        });
        
    }).fail( function (error) { // Appel KO => erreur a gérer
        console.log("Fail recuperer_emplacements_interventions");          
        if (error.status === 403) {
            window.location = "../../index.html";
        } else {
            alert('Erreur lors de l\'appel: HTTP Code ' + error.status + ' ~ ' + error.statusText + ' ~ ' + error.getResponseHeader('Content-Type'));
        }
    });
}

function generateMarkers() {

    // Petite popup Google Maps
    var infowindow = makeInfoWindow('');

    var position = {lat: 45.782122, lng: 4.872735};

    for (var i = 0; i < 100; i++) {

        var iconImage = null; // marker par défaut
        if (i % 2 === 0) {
            // image pour marker personnalisé
            iconImage = {url: './image/UnderConstruction.png', scaledSize: new google.maps.Size(32, 32)}
        }

        var marker = new google.maps.Marker({
            map: googleMapInstance,
            position: {lat: position.lat + (Math.random() - 0.5) / 10.0, lng: position.lng + 2 * (Math.random() - 0.5) / 10.0},
            title: 'Endroit #' + i,
            icon: iconImage
        });

        attachInfoWindow(
                marker, infowindow,
                '<div><strong><a href="./endroit.html?' + i + '">Endroit #' + i + '</a></strong><br/>Ceci est l\'endroit charmant numéro ' + i + '<br/>' + 'Incroyable !' + '</div>'
                );
    }

}


