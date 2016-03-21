function hasClass(element, klass) {
    return ("" + element.className).indexOf(klass);

}
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function checkIdenticalPasswords() {
    var error = $("<div/>", {
        html: 'Passwords doesn*t match!',
        "class": "error"
    });
    var password = $('#password');
    var repeatedPassword = $('#repeatPassword');
    if (password.val() != repeatedPassword.val()) {
        password.parent().addClass('has-error');
        repeatedPassword.parent().addClass('has-error');
        if ($('#mismatch').length) {
            $('#mismatch').show();

        } else {
            error.attr('id', 'mismatch');
            error.insertAfter(password);
            ;

        }
        return true;
    } else {
        password.parent().removeClass('has-error');
        repeatedPassword.parent().removeClass('has-error');
        $('#mismatch').hide();
        return false;

    }
    ;

}
function checkEmailIdentity() {
    var email = $('#email');
    var emailValue = email.val();
    var error = $("<div/>", {
        html: 'User with such email already exists!!',
        "class": "error"

    });
    $.ajax({
        url: 'check-email',
        data: {
            email: emailValue,
        },

        success: function (response) {
            if (response == true) {
                email.parent().addClass('has-error');
                if ($('#emailError').length) {
                    $('#emailError').show();
                } else {
                    error.attr("id", "emailError");
                    error.insertAfter(email);
                }
                return true;

            } else if (validateEmail(email) && email.val() > 3) {
                email.parent().removeClass('has-error');
                email.parent().addClass('has-succes');
                $('#emailError').hide();

            } else {
                $('#emailError').hide();

            }
        }
    });
    return false;
}

function testAJAX() {
    var loginValue = $('#login').val();
    var emailValue = $('#email').val();
    var error = $("<div/>", {
        html: 'Already exists!!',
        "class": "error"
    });
    $("#loading_indicator").ajaxStart(function () {
        $(this).show();
    }).ajaxStop(function () {
        $(this).hide();
    });
    $.ajax({

        url: 'test',
        data: {
            email: emailValue,
            login: loginValue
        },
        dataType: "json",
        success: function (response) {
            if (response == true) {

                $('#test1').append(error);
                $('#test2').append(error);
            }
        }

    });
}

function checkLoginIdentity() {
    var login = $('#login');
    var loginValue = $('#login').val();
    var error = $("<div/>", {
        html: 'User with such login already exists!!',
        "class": "error",

    });
    $.ajax({
        url: 'check-login',
        data: {
            login: loginValue,
        },

        success: function (response) {
            if (response == true) {
                login.parent().addClass('has-error');
                if ($('#loginError').length) {
                    $('#loginError').show();

                } else {
                    error.attr("id", "loginError");
                    error.insertAfter(login);
                }

                return 1;
            } else if (hasClass(login, 'has-error') && login.val() > 2) {
                login.parent().removeClass('has-error');
                login.parent().addClass('has-success');
                $('#loginError').hide();
                return 0;
            } else {
                $('#loginError').hide();
                return 0;

            }
        }
    });
};
