function queue() {
    var priority1 = $('input[name=rpriority]:checked').val();
    var size1 =$('input[name=rsize]:checked').val();
    console.log("[INFO] Plane queueing request received priority:[" + priority1 + "] size:[" + size1 + "]");
    $.ajax({
        url: "/queue"
        , type: "get",
        dataType: "text",
        data: {
            priority: priority1
            , size: size1
        }
        , success: list
        , error: function (error) {
            console.log("[ERROR] " + error.responseText);
        }
    });
}

function dequeue() {
    console.log("[INFO] Plane dequeuing request received.");
    $.ajax({
        url: "/dequeue"
        , type: "get"
        , success: list
        , error: function (error) {
            console.log("[ERROR] " + error.responseText);
        }
    });
}

function list() {
  $.ajax({
      url: "/list"
      , type: "get"
      , success: function (response) {
          console.log("[INFO] List response: " + response);
          
          var acarray = JSON.parse(response).aircraft;
          $( "#planes").empty();
          if (acarray != null) {
            for (i = 0; i < acarray.length; i++) {
              $( "#planes" ).append("<tr><td>" + acarray[i].priority + "</td><td>" + acarray[i].size + "</td></tr>");
            }
          }
      }
      , error: function (error) {
          console.log("[ERROR] " + error.responseText);
      }
  });
}

$(function () {
    $("form").on('submit', function (e) {
       e.preventDefault();
    });
    
    $( "#queue" ).click(function() { queue(); });
    $( "#dequeue" ).click(function() { dequeue(); });
    $( "#list" ).click(function() { list(); });
});
