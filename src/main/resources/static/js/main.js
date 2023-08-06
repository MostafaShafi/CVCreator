function emailValidation(emailAddress) {
    if (!emailAddress.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
        alert("Email format is not validate!")
    }
}

function checkPasswordFormat() {
    pass_format = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    var pass = document.getElementById("password");

    if (!pass.value.match(pass_format)) {
        alert("Password should be at least 8 character " +
            "and contains digits, one lowercase letter, one uppercase letter and one underscore.");
    }
}

function checkPhoneNumber(phone) {
    regex = /^\d{11}$/;
    if (phone.value.match(regex) == null)
        alert("Please fill Phone with just numbers with length 11!");
    /*if (phone.value.length != 11)
        alert("Please fill Phone with just 11 character!");*/
}

function checkGradeRange(grade) {
    if (grade.value < 10.0 || grade.value > 20.0) {
        alert("Please write a correct number(Range 10 to 20!");
        grade.value = null;
    }
}

function hideShowPassword() {
    passwordType = document.getElementById("password").getAttribute("type") === "password" ? "text" : "password";
    document.getElementById("password").setAttribute("type", passwordType);
}

function makeExperienceEndDateRequired() {
    document.getElementById("exp-end-date").required = !document.getElementById("is-until-work").checked;
}

function showErrorModal() {
    errorMessage = document.getElementById("skills-error-message");
    if (errorMessage.innerHTML != null && errorMessage.innerHTML.length > 0) {
        alert(errorMessage.innerHTML);
    }
}

function makePDF() {
    const element = document.getElementById('main');
    const personName = document.getElementById('PersonName');
    var opt = {filename: personName.innerText + ' - Resume.pdf'};
    html2pdf().set(opt).from(element).save();
}
