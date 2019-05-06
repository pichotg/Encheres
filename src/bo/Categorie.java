package bo;

public enum Categorie {
	// Objets directement construits
	INFO(1, "Informatique"), AMEU(2, "Ameublement"), VETE(3, "Vêtement"), SPOR(4, "Sport&Loisirs"), ALL(5,"Toutes");

	private int noCategorie = -1;
	private String name = "";

	private Categorie() {
	}

	// Constructeur
	Categorie(int noCategorie, String name) {
		this.name = name;
		this.noCategorie = noCategorie;
	}

	public String toString() {
		return name;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getNoByName(String name) {
		int noCat = -1;

		switch (name) {
		case "Informatique":
			noCat = 1;
			break;
		case "Ameublement":
			noCat = 2;
			break;
		case "Vêtement":
			noCat = 3;
			break;
		case "Sport&Loisirs":
			noCat = 4;
			break;	
		default:
			break;
		}

		return noCat;
	}
	
	

}
