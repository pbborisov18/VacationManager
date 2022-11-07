package com.example.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@Table(name = "Leaves")
public class Leave {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime creationDate;
    private boolean halfDay;
    private boolean approved;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserId", nullable = false)
    @ToString.Exclude
    private User user;

    public Leave(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime creationDate, boolean halfDay, boolean approved, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
        this.halfDay = halfDay;
        this.approved = approved;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Leave leave = (Leave) o;
        return id != null && Objects.equals(id, leave.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
