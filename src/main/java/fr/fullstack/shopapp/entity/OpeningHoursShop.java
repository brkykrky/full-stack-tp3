package fr.fullstack.shopapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
//import javax.persistence.Table;

@Entity
//@Table(name = "openingHours")
public class OpeningHoursShop {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(nullable = false)
	@Min(1)
	@Max(7)
    private int day;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern="hh:mm")
    private Date openAt;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern="hh:mm")
    private Date closeAt;
	
	// Mettre plutot onetomany dans shop
//	@ManyToOne
//	private Shop shop;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public Date getOpenAt() {
		return openAt;
	}
	
	public void setOpenAt(Date openAt) {
		this.openAt = openAt;
	}
	
	public Date getCloseAt() {
		return closeAt;
	}
	
	public void setCloseAt(Date closeAt) {
		this.closeAt = closeAt;
	}
}
