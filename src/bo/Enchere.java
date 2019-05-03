package bo;

import java.util.Date;

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
	
	/**
	 * Méthode qui vérifie si l'utilisateur a sufisemment de crédits
	 * Et que la date d'enchère est comprise entre début et fin de vente.
	 * @return
	 */
	public Boolean achatPossible() {
		Boolean bool = false;
		if (this.getMontant_enchere() <= this.getNoUtilisateur().getCredit()
				&& this.getDateEnchere().after(this.getNoArticle().getDateDebutEncheres())
				&& this.getDateEnchere().before(this.getNoArticle().getDateFinEncheres()))
		{
			bool = true;
		}
		return bool;
	}
}
