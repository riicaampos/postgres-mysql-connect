package br.com.app.domain.postgres.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "carros")
public class Carro implements Serializable{

    @Id
    private Long renavam;

    private String placa;

    private String marca;

    private String modelo;

    private String cor;

}

