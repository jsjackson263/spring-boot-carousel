
$(document).ready(function(){
	//dynamically add photographs to bootstrap carousel
	//var baseurl = '/api/image/';
	
	for(var i=0 ; i< imageurls.length ; i++) {
		console.log('Value of imageurls[' + i + ']: ' + imageurls[i]);
	    $('<div class="item"><img src="'+imageurls[i]+'"><div class="carousel-caption"></div>   </div>').appendTo('.carousel-inner');
	    $('<li data-target="#photo-carousel" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators')

	  }
	  $('.item').first().addClass('active');
	  $('.carousel-indicators > li').first().addClass('active');
	  $('#photo-carousel').carousel();
	  
});               
                
                
