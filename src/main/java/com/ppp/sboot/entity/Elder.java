package com.ppp.sboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
  public class Elder implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String name;

    private String phone;

    private String address;

    private String gender;

    private LocalDateTime birthday;

    private Double height;

    private Double weight;

    private String blood;

    private String allergic;

    private String edesc;

    private String img;

    private LocalDateTime phyexam;

    private String status;

    private Integer institutionId;

    private String disease;

    private String request;


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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDateTime getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDateTime birthday) {
    this.birthday = birthday;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public String getBlood() {
    return blood;
  }

  public void setBlood(String blood) {
    this.blood = blood;
  }

  public String getAllergic() {
    return allergic;
  }

  public void setAllergic(String allergic) {
    this.allergic = allergic;
  }

  public String getEdesc() {
    return edesc;
  }

  public void setEdesc(String edesc) {
    this.edesc = edesc;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public LocalDateTime getPhyexam() {
    return phyexam;
  }

  public void setPhyexam(LocalDateTime phyexam) {
    this.phyexam = phyexam;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getInstitutionId() {
    return institutionId;
  }

  public void setInstitutionId(Integer institutionId) {
    this.institutionId = institutionId;
  }

  public String getDisease() {
    return disease;
  }

  public void setDisease(String disease) {
    this.disease = disease;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }
}
