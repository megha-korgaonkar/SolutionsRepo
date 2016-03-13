package com.chaljaa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This Entity Refers to the Activities to which the Members of the System can enroll in. 
 * @author Rajan Sanghvi
 *
 */
@Entity
@Table(name = "es_activity")
public class EsActivity extends ESEntityBase {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Unique Identifier for each activity generated and managed the underlying database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "es_activity_id")
    private Long id;
    
    /**
     * This is the humanized code given to each activity by the System. This remains unique across various activities
     */
    @Column(name = "code", nullable = false)
    private String code;
    
    /**
     * The actual business name of the activity     
     **/
    @Column(name  = "name", nullable = false)
    private String name;

    public EsActivity() {
        super();
    }

    public EsActivity(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EsActivity other = (EsActivity) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        }
        else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ESActivity [code=" + code + ", name=" + name + "]";
    }
}
