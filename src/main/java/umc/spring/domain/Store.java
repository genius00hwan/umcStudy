package umc.spring.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.*;
import umc.spring.domain.base.BaseEntity;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.StoreStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Float rating;

    private String address;

    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
