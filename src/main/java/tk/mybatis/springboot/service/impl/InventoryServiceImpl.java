package tk.mybatis.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.springboot.enums.IntoOutTypeEnum;
import tk.mybatis.springboot.enums.InventoryStatusEnum;
import tk.mybatis.springboot.mapper.IntoOutInfoMapper;
import tk.mybatis.springboot.mapper.InventoryMapper;
import tk.mybatis.springboot.model.dto.InventoryDTO;
import tk.mybatis.springboot.model.entity.IntoOutInfo;
import tk.mybatis.springboot.model.entity.Inventory;
import tk.mybatis.springboot.service.InventoryService;

import java.util.List;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private IntoOutInfoMapper intoOutInfoMapper;

    @Override
    public List<Inventory> getAll(InventoryDTO inventoryDTO) {
        return inventoryMapper.findPageWithResult(inventoryDTO);
    }

    @Override
    public int addInventory(Inventory inventory) {
        Inventory ivt = inventoryMapper.selectByGoodsId(inventory.getGoodsId());
        // 插入日志
        IntoOutInfo intoOutInfo = new IntoOutInfo();
        intoOutInfo.setGoodsId(inventory.getGoodsId());
        intoOutInfo.setNumber(inventory.getNumber());
        intoOutInfo.setType(IntoOutTypeEnum.PUT.getValue());
        intoOutInfoMapper.insert(intoOutInfo);
        // 开始入库
        if (ivt == null) {
            inventory.setStatus(InventoryStatusEnum.YES.getValue());
            return inventoryMapper.insert(inventory);
        }else {
            ivt.setNumber(ivt.getNumber() + inventory.getNumber());
            return inventoryMapper.update(ivt);
        }
    }

    @Override
    public int minusInventory(Inventory inventory) {
        Inventory ivt = inventoryMapper.selectByGoodsId(inventory.getGoodsId());
        // 插入日志
        IntoOutInfo intoOutInfo = new IntoOutInfo();
        intoOutInfo.setGoodsId(inventory.getGoodsId());
        intoOutInfo.setNumber(inventory.getNumber());
        intoOutInfo.setType(IntoOutTypeEnum.OUT.getValue());
        intoOutInfoMapper.insert(intoOutInfo);
        // 开始出库
        if (ivt == null || InventoryStatusEnum.matchCode(ivt.getStatus()).equals(InventoryStatusEnum.NO)){
            return 0;
        }
        if (ivt.getNumber() < inventory.getNumber()){
            return -1;
        }
        ivt.setNumber(ivt.getNumber() - inventory.getNumber());
        return inventoryMapper.update(ivt);
    }

    @Override
    public Inventory getByGoodsId(Integer goodsId) {
        return inventoryMapper.selectByGoodsId(goodsId);
    }

    public Inventory getById(Integer id) {
        return inventoryMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        inventoryMapper.delete(id);
    }
}
