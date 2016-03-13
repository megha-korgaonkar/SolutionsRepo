package com.chaljaa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.chaljaa.enumeration.SequenceTitle;

@Entity
@Table(name = "es_sequence_generator")
public class EsSequenceGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sequence_generator_id")
    private Long id;
    
    @Column(name = "code_preffix", nullable = false)
    private String codePreffix;
    
    @Column(name = "value", nullable = false)
    private long value;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "title", nullable = false)
    private SequenceTitle title;
    
    @Version
    @Column(name = "version")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePreffix() {
        return codePreffix;
    }

    public void setCodePreffix(String codePreffix) {
        this.codePreffix = codePreffix;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public SequenceTitle getTitle() {
        return title;
    }

    public void setTitle(SequenceTitle title) {
        this.title = title;
    }

    public int getVersion() {
        return version;
    }    
}
