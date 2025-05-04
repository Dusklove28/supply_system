package com.ningling.service;


import com.ningling.DTO.SupplierPageQueryDTO;
import com.ningling.Entity.Supplier;
import com.ningling.VO.PageResult;

public interface SupplierService {
    PageResult getSuppliersList(SupplierPageQueryDTO s);

    boolean updateSupplier(Supplier supplier);

    boolean deleteSupplier(Long supplierId);

    boolean createSupplier(Supplier supplier);
}
