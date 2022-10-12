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

package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.mybatis.springboot.model.dto.InventoryDTO;
import tk.mybatis.springboot.model.entity.Inventory;
import tk.mybatis.springboot.service.InventoryService;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping
    public ModelAndView getAll(Inventory inventory) {
        ModelAndView result = new ModelAndView("intoOutInfo");
        InventoryDTO inventoryDTO = new InventoryDTO();
        BeanUtils.copyProperties(inventory, inventoryDTO);
        inventoryDTO.setStart(inventory.getPage() - 1);
        inventoryDTO.setPageSize(inventory.getRows());
        List<Inventory> inventoryList = inventoryService.getAll(inventoryDTO);
        result.addObject("pageInfo", new PageInfo<Inventory>(inventoryList));
        result.addObject("queryParam", inventory);
        result.addObject("page", inventory.getPage());
        result.addObject("rows", inventory.getRows());
        return result;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView("view");
        Inventory inventory = inventoryService.getById(id);
        result.addObject("inventory", inventory);
        return result;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id, RedirectAttributes ra) {
        ModelAndView result = new ModelAndView("redirect:/inventory");
        inventoryService.deleteById(id);
        ra.addFlashAttribute("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/putGoods", method = RequestMethod.POST)
    public ModelAndView putGoods(Inventory inventory, RedirectAttributes ra) {
        ModelAndView result = new ModelAndView("redirect:/goods");
        int flag = inventoryService.addInventory(inventory);
        String msg = flag == 1 ? "添加库存成功!" : "添加库存失败!";
        ra.addFlashAttribute("msg", msg);
        return result;
    }

    @RequestMapping(value = "/outGoods", method = RequestMethod.POST)
    public ModelAndView outGoods(Inventory inventory, RedirectAttributes ra) {
        ModelAndView result = new ModelAndView("redirect:/goods");
        int flag = inventoryService.minusInventory(inventory);
        String msg = flag == 1 ? "出库成功!" : "出库失败!";
        ra.addFlashAttribute("msg", msg);
        return result;
    }
}
