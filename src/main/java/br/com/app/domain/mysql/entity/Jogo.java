package br.com.app.domain.mysql.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "jogos")
public class Jogo implements Serializable{

    @Id
    private Long id;

    private String nome;


}
