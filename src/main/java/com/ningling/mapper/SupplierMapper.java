package com.ningling.mapper;



import com.github.pagehelper.Page;
import com.ningling.DTO.SupplierPageQueryDTO;
import com.ningling.Entity.Supplier;
import com.ningling.VO.SuppilerPageQueryVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupplierMapper {
    Page<SuppilerPageQueryVO> getSuppliersList(SupplierPageQueryDTO s);

    int updateSupplier(Supplier supplier);

    @Delete("delete from suppliers where supplier_id = #{supplierId}")
    int deleteSupplier(Long supplierId);


    int createSupplier(Supplier supplier);
}
