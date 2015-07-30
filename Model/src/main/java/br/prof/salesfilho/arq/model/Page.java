/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salesfilho
 * @param <T>
 */
public class Page<T> implements Serializable {
    private int rowCount;
    private List<T> rows;

    public Page() {
        this.rowCount = 0;
        this.rows = new ArrayList();
    }
    public Page(int rowCount, List<T> rows) {
        this.rowCount = rowCount;
        this.rows = rows;
    }

    
    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    
}
