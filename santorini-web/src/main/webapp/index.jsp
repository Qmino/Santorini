<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Santorini travel agency</title>
    <link rel="stylesheet" type="text/css" href="santorini.css">

</head>

<body>

<div id="div_main">

    <div id="div_header">
        <h3>Santorini Travel Agency</h3>
    </div>

    <div id="div_book">
        <input type="radio" name="bookmode" value="new" checked>Book a trip<br>
        <input type="radio" name="bookmode" value="edit">Edit a booking
        <input type="hidden" id="loadedbookingid">        
        &nbsp;<b>Id:</b> <input type="text" id="bookingid" size="10">
        <button id="button_loadbooking" type="button">Load</button>
        
    </div>

    <div id="div_pricepaid">
        <h3>Paid</h3>
        <b>Amount: </b> <input type="text" id="pricepaid" size="6">
    </div>

    <div id="div_trip">
        <div id="div_selecttrip">
            <h3>Choose a trip</h3>
            <select id="select_trip" size="4"></select>
        </div>
        <div id="div_tripdetails">
            <table class="table_trip"> <!--Identify by class, because we're going to clone this table-->
                <tr>
                    <td><b>Name:</b></td>
                    <td><span class="tripName"></span></td>
                </tr>
                <tr>
                    <td><b>Price:</b></td>
                    <td><span class="price"></span></td>
                </tr>
                <tr>
                    <td><b>Places left:</b></td>
                    <td><span class="availablePlaces"></span></td>
                </tr>
                <tr>
                    <td><b>Summary:</b></td>
                    <td><span class="tripSummary"></span></td>
                </tr>
                <tr>
                    <td><b>Description:</b></td>
                    <td><span class="tripDescription"></span></td>
                </tr>
                <tr>
                    <td><b>Start:</b></td>
                    <td><span class="startDate"></span></td>
                </tr>
                <tr>
                    <td><b>End:</b></td>
                    <td><span class="endDate"></span></td>
                </tr>
            </table>
        </div>
    </div>

    <div id="div_traveler">
        <h3>Choose a traveler</h3>

        <input type="radio" name="travelermode" value="new" checked>New traveler<br>
        <input type="radio" name="travelermode" value="load">Load an existing traveler
        &nbsp;<b>Id:</b> <input type="text" id="travelerid" size="10">
        <button id="button_loadtraveler" type="button">Load</button>
		
        <div id="div_traveler_bottom">
            <div id="div_travelerdetails">
                <form id="form_traveler">
                    <h5>Traveler Details</h5>
                    <input type="hidden" id="loadedtravelerid" name="id">
                    <table>
                        <tr>
                            <td>First Name:</td>
                            <td><input type="text" name="firstName"></td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" name="lastName"></td>
                        </tr>
                        <tr>
                            <td>Passport Number:</td>
                            <td><input type="text" name="passportNumber"></td>
                        </tr>
                        <tr>
                            <td>E-mail:</td>
                            <td><input type="text" name="emailAddress"></td>
                        </tr>
                        <tr>
                            <td>Phone:</td>
                            <td><input type="text" name="phoneNumber"></td>
                        </tr>
                        <tr>
                            <td>Street:</td>
                            <td><input type="text" name="street">&nbsp;Number:</td>
                            <td><input type="text" name="houseNumber" size="9"></td>
                        </tr>
                        <tr>
                            <td>City:</td>
                            <td><input type="text" name="city">&nbsp;ZIP Code:</td>
                            <td><input type="text" name="zip" size="9"></td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td><input type="text" name="country"></td>
                        </tr>
                    </table>
                </form>
            </div>

            <div id="div_confirmedbookings">
                <h5>Confirmed Bookings</h5>
                <table id="table_travelertrips" border="1">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Start</th>
                        <th>End</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div id="div_buttons">
        <button id="button_reset" type="button">Reset</button>
        <button id="button_save" type="button">Save</button>
    </div>

</div>

