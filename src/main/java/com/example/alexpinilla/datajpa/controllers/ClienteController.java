package com.example.alexpinilla.datajpa.controllers;

import com.example.alexpinilla.datajpa.models.dao.IClienteDao;
import com.example.alexpinilla.datajpa.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    @Qualifier("clientDaoJPA")
    private IClienteDao clienteDao;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("título", "listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String id(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteDao.findOne(id);
        } else {
            return "listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "editar cliente");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(Cliente cliente) {
        clienteDao.save(cliente);
        return "redirect:listar";
    }
}
