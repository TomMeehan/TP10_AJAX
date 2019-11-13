$(document).ready(function () {
    drawCodeTable();
})

function drawCodeTable() {
    $.ajax({
        url : "codesInJSON",
        dataType : "json",
        error : showError,
        success : 
                function(result) {
                    
                    var table = $("#codes");
                    table.append("<p>Hello</p>");
                    /*$.each(result.codes,
                        function(codeIndex) {
                            var code = result.codes[codeIndex].code;
                            var rate = result.codes[codeIndex].rate;
                            
                            
                        }
                    );*/
                }
    })
}
