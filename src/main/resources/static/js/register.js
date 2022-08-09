document.addEventListener("DOMContentLoaded", () => {

    const passwordInput = document.getElementById("password");
    const confirmationInput = document.getElementById("confirm-password");
    const match = document.getElementById("match-err")

    const form = document.getElementById("registration-form")

    form.addEventListener( 'submit', (submitEvent) => {

        submitEvent.preventDefault()

        if ( passwordInput.value == confirmationInput.value ){            
            
            match.style.display = "none"

            try {
    
                fetch( "/register", {
                    method: "POST",
                    mode: 'cors',
                    cache: 'no-cache',
                    redirect: "follow",
                    headers: {
                        "Content-Type": 'application/json'
                    },
                    body: JSON.stringify({
                        username: form.elements["username"].value,
                        password: form.elements["password"].value
                    })
                })/* .then( res => res.json())
                .then( resJson => {console.log(resJson);}) */
                .then( res => {

                    if (res.ok) {
                        location.href="/app"
                    }

                    document.getElementById("user-error").style.display = "block"

                    console.log(res);

                })
                .catch( error => {
                    console.log(error.message);
                })

            } catch (e) {
                console.error( e.message )
            }

        } else {
            match.style.display = "block"
        }

    })

    function passwordHasLowercase( password ) {
        const lowerCaseLetters = /[a-z]/g;
        return password.match(lowerCaseLetters) ? true : false
    }

    function passwordHasUppercase( password ) {
        const upperCaseLetters = /[A-Z]/g;
        return password.match(upperCaseLetters) ? true : false
    }

    function passwordHasNumber( password ) {
        const numbers = /[0-9]/g;
        return password.match(numbers) ? true : false
    }

    function passwordHasLength( password ) {
        return password .length >= 8 ? true : false
    }

    const letter = document.getElementById("letter");
    const capital = document.getElementById("capital");
    const number = document.getElementById("number");
    const length = document.getElementById("length");

    // When the user clicks on the password field, show the message box
    passwordInput.onfocus = function() {
    document.getElementById("message").style.display = "flex";
    }

    // When the user clicks outside of the password field, hide the message box
    passwordInput.onblur = function() {
    document.getElementById("message").style.display = "none";
    }

    // When the user starts to type something inside the password field
    passwordInput.onkeyup = function() {

        const passwordValue = passwordInput.value

        // Validate lowercase letters
        if(passwordHasLowercase(passwordValue)) {
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }

        // Validate capital letters
        if(passwordHasUppercase(passwordValue)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }

        // Validate numbers
        if(passwordHasNumber(passwordValue)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }

        // Validate length
        if(passwordHasLength(passwordValue)) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }
    }

    const confMessage = document.getElementById("conf-message")
    const matchDetail = document.getElementById("match")

    // When the user clicks on the match field, show the message box
    confirmationInput.onfocus = function() {
        confMessage.style.display = "flex";
    }

    // When the user clicks outside of the match field, hide the message box
    confirmationInput.onblur = function() {
        confMessage.style.display = "none";
    }
    
    confirmationInput.onkeyup = function () {

        passValue = passwordInput.value
        confValue = confirmationInput.value

        if ( passValue === confValue ) {
            matchDetail.classList.remove("invalid")
            matchDetail.classList.add("valid")
            match.style.display = "none"
        } else {
            matchDetail.classList.remove("valid")
            matchDetail.classList.add("invalid")
        }

    }

})