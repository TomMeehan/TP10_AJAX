
$(document).ready(function () {
    console.log("ready");
    drawCodeTable();
});

function drawCodeTable() {
    $.ajax({
        url : "../CodesInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    console.log("drawing...");
                    var template = $('#codesTemplate').html();
                    var processedTemplate = Mustache.to_html(template,result);
                    $('#codesTable').html(processedTemplate);
                }
    });
}

function addCode() {
    $.ajax({
        url : "../AddCode",
        data: $("#codeForm").serialize(),
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    console.log("adding...");
                    drawCodeTable();
                }
    });
}

function deleteCode(code) {
    $.ajax({
        url : "../DeleteCode",
        data: { "code" : code },
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    console.log("deleting...");
                    drawCodeTable();
                }
    });
}

function showError(xhr, status, message) {
    alert(JSON.parse(xhr.responseText).message);
}

