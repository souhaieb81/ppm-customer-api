package com.cbs.ppm.customer.model;

import java.time.LocalDate;

import com.cbs.ppm.customer.enums.CustomerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "legal_entities")
@PrimaryKeyJoinColumn(name = "customer_id")
public class LegalEntity extends Customer {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "incorporation_date")
    private LocalDate incorporationDate;

    @Column(name = "legal_form")
    private String legalForm;

    protected LegalEntity() {
        super();
    }

    private LegalEntity(LegalEntityBuilder builder) {
        super(builder.id, builder.taxId, builder.email, builder.phoneNumber, builder.address, CustomerType.MORAL);
        this.companyName = builder.companyName;
        this.incorporationDate = builder.incorporationDate;
        this.legalForm = builder.legalForm;
    }

    public static class LegalEntityBuilder extends CustomerBuilder<LegalEntityBuilder> {
        private String companyName;
        private LocalDate incorporationDate;
        private String legalForm;

        public LegalEntityBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public LegalEntityBuilder incorporationDate(LocalDate incorporationDate) {
            this.incorporationDate = incorporationDate;
            return this;
        }

        public LegalEntityBuilder legalForm(String legalForm) {
            this.legalForm = legalForm;
            return this;
        }

        public LegalEntity build() {
            return new LegalEntity(this);
        }
    }

    public static LegalEntityBuilder builder() {
        return new LegalEntityBuilder().customerType(CustomerType.MORAL);
    }
}