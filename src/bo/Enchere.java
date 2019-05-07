package bo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Enchere {
	public ArticleVendu noArticle;
	public Utilisateur noUtilisateur;
	public Date dateEnchere;
	public int montantEnchere;

	public Enchere() {
		super();
	}

	public Enchere(ArticleVendu noArticle, Utilisateur noUtilisateur, Date dateEnchere, int montantEnchere) {
		super();
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
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

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	/**
	 * Méthode qui vérifie si l'utilisateur a sufisemment de crédits
	 * Et que la date d'enchère est comprise entre début et fin de vente.
	 * @return
	 */
	public Boolean achatPossible() {
		Boolean bool = false;
		if (this.getMontantEnchere() <= this.getNoUtilisateur().getCredit()
				&& this.getDateEnchere().after(this.getNoArticle().getDateDebutEncheres())
				&& this.getDateEnchere().before(this.getNoArticle().getDateFinEncheres()))
		{
			bool = true;
		}
		return bool;
	}
	/**Formate la date
	 * 
	 * @return
	 */
	public String affichageDateEnchere()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateFormatee = "";
		dateFormatee = sdf.format(this.getDateEnchere());
		return dateFormatee;
	}
}
