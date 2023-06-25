package com.acme.myshoes.platform.designing.domain.persistence;

import com.acme.myshoes.platform.designing.domain.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design,Long> { }
