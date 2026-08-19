package org.example.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopService<R,P> {
    public P save(R r);
    public P update(Long id,R r);
    public P findById(Long id);
    public void deleteById(Long id);
    public Page<P> findAll(Pageable pageable);
}
