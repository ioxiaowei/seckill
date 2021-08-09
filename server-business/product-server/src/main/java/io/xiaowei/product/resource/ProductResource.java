package io.xiaowei.product.resource;

import io.xiaowei.model.ProductModel;
import io.xiaowei.product.service.IProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangxiaowei
 * @apiNote Resource
 **/
@RestController
@RequestMapping("product")
public class ProductResource {

    @Resource
    private IProductService iProductService;

    @PostMapping("save")
    public ProductModel save(@RequestBody ProductModel productModel) {
        return iProductService.save(productModel);
    }

    @GetMapping("all")
    public List<ProductModel> all() {
        return iProductService.all();
    }

    /**
     * 对外OpenFeign
     * @param id
     * @return
     */
    @PostMapping("find/{id}")
    public ProductModel findById(@PathVariable(value = "id") Long id) {
        return iProductService.findById(id);
    }

    /**
     * 对外OpenFeign
     *
     * @param shopId
     * @return
     */
    @PostMapping("shop/list/{shopId}")
    public List<ProductModel> shopList(@PathVariable(value = "shopId") Long shopId) {
        return iProductService.shopList(shopId);
    }
}
