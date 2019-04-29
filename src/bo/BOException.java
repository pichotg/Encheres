package bo;

@SuppressWarnings("serial")
public class BOException extends Exception{
	public BOException(){
		super();
	}
	
	public BOException(String message){
		super(message);
	}
	
	public BOException(String message, Throwable exc){
		super(message, exc);
	}
	
}
