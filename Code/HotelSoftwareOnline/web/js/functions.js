$(function()
    {
        $("#slides").slides({
            preload: true,
            play: 5000,
            pause: 5000,
            hoverPause: true,
            effect: "fade",
			fadeSpeed: 700,
            animationStart: function(current){
                $('.caption').animate({
                    bottom:-35
                },300); 
            },
            animationComplete: function(current){
                $('.caption').animate({
                    bottom:0
                },300);
            },
            slidesLoaded: function() {
                $('.caption').animate({
                    bottom:0
                },300);
            }
        });
    });


$(document).ready(function(e) {
    $(".datepicker").datepicker($.datepicker.regional["en-GB"]);
    $(".startDate").change(checkPeriod);
    $(".endDate").change(checkPeriod);
	
	//collapsible management
	//$('.collapsible').collapsible();
});

function checkPeriod() {
    if ($(".startDate").val() != "" && $(".endDate").val() != "") {
        $(".reservationContent").css("display", "block");
    }
}

// Klassen usw. die mit JS manipuliert werden sollen: error (rot,...), periode, 
// reservationContent, commentary, reservationItems, reservationItem
// collapse, addReservationItem(button), reservationNext (button), remove (button)
// include des reservation item erst, wenn die periode ausgew�hlt wurde (ajax, js)!
// beim Laden von reservation.xml checkPeriod() aufrufen!