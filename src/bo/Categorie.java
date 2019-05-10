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
		case "Computer":
			noCat = 1;
			break;
		case "Ameublement":
			noCat = 2;
			break;
		case "Furniture":
			noCat = 2;
			break;
		case "Vêtement":
			noCat = 3;
		case "Clothes":
			noCat = 3;
			break;
		case "Sport&Loisirs":
			noCat = 4;
			break;
		case "Sport&Leisure":
			noCat = 4;
			break;
		case "Toutes":
			noCat = 5;
			break;
		case "All":
			noCat = 5;
			break;	
		default:
			break;
		}

		return noCat;
	}
	
	public static String getNameByNo(int no) {
		String name = "Toutes";

		switch (no) {
		case 1:
			name = "Informatique";
			break;
		case 2:
			name = "Ameublement";
			break;
		case 3:
			name = "Vêtement";
			break;
		case 4:
			name = "Sport&Loisirs";
			break;
		case 5:
			name = "Toutes";
			break;	
		default:
			break;
		}
		return name;
	}
	

}
