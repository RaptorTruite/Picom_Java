package fr.humanbooster.fx.picom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.humanbooster.fx.picom.business.Client;
import fr.humanbooster.fx.picom.business.LoginForm;
import fr.humanbooster.fx.picom.business.Utilisateur;
import fr.humanbooster.fx.picom.business.Zone;
import fr.humanbooster.fx.picom.service.UtilisateurService;
import fr.humanbooster.fx.picom.service.ZoneService;

@RestController
@RequestMapping("/picom")
public class PiComRestController {

	private ZoneService zoneService;
	private UtilisateurService utilisateurService;
	
	public PiComRestController(ZoneService zoneService, UtilisateurService utilisateurService) {
		super();
		this.zoneService = zoneService;
		this.utilisateurService = utilisateurService;
	}

	//Utilisateurs
	@PostMapping(value = "/login")
	public Utilisateur login(@RequestBody LoginForm loginForm) {
		System.out.println("email = " + loginForm.getEmail());
		System.out.println("motDePasse = " + loginForm.getMotDePasse());
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(loginForm.getEmail(), loginForm.getMotDePasse());
		return utilisateur;
	}
	
	@PostMapping("/inscription")
	public Client utilisateursPost(@RequestBody Client client) {
		if(client != null) return utilisateurService.enregistrerClient(client);
		else return null;
	}
	//(Put)
	//(Delete)
	
	//Zones
	@GetMapping("/zones")
	public List<Zone> zonesGet() {
		return zoneService.recupererZones();
	}
	
	@PostMapping("/zones/{nom}")
	public Zone zonesPost(@PathVariable String nom) {
		Zone zone = zoneService.ajouterZone(nom);
		return zone;
	}
	
	@DeleteMapping("/zones/{id}")
	public boolean zonesDelete(@PathVariable Long id) {
		return zoneService.supprimerZone(id);
	}
	
	//Annonces
	//(Get)
	//(Post)
	//(Delete)
	//(Put)
}
