
	 $( function() {
		$.widget( "custom.catcomplete", $.ui.autocomplete, {
		  _create: function() {
			this._super();
			this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
		  },
		  _renderMenu: function( ul, items ) {
			var that = this,
			  currentCategory = "";
			$.each( items, function( index, item ) {
			  var li;
			  if ( item.category != currentCategory ) {
				ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
				currentCategory = item.category;
			  }
			  li = that._renderItemData( ul, item );
			  if ( item.category ) {
				li.attr( "aria-label", item.category + " : " + item.label );
			  }
			});
		  }
		});
		var data = [
			{ label: "Mamede Saigg", category: "Professor" },
			{ label: "Matematica Discreta", category: "Disciplina" },
			{ label: "Programação I", category: "Disciplina" },
			{ label: "Programação II", category: "Disciplina" },
			{ label: "Banco de Dados", category: "Disciplina" },
			{ label: "1º semestre", category: "Semestre" },
			{ label: "2º semestre", category: "Semestre" },
			{ label: "3º semestre", category: "Semestre" },
			{ label: "4º semestre", category: "Semestre" },
			{ label: "5º semestre", category: "Semestre" },
			{ label: "6º semestre", category: "Semestre" },
			{ label: "7º semestre", category: "Semestre" },
			{ label: "8º semestre", category: "Semestre" }
		];
	 
		$( "#search" ).catcomplete({
		  delay: 0,
		  source: data
		});
	  } );
