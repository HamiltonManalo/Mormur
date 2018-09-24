function toggleOverlay() {
	var transEndEventNames = {
		'WebkitTransition': 'webkitTransitionEnd',
		'MozTransition': 'transitionend',
		'OTransition': 'oTransitionEnd',
		'msTransition': 'MSTransitionEnd',
		'transition': 'transitionend'
	},
	transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ],
	support = { transitions : Modernizr.csstransitions };

	if( classie.has( overlay, 'open' ) ) {
		classie.remove( overlay, 'open' );
		classie.add( overlay, 'close' );
		document.onkeydown = null;
		var onEndTransitionFn = function( ev ) {
			if( support.transitions ) {
				if( ev.propertyName !== 'visibility' ) return;
				this.removeEventListener( transEndEventName, onEndTransitionFn );
			}
			classie.remove( overlay, 'close' );
		};
		if( support.transitions ) {
			overlay.addEventListener( transEndEventName, onEndTransitionFn );
		}
		else {
			onEndTransitionFn();
		}
	}
	else if( !classie.has( overlay, 'close' ) ) {
		classie.add( overlay, 'open' );
		
		// Close the overlay when the user presses the escape key
		document.onkeydown = function(event) {
    	if (event.keyCode == 27) {
        toggleOverlay();
    	}			
		}
	}
}

(function() {
		$('.overlay-trigger').each(function(index, element) {
		$(element).click(function() {
			overlay = document.querySelector('section.' + $(element).attr('data-overlay'));
			closeBtn = overlay.querySelector('button.overlay-close');
			toggleOverlay();		
			closeBtn.addEventListener( 'click', toggleOverlay);			
		});		
	});
})();