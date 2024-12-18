package com.atapia.main.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tpais")
@NoArgsConstructor
@Getter
@Setter
public class Tpais implements Serializable {
	@Id
	@Column(name = "idPais")
    private String idPais;

	@Column(name = "Name")
	private String name;

    @Column(name = "createdAt")
    private Date createdAt;;
}
