    package org.geektime.springbucks;

    import lombok.*;
    import org.hibernate.annotations.Type;
    import org.joda.money.Money;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.Table;
    import java.io.Serializable;

    /**
     * @author: justd
     * @Date: 19-3-3 23:10
     * @Description:
     */
    @Entity
    @Table(name = "T_COFFEE")
    @Data
    @ToString(callSuper = false)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Coffee extends BaseEntity implements Serializable {
        private String name;
        @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
                parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
        private Money price;
    }
