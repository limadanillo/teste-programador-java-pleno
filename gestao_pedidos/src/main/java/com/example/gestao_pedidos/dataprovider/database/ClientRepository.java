package com.example.gestao_pedidos.dataprovider.database;

import com.example.gestao_pedidos.core.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
