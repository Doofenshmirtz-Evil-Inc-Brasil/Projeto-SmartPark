package br.edu.smartpark.repositorio;
import java.util.*;
public class RepositorioEmMemoria<T> {
    private final Map<String,T> dados=new HashMap<>();
    public void salvar(String id,T valor){dados.put(id,valor);}
    public T buscar(String id){return dados.get(id);}
    public Collection<T> todos(){return dados.values();}
}
