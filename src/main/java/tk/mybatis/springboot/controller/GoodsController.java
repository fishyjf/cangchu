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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.mybatis.springboot.model.bo.GoodsBO;
import tk.mybatis.springboot.model.dto.GoodsDTO;
import tk.mybatis.springboot.model.entity.Goods;
import tk.mybatis.springboot.service.GoodsService;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping
    public ModelAndView getAll(Goods goods) {
        ModelAndView result = new ModelAndView("index");
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goods, goodsDTO);
        goodsDTO.setStart(goods.getPage());
        goodsDTO.setPageSize(goods.getRows());
        List<GoodsBO> goodsList = goodsService.getAll(goodsDTO);
        result.addObject("pageInfo", new PageInfo<GoodsBO>(goodsList));
        result.addObject("queryParam", goods);
        result.addObject("page", goods.getPage());
        result.addObject("rows", goods.getRows());
        return result;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView result = new ModelAndView("view");
        result.addObject("goods", new Goods());
        return result;
    }

    @RequestMapping(value = "/put/{id}")
    public ModelAndView put(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView("put");
        Goods goods = goodsService.getById(id);
        result.addObject("goods", goods);
        return result;
    }

    @RequestMapping(value = "/out/{id}")
    public ModelAndView out(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView("out");
        Goods goods = goodsService.getById(id);
        result.addObject("goods", goods);
        return result;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView("view");
        Goods goods = goodsService.getById(id);
        result.addObject("goods", goods);
        return result;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id, RedirectAttributes ra) {
        ModelAndView result = new ModelAndView("redirect:/goods");
        goodsService.deleteById(id);
        ra.addFlashAttribute("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(Goods goods) {
        ModelAndView result = new ModelAndView("view");
        String msg = goods.getId() == null ? "新增成功!" : "更新成功!";
        if (goods.getId() == null) {
            goodsService.addGoods(goods);
        }else {
            goodsService.updGoods(goods);
        }
        result.addObject("goods", goods);
        result.addObject("msg", msg);
        return result;
    }
}
