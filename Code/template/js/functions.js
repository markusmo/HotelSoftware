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
});