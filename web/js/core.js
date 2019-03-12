document.onreadystatechange = function () {

    document.getElementById("search_input").onkeypress = function () {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                // document.getElementById("demo").innerHTML = this.responseText;
                // alert("response: " + this.responseText);
                document.getElementById("search_result").innerHTML = this.responseText;
            } else {
                document.getElementById("search_result").innerHTML = "Problem";
            }
        };
        xhttp.open("GET", "ricerca", true);
        xhttp.send();
    };

    document.getElementById("search_input").onclick = function () {

    };

};