<div id="div_confirmation">
    Booking success!
    Please take note of your <b>booking id: <span id=span_bookingid></span></b>.
    <br>
    <br>
    Press
    <button id="button_ok" type="button">Ok</button>
    or hit refresh to return to the main page.
    <h3>Paid</h3>
    <b>Amount: </b><span id="span_pricepaid"></span>

    <h3>Trip Details</h3>
    <div id="div_confirmation_trip"></div>
	
    <h3>Traveler Details</h3>
    <div id="div_confirmation_traveler">
        <table id="table_confirmation_traveler">
            <tr>
                <td><b>First Name:</b></td>
                <td><span class="firstName"></span></td>
            </tr>
            <tr>
                <td><b>Last Name:</b></td>
                <td><span class="lastName"></span></td>
            </tr>
        </table>
    </div>
</div>

<script src='jquery-1.10.2.js'></script>
<script>

/*
 popular function to serialize a form to JSON, eg:
 http://css-tricks.com/snippets/jquery/serialize-form-to-json/
*/
$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

/*****************
  RESET FUNCTIONS
******************/

resetBooking = function() {
	$('#loadedbookingid').val('');
	$("input[name='bookmode'][value='new']").prop('checked', true);
	$('#bookingid').prop('readonly', true);
	$('#button_loadbooking').prop('disabled', true);
	$("#bookingid").val('');	
	$('#div_pricepaid').hide();
	resetTrip();
	resetTraveler();
};

resetTrip = function() {
	$("#select_trip").val('');
	$(".table_trip span").text('');
};

resetTraveler = function() {
	$('#loadedtravelerid').val('');
	$("input[name='travelermode'][value='new']").prop('checked', true);
	$('#travelerid').prop('readonly', true);
	$('#button_loadtraveler').prop('disabled', true);
	$("#travelerid").val('');
	$('#form_traveler :input').prop('readonly', false);	
	$("#form_traveler")[0].reset();
	resetTravelerTrips();
};

resetTravelerTrips = function() {
	$("#table_travelertrips tbody tr").remove();
};

/****************
  LOAD FUNCTIONS
*****************/

loadBooking = function(booking) {
	$('#loadedbookingid').val(booking.id);
	$('#pricepaid').val(booking.pricePaid);
	$('#div_pricepaid').show();
	selectTrip(booking.trip.id);
	loadTrip(booking.trip);
	selectTraveler(booking.traveler.id);
	loadTraveler(booking.traveler);
};

loadAllTrips = function(trips) {
	for (var i in trips) {
		$('#select_trip').append('<option value="' + trips[i].id + '">' + trips[i].tripName + '</option>');
	}};
	
loadTrip = function(trip) {
	for (var key in trip) {
		$('.table_trip span[class="' + key + '"]').text(trip[key]);
	}};	
	
loadTraveler = function(traveler) {
	$('#loadedtravelerid').val(traveler.id);
	for (var key in traveler) {
		$('#form_traveler :input[name="' + key + '"]').val(traveler[key]);
	}};	
	
loadTravelerTrips = function(trips) {
	resetTravelerTrips();
	for (var i1 in trips) {
		var row = '';
		for (var i2 in trips[i1]) {
			if ((i2 == "tripName") || (i2 == "startDate") || (i2 == "endDate")) {
				row = row + '<td>' + trips[i1][i2] + '</td>';
			}
		}
		$('#table_travelertrips tbody').append('<tr>' + row + '</tr>');
	}};

loadConfirmation = function(booking, traveler) {

	$('#span_bookingid').text(booking.id);
	
	$('#span_pricepaid').text(booking.pricePaid);

	$('#div_confirmation .table_trip').remove();
	$('.table_trip').clone(true).appendTo('#div_confirmation_trip');
	$('#div_confirmation .availablePlaces').parent().parent().remove();
	
	for (var key in traveler) {
		$('#table_confirmation_traveler span[class="' + key + '"]').text(traveler[key]);
	}
};	

