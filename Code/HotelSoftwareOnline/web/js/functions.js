$(function()
    {
        $("#slides").slides({
            preload: true,
            play: 5000,
            pause: 2500,
            hoverPause: true,
            effect: "fade",
            animationStart: function(current){
                $('.caption').animate({
                    bottom:-35
                },100); 
            },
            animationComplete: function(current){
                $('.caption').animate({
                    bottom:0
                },200);
            },
            slidesLoaded: function() {
                $('.caption').animate({
                    bottom:0
                },200);
            }
        });
    });

$(document).ready(function(e) {
    $(".datepicker").datepicker($.datepicker.regional["en-GB"]);
    $("#startDate").change(checkPeriod);
    $("#endDate").change(checkPeriod);
});

function checkPeriod() {
    if ($("#startDate").val() != "" && $("#endDate").val() != "") {
        $("#reservationContent").css("display", "block");
    }
}

function toggle(id)
{
    $("#"+id).toggle();
}

// Klassen usw. die mit JS manipuliert werden sollen: error (rot,...), periode, 
// reservationContent, commentary, reservationItems, reservationItem
// collapse, addReservationItem(button), reservationNext (button), remove (button)
// include des reservation item erst, wenn die periode ausgew�hlt wurde (ajax, js)!
// beim Laden von reservation.xml checkPeriod() aufrufen!