package bo;

public enum Categorie {
	//Objets directement construits
	INFO(1, "Informatique"),
	AMEU(2, "Ameublement"),
	VETE(3, "Vêtement"),
	SPOR(4, "Sport&Loisirs");
		   
	private int noCategorie = -1;
	private String name = "";
		  
		   
	//Constructeur
	Categorie(int noCategorie, String name){
		this.name = name;
		this.noCategorie = noCategorie;
	}
		   
	public String toString(){
		return name;
	}
}
