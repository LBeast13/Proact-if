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
    setTimeout(
            generateMarkers,
            5000
            );
    
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
       console.log(response.coordInterv);
        
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


