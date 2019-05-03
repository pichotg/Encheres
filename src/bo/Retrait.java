package bo;

public class Retrait {
	private int noArticle;
	private String rue;
	private String code_Postal;
	private String ville;
	
	public Retrait() {
		super();
	}
	
	public Retrait(int noArticle, String rue, String code_Postal, String ville) {
		super();
		this.noArticle = noArticle;
		this.rue = rue;
		this.code_Postal = code_Postal;
		this.ville = ville;
	}

	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_Postal() {
		return code_Postal;
	}
	public void setCode_Postal(String code_Postal) {
		this.code_Postal = code_Postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	

}
