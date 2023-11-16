package gitme.money;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.adresse_ip}")
	private String adresseIP;

	@Value("${spring.datasource.port}")
	private String port;

	@Value("${spring.datasource.nom_base_de_donnees}")
	private String nomBaseDeDonnees;

	@Value("${spring.datasource.username}")
	private String nomUtilisateur;

	@Value("${spring.datasource.password}")
	private String motDePasse;

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().url("jdbc:mysql://" + adresseIP + ":" + port + "/" + nomBaseDeDonnees)
				.username(nomUtilisateur).password(motDePasse).build();
	}
}
