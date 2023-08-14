package cahier.text.gestioncahierdetexte.entities;

import java.util.*;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Entity
@Table(name = "compte_individu",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        })
@Data
public class IndividuCompte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String passwordTemp;
    private boolean actif;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateDebutValite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFinValite;
    @ManyToOne
    @JoinColumn(name = "individu_id", nullable = false)
    private Individu individu; //$2a$10$YMJWAx48/CJSZJEaIKGeieXLbygEKWR1b2y0n7gm3ZCxHmy2nORcm
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    public IndividuCompte() {
    }
    public IndividuCompte(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public IndividuCompte(Long id, String username, String email,  String password, String passwordTemp, boolean actif,
                          Date dateDebutValite, Date dateFinValite, Individu individu, Set<Role> roles) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordTemp = passwordTemp;
        this.actif = actif;
        this.dateDebutValite = dateDebutValite;
        this.dateFinValite = dateFinValite;
        this.individu = individu;
        this.roles = (List<Role>) roles;
    }
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public String getPasswordTemp() {
//        return passwordTemp;
//    }
//    public void setPasswordTemp(String passwordTemp) {
//        this.passwordTemp = passwordTemp;
//    }
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public boolean isActif() {
//        return actif;
//    }
//    public void setActif(boolean actif) {
//        this.actif = actif;
//    }
//    public Date getDateDebutValite() {
//        return dateDebutValite;
//    }
//    public void setDateDebutValite(Date dateDebutValite) {
//        this.dateDebutValite = dateDebutValite;
//    }
//    public Date getDateFinValite() {
//        return dateFinValite;
//    }
//    public void setDateFinValite(Date dateFinValite) {
//        this.dateFinValite = dateFinValite;
//    }
//    public Individu getIndividu() {
//        return individu;
//    }
//    public void setIndividu(Individu individu) {
//        this.individu = individu;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public List<Role> getRoles() {
//        return roles;
//    }
//    public void setRoles(Role roles) {this.roles = (List<Role>) roles;}
}
