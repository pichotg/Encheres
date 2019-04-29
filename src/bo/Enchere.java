package bo;

import java.sql.Date;

public class Enchere {
	public ArticleVendu noArticle;
	public Utilisateur noUtilisateur;
	public Date dateEnchere;
	public int montant_enchere;
	
	public Enchere() {
		super();
	}

	public Enchere(ArticleVendu noArticle, Utilisateur noUtilisateur, Date dateEnchere, int montant_enchere) {
		super();
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public ArticleVendu getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(ArticleVendu noArticle) {
		this.noArticle = noArticle;
	}

	public Utilisateur getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Utilisateur noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
}
