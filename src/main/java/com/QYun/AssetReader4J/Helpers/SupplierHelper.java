package com.QYun.AssetReader4J.Helpers;

import java.io.IOException;

@FunctionalInterface
public interface SupplierHelper<T> {
    T get() throws IOException;
}
