package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "stock_release_ccn", uniqueConstraints = {
    @UniqueConstraint(columnNames = "referenceId")
})
public class ReleaseToCCNStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String referenceId;

    @Column(nullable = false)
    private String assemblyCode;

    @Column(nullable = false)
    private String ccn;


    @Column(nullable = false)
    private String releaseDate;




    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getAssemblyCode() {
        return assemblyCode;
    }

    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }
    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}