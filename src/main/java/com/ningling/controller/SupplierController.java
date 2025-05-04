package com.ningling.controller;

import com.ningling.DTO.SupplierPageQueryDTO;
import com.ningling.Entity.Supplier;
import com.ningling.VO.PageResult;
import com.ningling.service.SupplierService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@Api("供应商相关接口")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/getSuppliersList")
    @ApiOperation("分页查询供应商")
    public Result<PageResult> getSuppliersList(@RequestBody SupplierPageQueryDTO s){
        PageResult suppliersList = supplierService.getSuppliersList(s);

        return Result.success(suppliersList);
    }

    @PutMapping("/updateSupplier")
    @ApiOperation("修改供应商")
    public Result updateSupplier(@RequestBody Supplier supplier){
        if (!supplierService.updateSupplier(supplier)) {
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteSupplier/{supplierId}")
    @ApiOperation("删除供应商")
    public Result deleteSupplier(@PathVariable Long supplierId){

        if(!supplierService.deleteSupplier(supplierId)){
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    @PostMapping("/createSupplier")
    @ApiOperation("新增供应商")
    public Result createSupplier(@RequestBody Supplier supplier){
        if(!supplierService.createSupplier(supplier)){
            return Result.error("创建失败");
        }
        return Result.success("创建成功");
    }
}
