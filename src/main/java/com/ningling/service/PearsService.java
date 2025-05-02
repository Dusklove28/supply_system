package com.ningling.service;

import com.ningling.Entity.Pears;
import com.ningling.VO.PearsListVO;

import java.util.List;

public interface PearsService {
    List<PearsListVO> getPearsList();

    PearsListVO getPearById(Long productId);

    boolean checkStockById(int productId);
}
