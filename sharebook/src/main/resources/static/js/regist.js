$( document ).ready( function() {
    $( '.checkall' ).click( function() {
      $( '.agreement' ).prop( 'checked', this.checked );
    } );

    $(".main").on("click", ".agreement", function() {
        var is_checked = true;
    
        $(".main .agreement").each(function(){
            is_checked = is_checked && $(this).is(":checked");
        });
    
        $(".checkall").prop("checked", is_checked);
    });
  } );