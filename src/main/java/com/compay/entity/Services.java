package com.compay.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SERVICES")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name="LINK")

    private String link;

    @Column(name="UNITS")
    private String units;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", link='" + link + '\'' +
                ", units='" + units + '\'' +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="service")
    private Set<AdressServices> adressServices;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="defaultServices")
    private Set<DefaultRates> defaultRates;
}
