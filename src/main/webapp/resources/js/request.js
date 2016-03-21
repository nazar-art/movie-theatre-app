function sendRequest(id, element) {
    var loader = $("<img/>", {
        src: "resources/images/ajax-loader.gif"
    });
    var successRequest = $("<span/>", {
        html: "Sent",
        "class": "label label-primary"
    });
    var failRequest = $("<span/>", {
        html: "Cannot be sent",
        "class": "label label-danger"
    });

    $.ajax({
        url: 'response',
        data: {
            "id": id,
        },
        beforeSend: function () {
            console.log('start');
            element.style.display = "none";
            loader.insertAfter(element);

        },

        success: function (response) {
            element.nextSibling.style.display = "none";
            if (response) {
                console.log('true');
            } else {
                failRequest.insertAfter(element);

            }
        },

    });
    /*
     *
     * Some selectors for parsing over c:url
     *
     * var a = $('.btn-success').parent().siblings('td:has(a)').eq(0); var b =
     * a.children().attr('href');
     *
     *
     * var a = $('.btn-success').parent().siblings('td:has(a)').eq(1); var b =
     * a.children().attr('href');
     */
}
