package restaurant.uom.bean.entity;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(AccessType.FIELD)
public class Uom extends ApplicationBean {
	private static final long serialVersionUID = -6589930036774238003L;

	@Id
	@GeneratedValue(generator="UomSeq")
	@SequenceGenerator(name="UomSeq", allocationSize=1)
	private Long id;	
	@ManyToOne(targetEntity=UomGroup.class)
	@JoinColumn(nullable=false, name="groupId")
	private UomGroup group;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private BigDecimal rate;
	@Column(nullable = false)
	private Boolean isBaseUom;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public UomGroup getGroup() {
		return group;
	}

	public void setGroup(UomGroup group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsBaseUom() {
		return isBaseUom;
	}

	public void setIsBaseUom(Boolean isBaseUom) {
		this.isBaseUom = isBaseUom;
	}

	
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
