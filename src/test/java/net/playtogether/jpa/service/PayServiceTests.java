package net.playtogether.jpa.service;

import java.time.LocalDate;
import java.util.List;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.entity.PayType;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PayServiceTests {

	@Autowired
	private PayService payService;
	
	@Autowired
	private PayTypeService payTypeService;
	
	@Autowired
	private UsuarioService	usuarioService;
	
	@Autowired
	private ChampionshipService	championshipService;
	
	@Autowired
	private TeamService	teamService;
	
	//Save Pay
	 @Test
	 void shouldPaySaveTest() {
		 List<Pay> lista = this.payService.findAll();
		 Integer tamaño = lista.size();
		 
		 Pay p = new Pay();
		 Usuario user = this.usuarioService.findUserById(1);
		 p.setUser(user);
		 p.setAmount(5.0);
		 p.setDate(LocalDate.of(2021, 03, 01));
		 PayType pt = this.payTypeService.findById(1);
		 p.setPayType(pt);
		 this.payService.save(p);
		 
		 Integer tamaño2 = this.payService.findAll().size();
		 
		 Assertions.assertThat(tamaño2).isEqualTo(tamaño + 1); 
	 }
	 
	//Delete Pay
		 @Test
		 void deletePayTest() {
			 List<Pay> lista = this.payService.findAll();
			 Integer tamaño = lista.size();
			
			 Pay p = this.payService.findById(1);
			 this.payService.delete(p);
			 
			 Integer tamaño2 = this.payService.findAll().size();
			 
			 Assertions.assertThat(tamaño2).isEqualTo(tamaño - 1); 
		 }
	
	//Find last pay by user name
		 @Test
		 void shouldFindLastPayByUsernameTest() {
			 Usuario usuario = this.usuarioService.findUserById(3);
			 Pay pay = this.payService.findLastPayByUsername(usuario.getUser().getUsername());
			 
			 assertThat(pay.getId()).isEqualTo(21);
		 }
		 
	//Find last finished pay for championship by username
	 @Test
	 void shouldFindLastFinishedPayForChampionshipByUsername() {
		 Usuario usuario = this.usuarioService.findUserById(3);
		 Championship ch = this.championshipService.findChampionshipId(7);
		 Pay pay = this.payService.findLastFinishedPayForChampionshipByUsername(usuario.getUser().getUsername(), ch.getId());
		 
		 assertThat(pay.getId()).isEqualTo(20);
	 }
		 
	//Find last finished pay for team by username
		 @Test
		 void shouldFindLastFinishedPayForTeamByUsername() {
			 Usuario usuario = this.usuarioService.findUserById(3);
			 Pay pay = this.payService.findLastFinishedPayForTeamByUsername(usuario.getUser().getUsername(),7);
			 
			 assertThat(pay.getId()).isEqualTo(20);
		 }
		 
	//Find last finished pay for championship by username
		 @Test
		 void shouldFindIdPaysNotFinishedByUsername() {
			 Usuario usuario = this.usuarioService.findUserById(3);
			 List<Pay> lp = this.payService.findIdPaysNotFinishedByUsername(usuario.getUser().getUsername());
			 
			 assertThat(lp.size()).isEqualTo(2);
		 }
		 
	//Find last NOT finished pay for championship by username
		 @Test
		 void shouldfindLastNotFinishedPayForChampionshipByUsername() {
			 Usuario usuario = this.usuarioService.findUserById(3);
			 Championship ch = this.championshipService.findChampionshipId(7);

			 List<Pay> p = this.payService.findLastNotFinishedPayForChampionshipByUsername(usuario.getUser().getUsername(), ch.getId());
			 
			 assertThat(p.size()).isEqualTo(1);
		 }
		 
	//Find last NOT finished pay for team by username
		 @Test
		 void shouldfindLastNotFinishedPayForTeamByUsername() {
			 Usuario usuario = this.usuarioService.findUserById(3);
			 Team t = this.teamService.findTeamById(7);

			 List<Pay> p = this.payService.findLastNotFinishedPayForChampionshipByUsername(usuario.getUser().getUsername(), t.getId());
			 
			 assertThat(p.size()).isEqualTo(1);
		 }

		 
	//Delete all pays test
		 @Test
		 void shouldDeleteAllPays() {
			 this.payService.deleteAll(this.payService.findAll());
			 List<Pay> lista2 = this.payService.findAll();
			 
			 assertThat(lista2.size()).isEqualTo(0);
		 }
		 
		 
	//Find last PREMIUM pay by Username
		 @Test
		 void shouldfindLastPayByUsernamePremium() {
			 Usuario usuario = this.usuarioService.findUserById(4);
			 List<Pay> p = this.payService.findLastPayByUsernamePremium(usuario.getUser().getUsername());
			 
			 assertThat(p.size()).isEqualTo(1);
		 }

	//Find pay by id test
		 @Test
		 void shouldfindById() {
			 Pay p = this.payService.findById(3);
			 
			 assertThat(p.getId()).isEqualTo(3);
		 }


}
