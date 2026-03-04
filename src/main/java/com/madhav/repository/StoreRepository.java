package com.madhav.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhav.model.Store;

public interface StoreRepository extends JpaRepository<Store, UUID> {

    public Store findByStoreAdminId(UUID id);


}
