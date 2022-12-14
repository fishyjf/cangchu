/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.springboot.model.dto.InventoryDTO;
import tk.mybatis.springboot.model.entity.Goods;
import tk.mybatis.springboot.model.entity.Inventory;
import tk.mybatis.springboot.util.MyMapper;

import java.util.List;

/**
 * @author liuzh_3nofxnp
 * @since 2016-01-22 22:17
 */
@Mapper
public interface InventoryMapper {
    int delete(Integer id);

    int insert(Inventory inventory);

    int insertDynamic(Inventory inventory);

    int updateDynamic(Inventory inventory);

    int update(Inventory inventory);

    Inventory selectById(Integer id);

    Inventory selectByGoodsId(Integer id);

    List<Inventory> findPageWithResult(InventoryDTO inventoryDTO);

    Integer findPageWithCount(InventoryDTO inventoryDTO);
}
