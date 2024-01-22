package com.dden.pasbot.data;

import com.dden.pasbot.dto.AddressDto;
import com.dden.pasbot.dto.DepartmentType;
import com.dden.pasbot.dto.PinDto;
import com.dden.pasbot.dto.PinState;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentsRepository extends BaseRepository<DepartmentsEntity>{

    public List<DepartmentType> getTypesOfCity(Integer id){
        return em.createQuery("""
                                select distinct d.type
                                from DepartmentsEntity d
                                where d.cityId = :id
                                """, DepartmentType.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<AddressDto> getAddressOfType(DepartmentType departmentType, Long chatId){
        return em.createQuery("""
                                select new com.dden.pasbot.dto.AddressDto(d.id, d.address, d.name)
                                from DepartmentsEntity d
                                inner join ChatsCitiesEntity c
                                on d.cityId = c.cityId
                                where d.type =: departmentType and c.chatId =: chatId
                                """, AddressDto.class)
                .setParameter("departmentType", departmentType)
                .setParameter("chatId", chatId)
                .getResultList();
    }

    public PinDto findPin(Integer addressId){
        return em.createQuery("""
                                select new com.dden.pasbot.dto.PinDto(d.pin, d.state)
                                from DepartmentsEntity d
                                where d.id = :id
                                """, PinDto.class)
                .setParameter("id", addressId)
                .getSingleResult();
    }

    @Transactional
    public int updateState(PinState state, Integer addressId, Long userId){
        return em.createQuery("""
                                update DepartmentsEntity d
                                set d.state =: state, d.updatedBy =: userId
                                where d.id = :id
                                """)
                .setParameter("id", addressId)
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public int updatePin(Integer addressId, String pin, Long userId){
        return em.createQuery("""
                                 update DepartmentsEntity d
                                 set d.pin =: pin, d.updatedBy =: userId
                                 where d.id = :id
                                 """)
                .setParameter("id", addressId)
                .setParameter("pin", pin)
                .setParameter("userId", userId)
                .executeUpdate();
    }


}