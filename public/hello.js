$(document).ready(function() {
    $.ajax({
        url: "http://192.168.1.105:8080/itinerarios/rest/greeting"
    }).then(function(data, status, jqxhr) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
       console.log(jqxhr);
    });
});
