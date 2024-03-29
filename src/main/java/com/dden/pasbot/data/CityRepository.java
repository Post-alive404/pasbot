package com.dden.pasbot.data;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository extends BaseRepository<CityEntity>{
    public List<CityEntity> findAll() {
        return em.createQuery("""
                        select c
                        from CityEntity c
                        """,
                        CityEntity.class)
                .getResultList();
    }

    public String getName(Integer id) {
        return em.createQuery("""
                        select c.name
                        from CityEntity c
                        where c.id = :id
                        """, String.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
