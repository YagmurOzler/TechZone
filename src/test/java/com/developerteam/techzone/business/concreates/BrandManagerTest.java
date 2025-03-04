package com.developerteam.techzone.business.concreates;

import com.developerteam.techzone.dataAccess.abstracts.IBrandRepository;
import com.developerteam.techzone.entities.concreates.Brand;
import com.developerteam.techzone.entities.dto.DtoBrand;
import com.developerteam.techzone.entities.dto.DtoBrandIU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.developerteam.techzone.dataAccess.abstracts.IBrandRepository;
import com.developerteam.techzone.entities.concreates.Brand;
import com.developerteam.techzone.entities.dto.DtoBrand;
import com.developerteam.techzone.entities.dto.DtoBrandIU;
import com.developerteam.techzone.exception.BaseException;
import com.developerteam.techzone.exception.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class BrandManagerTest {

    @Autowired
    private IBrandRepository brandRepository; // Gerçek repository

    @Autowired
    private BrandManager brandManager;

    @Test
    void testGetAll() {
        List<DtoBrand> brands = brandManager.getAll();
        assertNotNull(brands);
        assertEquals(2, brands.size());
        assertEquals(1, brands.get(0).getId());
        assertEquals("Apple", brands.get(0).getName());
    }

    @Test
    void testGetById() {
        DtoBrand brand = brandManager.getById(1);
        assertEquals("Apple", brand.getName());
        assertEquals(1, brand.getId());
    }

    @Test
    @Transactional
    @Rollback(false)
    void testAdd() {
        DtoBrandIU dtoBrandIU = new DtoBrandIU();
        dtoBrandIU.setName("Xiaomi");

        // Act
        assertNotNull(dtoBrandIU);
        DtoBrand result = brandManager.add(dtoBrandIU);

        // Assert
        assertNotNull(result, "Result should not be null.");
        assertEquals("Xiaomi", result.getName());


        // Database control
        Brand savedBrand = brandRepository.getById(6);
        assertNotNull(savedBrand, "Saved brand should not be null.");
        assertEquals(result.getName(), savedBrand.getName());
    }

    @Test
    @Transactional
    @Rollback(false)
    void testUpdate() {
        DtoBrandIU updatedBrand = new DtoBrandIU();
        updatedBrand.setName("Realme");


        DtoBrand updating = brandManager.update(6, updatedBrand);

        assertNotNull(updating);
        assertEquals("Realme", updating.getName());

        //Database control
        Brand foundBrand = brandRepository.getById(6);
        assertEquals(updatedBrand.getName(), foundBrand.getName());
    }

    @Test
    @Transactional
    @Rollback(false)
    void testDelete() {
        Brand brand = brandRepository.getById(3);
        assertNotNull(brand);
        brandRepository.delete(brand);
    }

    @Test
    void testFindBrandOrThrow() {
        Brand result = brandManager.findBrandOrThrow(1);

        assertNotNull(result);
        assertEquals(1, result.getId(), "The brand ID should match.");
        assertEquals("Apple", result.getName());

    }


}
