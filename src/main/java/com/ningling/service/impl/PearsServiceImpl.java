package com.ningling.service.impl;

import com.ningling.Entity.Pears;
import com.ningling.VO.PearsListVO;
import com.ningling.mapper.PearsMapper;
import com.ningling.service.PearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PearsServiceImpl implements PearsService {

    @Autowired
    private PearsMapper pearsMapper;
    @Override
    public List<PearsListVO> getPearsList() {
        List<Pears> pearsList = pearsMapper.getPearsList();
        List<PearsListVO> plv = new ArrayList<>();
        for (Pears p : pearsList) {
            PearsListVO pearsListVO = PearsListVO.builder()
                    .id(p.getProductId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .price(p.getPrice())
                    .imgUrl(p.getImageUrl())
                    .build();
            plv.add(pearsListVO);
        }
        return plv;
    }

    @Override
    public PearsListVO getPearById(Long productId) {

        Pears p = pearsMapper.getPearById(productId);

        PearsListVO pV = PearsListVO.builder()
                .id(p.getProductId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .imgUrl(p.getImageUrl())
                .build();


        return pV;
    }

    @Override
    public boolean checkStockById(int productId) {
        if(pearsMapper.checkStockById(productId) > 0){
            return true;
        }
        return false;
    }
}
