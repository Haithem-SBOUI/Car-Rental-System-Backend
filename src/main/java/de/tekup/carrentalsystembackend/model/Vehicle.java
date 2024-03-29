package de.tekup.carrentalsystembackend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.tekup.carrentalsystembackend.model.enums.CarBrand;
import de.tekup.carrentalsystembackend.model.enums.FuelType;
import de.tekup.carrentalsystembackend.model.enums.TransmType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate_number", unique = true)
    private String licensePlateNumber;

    // ?Vehicle Details
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    private String model;

    @Column(name = "launch_year")
    private int launchYear;

    private String color;

    private long mileage;

    // ?Technical Specifications
    @Enumerated(EnumType.STRING)
    private FuelType fuel; //Gasoline (Petrol), Diesel, Electric

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type")
    private TransmType transmissionType; // Automatic , Manual

    @Column(name = "horse_power")
    private int horsPower;


    @Column(name = "price_per_day")
    private int pricePerDay;

    private Boolean isAvailable;

    @Column(name = "last_maintenance_mileage")
    private int lastMaintenanceMileage;

    @ElementCollection
    @CollectionTable(name = "vehicle_pictures")
    @Column(name = "picture_path")
    private List<String> picturePaths;


    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Insurance insurance;

    //    @ManyToMany(mappedBy = "vehicles", cascade = CascadeType.ALL)
    //    @JsonBackReference
    //    private Set<FavoriteVehicle> favoriteVehicle;

    @ManyToOne
    @JsonManagedReference
    private User user;


    @OneToMany
    @JsonIgnoreProperties("vehicle")
    private Set<Reservation> reservations;


    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;


}
