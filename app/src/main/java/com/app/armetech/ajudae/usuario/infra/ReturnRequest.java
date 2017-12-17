package com.app.armetech.ajudae.usuario.infra;

/**
 * Created by erickapsilva on 16/12/2017.
 */

public interface ReturnRequest<T> {
    void retrieveData(T value);
}
