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
    public boolean checkStockById(Long productId) {
        if(pearsMapper.checkStockById(productId) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Pears pears, Long productId) {
        pears.setProductId(productId);
        pearsMapper.update(pears);
        return false;
    }

    @Override
    public List<PearsListVO> searchPearsList(String keyword) {
        List<Pears> pearsList = pearsMapper.searchPearsList(keyword);
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
    public List<PearsListVO> getProductsByCategory(Long classificationId) {
        List<Pears> pearsList = pearsMapper.getProductsByCategory(classificationId);
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
}