/******************
  SELECT FUNCTIONS
*******************/
	
selectTrip = function(tripId) { $('#select_trip').val(tripId); };

selectTraveler = function(travelerId) {
	$("input[name='travelermode'][value='load']").prop('checked', true);
	$('#travelerid').val(travelerId);
	$('#travelerid').prop('readonly', false);
	$('#button_loadtraveler').prop('disabled', false);
	$('#form_traveler :input').prop('readonly', true);	
};

/***************
  TAB FUNCTIONS
****************/

initTabs = function() {
	$('#div_main').show();
	$('#div_confirmation').hide();
};

toggleTabs = function() {
	$('#div_main').toggle();
	$('#div_confirmation').toggle();
	window.scrollTo(0,0);
};

/**************************
  ERROR HANDLING FUNCTIONS
***************************/

handleError = function(jqXHR, textStatus, errorThrown) {
	var errorxml = $($.parseXML(jqXHR.responseText));
	alert(errorxml.find('message').text());
};

$(document).ready(function() {

	// caching proved problematic in IE 8
	$.ajaxSetup({cache: false});

	initTabs();
	
	resetBooking();
	
    $.getJSON('rest/trips', loadAllTrips).fail(handleError);
	
	$('#button_ok').click(function() { resetBooking(); toggleTabs(); });
	
    $("input[name='bookmode']").change(function() {
		if ($(this).val() == 'new') {
			resetBooking();
		}	
		else  { 
			$('#bookingid').prop('readonly', false);
			$('#button_loadbooking').prop('disabled', false);
		}});
		
    $("input[name='travelermode']").change(function() {
		if ($(this).val() == 'new') {
			resetTraveler();
		}	
		else {  
			$('#travelerid').prop('readonly', false);
			$('#button_loadtraveler').prop('disabled', false);
			$('#form_traveler :input').prop('readonly', true);
		}});

	$('#button_loadbooking').click(function() { 
		$.getJSON('rest/bookings/' + $('#bookingid').val(), function(booking) {
			loadBooking(booking);
			$.getJSON('rest/travelers/trips/' + booking.traveler.id, loadTravelerTrips).fail(handleError);}
		).fail(handleError)});
		
	$('#select_trip').click(function() {
		$.getJSON('rest/trips/' + $(this).val(), loadTrip).fail(handleError);
	});

	$('#button_loadtraveler').click(function() {
		$.getJSON('rest/travelers/' + $('#travelerid').val(), function(traveler) {
			loadTraveler(traveler);
			$.getJSON('rest/travelers/trips/' + $('#travelerid').val(), loadTravelerTrips).fail(handleError);}
		).fail(handleError)});

	$('#button_reset').click(function() { 
		resetBooking();	
	});	
		
	$('#button_save').click(function() {
		var bookingid = $('#loadedbookingid').val();
		var tripid = $('#select_trip').val();
		var traveler = $("#form_traveler").serializeObject();
		var pricepaid = $('#pricepaid').val();
		
		var bookingSuccess = function(data, textStatus, jqXHR) {			
			loadConfirmation(data.bookings[0], traveler);
			toggleTabs();
		};

		if (bookingid) {		
			var booking = {'id':		bookingid,
						   'trip': 	    {'id': tripid},
						   'traveler':	traveler,
						   'pricePaid': pricepaid};
			$.ajax({type: 			'PUT',
					url: 			'rest/book/' + bookingid,
					data: 			JSON.stringify(booking),
					contentType: 	"application/json",
					dataType: 		"json",
					success:        bookingSuccess,
					error:          handleError});
		}
		else {		
			var bookingRequest = {'tripId': 	tripid,
								  'travelers': 	[traveler]};
			$.ajax({type: 			'POST',
					url: 			'rest/book',
					data: 			JSON.stringify(bookingRequest),
					contentType: 	"application/json",
					dataType: 		"json",
					success:        bookingSuccess,
					error:			handleError});
		}
	});
});
	
</script>

</body>
</html>
