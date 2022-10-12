package tk.mybatis.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.GoodsMapper;
import tk.mybatis.springboot.model.bo.GoodsBO;
import tk.mybatis.springboot.model.dto.GoodsDTO;
import tk.mybatis.springboot.model.entity.Goods;
import tk.mybatis.springboot.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<GoodsBO> getAll(GoodsDTO goodsDTO) {
        if (goodsDTO.getStart() != null && goodsDTO.getPageSize() != null) {
            PageHelper.startPage(goodsDTO.getStart(), goodsDTO.getPageSize());
        }
        return goodsMapper.findPageWithResult(goodsDTO);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public int updGoods(Goods goods) {
        return goodsMapper.update(goods);
    }

    public Goods getById(Integer id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        goodsMapper.delete(id);
    }
}
