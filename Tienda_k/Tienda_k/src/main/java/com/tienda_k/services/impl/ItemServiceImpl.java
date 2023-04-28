/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda_k.services.impl;

import com.tienda_k.domain.Item;
import com.tienda_k.services.ItemService;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el articulo en el carrito
            if (Objects.equals(i.getIdArticulo(), item.getIdArticulo())) {
                //Valida si aÃºn puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no estÃ¡ el articulo en el carrito se agrega cantidad =1.            
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    //Se usa para eliminar un articulo del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdArticulo(), item.getIdArticulo())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    //Se obtiene la informaciÃ³n de un articulo del carrito... para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdArticulo(), item.getIdArticulo())) {
                return i;
            }
        }
        return null;
    }

    //Se usa en la pÃ¡gina para actualizar la cantidad de articulos
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdArticulo(), item.getIdArticulo())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    /*En un futuro serÃ­a el proceso de generar la facturaciÃ³n...
    por ahora sÃ³lo borra los elementos del carrito */
    @Override
    public void facturar() {
        System.out.println("Facturando");
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getDescripcion() + 
                    " Cantidad: " + i.getCantidad() + 
                    " Total: " + i.getPrecio() * i.getCantidad()+
                    " Factura: "+i.getId_factura());

        }
        listaItems.clear();
    }
}
