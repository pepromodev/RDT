	<script th:inline="javascript" type="text/javascript">
		/* Fill in modal with content loaded with Ajax */
		$(document).ready(function() {
			$('#signup').on('click', function() {
				$("#signup-modal").modal();
				$("#signup-modal-body").text("");
				$.ajax({
					url : "signup",
					cache : false
				}).done(function(html) {
					$("#signup-modal-body").append(html);
				});
			});
			/* Selection du partenaire ************************************/
			/*	Récuperer les données relatives au partenaire sélectionné */
			/*	Afficher les données relatives au partenaire sélectionné  */
			$('#idPartenaire').on('change', function() {
				var idPartenaireSelectionne = $('#idPartenaire').val();
				$.ajax({
					url : "objetPartenaire?idPartenaire=" + idPartenaireSelectionne,
					cache : false
				}).done(function(html) {
					$("#fragmentObjetPartenaire").replaceWith(html);
				});
			/*	Récuperer la liste des conventions du partenaire sélectionné */
			/*	Afficher la liste des conventions du partenaire sélectionné  */
				$.ajax({
					url : "selectionConventions?idPartenaire=" + idPartenaireSelectionne,
					cache : false
				}).done(function(html) {
					$("#fragmentListeConvention").replaceWith(html);
				});
			});
			/* Selection de la convention ************************************/
			/*	Récuperer les données relatives à la convention sélectionnée */
			/*	Afficher les données relatives à la convention sélectionnée  */
			$('#idConvention').on('change', function() {
				alert("");
				var idConventionSelectionne = $('#idConvention').val();
				$.ajax({
					url : "objetConvention?idConvention=" + idConventionSelectionne,
					cache : false
				}).done(function(html) {
					$("#fragmentObjetConvention").replaceWith(html);
					$("#fragmentEtatConvention").replaceWith(html);
				});
			})

		});
	</script>