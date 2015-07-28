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
import org.primefaces.model.SortOrder;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
@Named
public class GenericLazyDataModel<T extends AbstractBean, K> extends LazyDataModel<T> {

    @Inject
    private GenericService<T, K> genericService;

    private List<T> dataSource = new ArrayList();

    @PostConstruct
    public void init() {
    }

    @Override
    public Object getRowKey(T object) {
        return object.getId();
    }

    @Override
    public T getRowData(String rowKey) {
        for (T entity : dataSource) {
            if (rowKey.equalsIgnoreCase(entity.getId().toString())) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        boolean order = sortOrder.equals(SortOrder.ASCENDING);
        setRowCount(genericService.countAll());
        dataSource = genericService.findPage(first, pageSize, sortField, order, filters);
        return dataSource;
    }

}
