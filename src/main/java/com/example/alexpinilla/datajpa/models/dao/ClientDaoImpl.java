package com.example.alexpinilla.datajpa.models.dao;

import com.example.alexpinilla.datajpa.models.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("clientDaoJPA")
public class ClientDaoImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)

    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Transactional
    //merge para Actualizar
    //persist para crear
    @Override
    public void save(Cliente cliente) {
        if (cliente.getId()!=null && cliente.getId()>0){
          em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Override
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }
}
