package com.app.armetech.ajudae.usuario.infra;


//Interface usada pra receber as informações da requisição e entregá-las de maneira assíncrona.
public interface ReturnRequest<T> {
    void retrieveData(T value);
}
