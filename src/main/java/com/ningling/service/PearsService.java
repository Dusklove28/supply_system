package com.ningling.service;

import com.ningling.Entity.Pears;
import com.ningling.VO.PearsListVO;

import java.util.List;

public interface PearsService {
    List<PearsListVO> getPearsList();

    PearsListVO getPearById(Long productId);

    boolean checkStockById(Long productId);


    boolean update(Pears pears, Long productId);

    List<PearsListVO>  searchPearsList(String keyword);

    List<PearsListVO> getProductsByCategory(Long classificationId);
}
