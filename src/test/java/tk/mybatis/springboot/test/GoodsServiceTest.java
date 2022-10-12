package tk.mybatis.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.springboot.Application;
import tk.mybatis.springboot.model.dto.GoodsDTO;
import tk.mybatis.springboot.model.entity.Goods;
import tk.mybatis.springboot.service.GoodsService;

import java.util.List;

/**
 * @author liuzh
 * @since 2017/9/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void test() {
        GoodsDTO goodsDTO = new GoodsDTO();
//        List<Goods> all = goodsService.getAll(goodsDTO);
//        for (Goods c : all) {
//            System.out.println(c.getId());
//        }
    }
}
