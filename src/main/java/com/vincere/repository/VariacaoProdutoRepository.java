package com.vincere.repository;

import com.vincere.model.VariacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariacaoProdutoRepository
        extends JpaRepository<VariacaoProduto, Long> {
}
