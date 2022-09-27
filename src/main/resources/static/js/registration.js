var passwordField = document.getElementById("password");

var passwordConfirmField = document.getElementById("passwordConfirm");

var form = document.getElementById("registerForm")

function validatePasswordMatch() {
    if (passwordField.value != passwordConfirmField.value) {
        alert("Passwords do not match. Try again!");
    } else {
        form.submit();
    }
}