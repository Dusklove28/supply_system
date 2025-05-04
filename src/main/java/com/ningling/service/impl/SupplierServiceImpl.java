package com.ningling.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ningling.DTO.SupplierPageQueryDTO;
import com.ningling.Entity.Supplier;
import com.ningling.VO.PageResult;
import com.ningling.VO.SuppilerPageQueryVO;
import com.ningling.mapper.SupplierMapper;
import com.ningling.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;


    @Override
    public PageResult getSuppliersList(SupplierPageQueryDTO s) {
        PageHelper.startPage(s.getPageNum(),s.getPageSize());
        Page<SuppilerPageQueryVO> page = supplierMapper.getSuppliersList(s);
        PageResult pageResult = PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();

        return pageResult;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        supplier.setUpdatedTime(LocalDateTime.now());

        if (supplierMapper.updateSupplier(supplier) <= 0) {
            throw new IllegalArgumentException("修改失败");
        }

        return true;
    }

    @Override
    public boolean deleteSupplier(Long supplierId) {


        if (  supplierMapper.deleteSupplier(supplierId) <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean createSupplier(Supplier supplier) {
        supplier.setCreatedTime(LocalDateTime.now());
        supplier.setUpdatedTime(LocalDateTime.now());
        if (supplierMapper.createSupplier(supplier) <=0) {
            return false;
        }
        return true;
    }
}
