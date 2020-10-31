var xmlHttp;

function updateOutput(inputString) {
    if (inputString.length === 0) {
        document.getElementById("userNameMessage").innerHTML = "";
        return;
    }
    try {
        if (window.XMLHttpRequest)
            xmlHttp = new XMLHttpRequest();
        else if (window.ActiveXObject)
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        if (!xmlHttp || xmlHttp === null) {
            return;
        }
        var url = "proveraKorisnickogImena?korisnickoIme=" + inputString; // Here, I have mapped servlet as "validate".
        xmlHttp.onreadystatechange = StateChanged;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } catch (e) {
        document.getElementById("userNameMessage").innerHTML = "An error occured";
    }
}
function StateChanged() {
    if ((xmlHttp.readyState === 4) && (xmlHttp.status === 200)) {
        document.getElementById("userNameMessage").innerHTML = xmlHttp.responseText;
    }
}

var password = document.getElementById("password")
        , password_check = document.getElementById("password_check");

function validatePassword() {
    if (password.value !== password_check.value) {
        password_check.setCustomValidity("Unete lozinke se ne podudaraju.");
    } else {
        password_check.setCustomValidity('');
    }
}

password.onchange = validatePassword;
password_check.onkeyup = validatePassword;

