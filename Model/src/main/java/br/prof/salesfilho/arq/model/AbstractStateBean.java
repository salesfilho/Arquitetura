/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.model;

import java.util.Objects;

/**
 *
 * @author salesfilho
 * @param <K>
 */
public class AbstractStateBean<K>  extends AbstractBean<K>{
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.state);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractStateBean<?> other = (AbstractStateBean<?>) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }
 
}
