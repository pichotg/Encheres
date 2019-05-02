package bo;

import java.util.ArrayList;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private int administrateur;
	private int etatUtilisateur;
	private ArrayList <ArticleVendu> lesArticles;
	private ArrayList <Enchere> lesEncheres;
	
		
	public Utilisateur() {
		super();
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, int administrateur, int etatUtilisateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.etatUtilisateur = etatUtilisateur;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}
	
	public String getAdresse() {
		return String.format("%s \n %s %s", this.getRue(),this.getCodePostal(), this.getVille());
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(int administrateur) {
		this.administrateur = administrateur;
	}

	public int getEtatUtilisateur() {
		return etatUtilisateur;
	}

	public void setEtatUtilisateur(int etatUtilisateur) {
		this.etatUtilisateur = etatUtilisateur;
	}

	public ArrayList<ArticleVendu> getLesArticles() {
		return lesArticles;
	}

	public void setLesArticles(ArrayList<ArticleVendu> lesArticles) {
		this.lesArticles = lesArticles;
	}

	public ArrayList<Enchere> getLesEncheres() {
		return lesEncheres;
	}

	public void setLesEncheres(ArrayList<Enchere> lesEncheres) {
		this.lesEncheres = lesEncheres;
	}

	@Override
	public String toString() {
		return "Utilisateur [getNoUtilisateur()=" + getNoUtilisateur() + ", getPseudo()=" + getPseudo() + ", getNom()="
				+ getNom() + ", getPrenom()=" + getPrenom() + ", getEmail()=" + getEmail() + ", getTelephone()="
				+ getTelephone() + ", getRue()=" + getRue() + ", getCodePostal()=" + getCodePostal() + ", getVille()="
				+ getVille() + ", getMotDePasse()=" + getMotDePasse() + ", getCredit()=" + getCredit()
				+ ", getAdministrateur()=" + getAdministrateur() + "]";
	}	
}
