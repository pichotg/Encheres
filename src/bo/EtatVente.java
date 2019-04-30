package bo;

public enum EtatVente {

	VEC("vec","Vente en cours"),
	VND("vnd","Vente non débutée"),
	VET("vet","vente terminée");
	
	private String nom;
	private String description;
	//Constructeur
	EtatVente(String nom, String description){
		this.nom = nom;
		this.description = description;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
