/*!
 * JavaScript Library v1.0.0
 *
 * Copyright 2014, 2014 HostMe Foundation, Inc. and other contributors
 * Released under the MIT license
 *
 * Date: 2014-07-27
 */
$('a[href*=#]:not([href=#myCarousel])').click(
    function () {
        if (location.pathname.replace(/^\//, '') == this.pathname
            .replace(/^\//, '')
            && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name='
                + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top - 50
                }, 1000);
                return false;
            }
        }
    });
function createOverlay() {
    var docHeight = $(document).height();

    $("<div id='overlay'></div>").appendTo("#someElement").height(
        docHeight).css({
            'opacity': 0.4,
            'position': 'absolute',
            'top': 0,
            'left': 0,
            'background-color': 'black',
            'width': '25%',
            'height': '25%',
            'z-index': 5000
        });
}