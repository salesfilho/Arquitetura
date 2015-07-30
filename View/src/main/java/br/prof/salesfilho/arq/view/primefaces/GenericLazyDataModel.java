/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.view.primefaces;

import br.prof.salesfilho.arq.model.AbstractBean;
import br.prof.salesfilho.arq.service.GenericService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
@Named
public class GenericLazyDataModel<T extends AbstractBean, K> extends LazyDataModel<T> implements SelectableDataModel<T> {

    @Inject
    private GenericService<T, K> genericService;

    private List<T> datasource = null;

    @PostConstruct
    public void init() {
        datasource = new ArrayList();
    }

    @Override
    public Object getRowKey(T object) {
        return object.getId();
    }

    @Override
    public T getRowData(String rowKey) {
        for (T entity : datasource) {
            if (rowKey.equalsIgnoreCase(entity.getId().toString())) {
                return entity;
            }
        }
        return null;
    }

    public int getAtualRowCount() {
        return datasource.size();
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = loadData(first, pageSize, sortField, sortOrder, filters);
        return datasource;
    }

    private List<T> loadData(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Boolean order = sortOrder.equals(SortOrder.ASCENDING);
        if (!filters.isEmpty()) {
            this.setRowCount(genericService.countPage(first, pageSize, sortField, order, filters));
        } else {
            this.setRowCount(genericService.countAll());
        }
        return genericService.findPage(first, pageSize, sortField, order, filters);
    }

}
