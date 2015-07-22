/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.model;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author salesfilho
 */
public class State implements Serializable {

    private Map<Object, Object> state;

    public State(Map<Object, Object> state) {
        this.state = state;
    }

    public Map<Object, Object> getState() {
        return state;
    }

    public void setState(Map<Object, Object> state) {
        this.state = state;
    }

    public void addState(Object key, Object value) {
        this.state.put(key, value);
    }

    public void removeState(Object key) {
        this.state.remove(key);
    }

    public void getState(Object key) {
        this.state.get(key);
    }

}
