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
@Access(AccessType.PROPERTY)
public class Uom extends ApplicationBean {
	private static final long serialVersionUID = -6589930036774238003L;

	private Long id;
	private UomGroup group;
	private String name;
	private BigDecimal rate;
	private Boolean isBaseUom;

	@Id
	@GeneratedValue(generator="UomSeq")
	@SequenceGenerator(name="UomSeq", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=UomGroup.class)
	@JoinColumn(nullable=false, name="groupId")
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

	@Column(nullable = false)
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
