package com.br.wishlist.app.usecases;

public interface IRemoveProduct {
    void execute(String customerId, String productId);
}
