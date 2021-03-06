<%@ page import="java.util.*,java.text.*,centre_Sportif5Servlet.*,CentreSportif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de bibliothèque</title>
<meta name="author" content="equipe15">
<meta name="description"
	content="Page d'accueil du système de gestion de la bilbiothèque.">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/navigation.jsp" />
		<h1 class="text-center">Système de gestion de la bibliothèque</h1>

<%
		    if (session.getAttribute("admin") != null)
		    {
%>
		<h3 class="text-center">Retard</h3>
		<div class="col-8 offset-2">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Utilisateur</th>
						<th scope="col">Nom</th>
						<th scope="col">Téléphone</th>
					</tr>
				</thead>
				<tbody>
<%
					    List<TupleMembre> membres = BiblioHelper.getBiblioInterro(session).getGestionMembre()
					                .getListeMembres(false);
					        for (TupleMembre m : membres)
					        {
%>
					<tr>
						<td><%=m.getUtilisateurName()%></td>
						<td><%=m.getNom()%></td>
						<td><%=m.getTelephone()%></td>
					<tr>
					<tr>
						<td></td>
						<td colspan="2">
<%
							    GestionBibliotheque b = BiblioHelper.getBiblioInterro(session);
							    List<TupleLivre> livres = b.getGestionInterrogation().listerLivresRetard(m.getUtilisateurName(),
							                    new Date().toString());
							    if (livres.size() == 0)
							    {
%> 
									Aucun retard
<%
							    }
							    else
							    {
%>
							<table class="table">
								<thead class="thead-dark">
									<tr>
										<th scope="col">#</th>
										<th scope="col">Titre</th>
										<th scope="col">Date de prêt</th>
									</tr>
								</thead>
								<tbody>
<%
									    for (TupleLivre l : livres)
									    {
%>
									<tr>
										<th scope="row"><%=l.getIdLivre()%></th>
										<td><%=l.getTitre()%></td>
										<td><%=l.getDatePret().toString()%></td>
									</tr>
<%
									    } // end for chaque livre en retard
%>
								</tbody>
							</table>
<%
     							} // end else livre en retard
         					} // end for all members
 %>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
<%
		    } // end if admin
		    else
		    {
		        GestionBibliotheque b = BiblioHelper.getBiblioInterro(session);
			    List<TupleLivre> livres = b.getGestionInterrogation().listerLivresMembre((String)session.getAttribute("userID"));
%>
		        <h3 class="text-center">Mes livres</h3>
		<div class="col-8 offset-2">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Titre</th>
						<th scope="col">Auteur</th>
						<th scope="col">Date de prêt</th>
					</tr>
				</thead>
				<tbody>
<%
					for(TupleLivre l : livres)
					{
%>
					<tr>
						<td><%= l.getIdLivre() %></td>
						<td><%= l.getTitre() %></td>
						<td><%= l.getAuteur() %></td>
						<td><%= l.getDatePret().toString() %></td>
					</tr>
<%
					}
%>
				</tbody>
			</table>
		</div>
<%
		        
		    }
%>

		<br>
		<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
		<jsp:include page="/WEB-INF/messageErreur.jsp" />
		<br>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>
