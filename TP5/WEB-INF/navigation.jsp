<nav class="navbar navbar-expand-lg navbar-light navbar-fixed-left" style="background-color: #e3f2fd;">
	<div class="navbar-collapse collapse">
    	<ul class="nav navbar-nav">
	<%
	    if (session.getAttribute("admin") != null)
	    {
	%>
			<li><a class="nav-item nav-link" href="#">Fiche Participant</a></li>
	<%
	    }
	%>
			<li><a class="nav-item nav-link" href="#">S'inscrire � une �quipe</a></li>
			<li><a class="nav-item nav-link" href="#">G�rer son �quipe</a></li>
			<li><a class="nav-item nav-link" href="#">Supprimer son compte</a></li>
			
		</ul>
		</div>
		<div class="navbar-collapse collapse justify-content-end">
		<ul class="nav navbar-nav navbar-right">
			<li><a class="nav-item nav-link" href="Logout">D�connexion</a></li>
		</ul>
	</div>
</nav>
