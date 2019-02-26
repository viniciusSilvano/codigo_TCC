package com.Faciplac.SGQ.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "idusuario")
public class Professor extends Usuario {
	
	
	
	
}
