package com.Faciplac.SGQ.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "coordenador")
@PrimaryKeyJoinColumn(name = "idusuario")
public class Coordenador extends Usuario {
	
}
