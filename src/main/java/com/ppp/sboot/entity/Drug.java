package com.ppp.sboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-20
 */
@Getter
@Setter
@Data
  public class Drug implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEdesc() {
    return edesc;
  }

  public void setEdesc(String edesc) {
    this.edesc = edesc;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getInventory() {
    return inventory;
  }

  public void setInventory(Integer inventory) {
    this.inventory = inventory;
  }

  public String getDosage() {
    return dosage;
  }

  public void setDosage(String dosage) {
    this.dosage = dosage;
  }

  public String getTaboo() {
    return taboo;
  }

  public void setTaboo(String taboo) {
    this.taboo = taboo;
  }

  private String name;

    private String edesc;

    @TableField("beginDate")
    private LocalDateTime beginDate;
    @TableField("endDate")
    private LocalDateTime endDate;

    private BigDecimal price;

    private Integer inventory;

    private String dosage;

    private String taboo;


}
