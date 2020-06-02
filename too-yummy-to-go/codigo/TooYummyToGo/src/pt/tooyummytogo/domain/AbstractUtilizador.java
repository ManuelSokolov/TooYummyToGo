package pt.tooyummytogo.domain;
/* Esta classe representa um utilizador */
public abstract class AbstractUtilizador {

	protected String username;
	protected String password;

	/**
	 * Constructor
	 * 
	 * @param username user name
	 * @param password user password
	 */
	public AbstractUtilizador(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Verifica se a password dada e igual a password do utilizador
	 * @param password
	 * @return true se a password e igual a password do utilizador, false caso contrario
	 */
	public boolean hasPassword(String password) {
		return this.password.contentEquals(password);
	}
	
	/**
	 * Devolve o nome de utilizador 
	 * @return username 
	 */
	public String getUsername() {
		return username;
	}
	


}
