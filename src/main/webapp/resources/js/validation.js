$(document).ready(
    function () {
        jQuery.validator.setDefaults({
            errorPlacement: function (error, element) {
                // if the input has a prepend or append element, put
                // the
                // validation msg after the parent div
                if (element.parent().hasClass('input-prepend')
                    || element.parent().hasClass('input-append')) {
                    error.insertAfter(element.parent());
//						element.parent().addClass('has-error');
                    // else just place the validation message
                    // immediatly
                    // after the input
                } else {
                    error.insertAfter(element);
//						element.parent().addClass('has-error');

                }
            },
            errorElement: "small", // contain the error msg in a
            // small tag
            highlight: function (element) {
                $(element).parent().addClass('has-error');


                // add
                // the
                // Bootstrap
                // error
                // class
                // to
                // the
                // control
                // group
            },
            success: function (element) {
                $(element).closest('.control-group').removeClass('error');
                element.parents().eq(0).removeClass('has-error');
                element.parents().eq(0).addClass('has-success');

                // remove
                // the
                // Boostrap
                // error
                // class
                // from
                // the
                // control
                // group
            }
        });
        $('#loginForm').validate({
            rules: {
                j_username: {
                    minlength: 3,
                    required: true
                },
                j_password: {
                    minlength: 3,
                    required: true
                }
            }
        });

        $('#registrationForm').validate({
            rules: {
                lastName: {
                    minlength: 2,
                    required: true
                },
                firstName: {
                    minlength: 2,
                    required: true
                },

                login: {
                    minlength: 3,
                    required: true
                },
                password: {
                    minlength: 3,
                    required: true
                },
                repeatPassword: {
                    minlength: 3,
                    required: true
                },
                email: {
                    required: true,
                    email: true
                }
            }
        });
    });
