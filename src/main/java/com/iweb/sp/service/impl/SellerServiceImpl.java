package com.iweb.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iweb.sp.dao.SellerInfoDao;
import com.iweb.sp.dao.SkuCategoryDao;
import com.iweb.sp.dao.SkuCategoryItemDao;
import com.iweb.sp.dao.SkuDao;
import com.iweb.sp.pojo.SellerInfo;
import com.iweb.sp.pojo.Sku;
import com.iweb.sp.pojo.SkuCategory;
import com.iweb.sp.pojo.SkuCategoryItem;
import com.iweb.sp.pojo.vo.SkuAndCategory;
import com.iweb.sp.service.SellerService;
import com.iweb.sp.utils.FileUtil;
import com.iweb.sp.utils.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lukecheng
 * @date 2022/08/15
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerInfoDao sellerInfoDao;

    @Resource
    private SkuDao skuDao;


    @Resource
    private SkuCategoryDao skuCategoryDao;

    @Resource
    private SkuCategoryItemDao skuCategoryItemDao;

    /**
     *
     * @param sellerInfo 商家视图对象
     * @return boolean 判断是否注册成功
     */
    @Override
    public boolean register(SellerInfo sellerInfo, MultipartFile multipartFile,String cateName) {

        LambdaQueryWrapper<SellerInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SellerInfo::getPhone,sellerInfo.getPhone()).eq(SellerInfo::getPassword,sellerInfo.getPassword());
        SellerInfo seller = sellerInfoDao.selectOne(lqw);
        if(seller!=null){
            //已经注册过
            return false;
        }
        int count = sellerInfoDao.insert(sellerInfo);
        if(count!=1){
            return false;
        }

        LambdaQueryWrapper<SellerInfo> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(SellerInfo::getPhone,seller.getPhone());
        SellerInfo sellerInfo1 = sellerInfoDao.selectOne(lqw2);

        int id = sellerInfo1.getSellerId();
        String name = "SellerInfo" + id;
        sellerInfo.setAvatarImage(name);
        sellerInfoDao.update(sellerInfo,null);


        //添加商品分类
        SkuCategory skuCategory  = new SkuCategory();
        skuCategory.setSkuCategoryName(cateName);
        skuCategory.setSellerId(id);
        skuCategory.setCreateTime(new SimpleDateFormat().format(System.currentTimeMillis()));
        skuCategory.setUpdateTime(new SimpleDateFormat().format(System.currentTimeMillis()));
        skuCategoryDao.insert(skuCategory);

        boolean b = FileUtil.useOss(name, multipartFile);
        return b ;
    }

    @Override
    public SellerInfo loginByPassword(String phone, String password) {
        LambdaQueryWrapper<SellerInfo> lwq = new LambdaQueryWrapper<>();
        lwq.eq(SellerInfo::getPhone,phone).eq(SellerInfo::getPassword,password);
        SellerInfo sellerInfo = sellerInfoDao.selectOne(lwq);
        return sellerInfo;
    }

    @Override
    public String loginByMessage(String phone) {
        //生成四位数验证码
        String utilcode = "0123456789";
        StringBuilder code  = new StringBuilder();
        //生成随机数
        for(int i=1;i<=4;i++){
            int num = new Random().nextInt(10);
            code.append(utilcode.charAt(num));
        }
        String usercode = code.toString();
        try {
            SendMessage.SendMessageByali(phone,usercode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usercode;
    }

    /**
     *
     * @param sku 商品对象
     * @param skuCategory 商品分类对象
     * @param skuCategoryItem
     */
    @Override
    public void insertSkuBySeller(Sku sku, SkuCategory skuCategory, SkuCategoryItem skuCategoryItem) {
        //添加商品
        skuDao.insert(sku);
        //查询商品分类表得到 商品分类id
        LambdaQueryWrapper<SkuCategory> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SkuCategory::getSkuCategoryName,skuCategory.getSkuCategoryName());
        SkuCategory skuCateGory = skuCategoryDao.selectOne(lqw);
        //添加到商品分类详情表  -- 封装对象
        skuCategoryItem.setSkuCategoryId(skuCategory.getSkuCategoryId());
        skuCategoryItemDao.insert(skuCategoryItem);
    }


    /**
     *
     * @param skuCategory 商品分类对象
     */
    @Override
    public void insertSkuCategoryBySeller(SkuCategory skuCategory) {
        skuCategoryDao.insert(skuCategory);
    }



    /**
     *商家从后台根据名称查看商品(模糊查询)
     * @param skuName 商品名称
     * @return 商品集合
     */
    @Override
    public List<SkuAndCategory> selectSkuBySeller(String skuName) {
        return null;
    }

    /**
     * 商家删除指定商品
     * @param skuId 商品id
     * @param sellerId 商家id
     */
    @Override
    public void deleteSkuBySeller(Integer skuId,Integer sellerId) {
        LambdaQueryWrapper<Sku> lwq = new LambdaQueryWrapper<>();
        lwq.eq(Sku::getSellerId,sellerId).eq(Sku::getSkuId,skuId);
        skuDao.delete(lwq);
    }


    /**商家更改商品信息
     * @param sku 商品对象
     * @param skuCategory 商品分类对象
     * @param skucategoryId 修改之前的商品分类id
     */
    @Override
    public void updateSkuBySeller(Sku sku, SkuCategory skuCategory,String skucategoryId) {
        //更改商品信息
        skuDao.update(sku,null);
        LambdaQueryWrapper<SkuCategoryItem> lqw = new LambdaQueryWrapper<>();
        //根据商品id和分类id更改商品详细表信息
        lqw.eq(SkuCategoryItem::getSkuCategoryId,skucategoryId).eq(SkuCategoryItem::getSkuId,sku.getSkuId());
        SkuCategoryItem skuCategoryItem = skuCategoryItemDao.selectOne(lqw);
        skuCategoryItem.setSkuCategoryId(skuCategory.getSkuCategoryId());
        skuCategoryItem.setUpdateTime(sku.getUpdateTime());
        //更新分类详情表信息
        skuCategoryItemDao.update(skuCategoryItem,null);
    }

    /**
     * 商家点开全部商品，展示全部商品
     * @return 商品的分类的信息集合
     */
    @Override
    public List<SkuAndCategory> selectAllSku(Integer sellerId,Integer pageNum) {
        //根据用户id查询商品信息
        LambdaQueryWrapper<Sku> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Sku::getSellerId,sellerId);
        List<Sku> skus = skuDao.selectList(lqw);

        ArrayList<SkuAndCategory> showSkus = new ArrayList<>();;

        for (Sku sku : skus) {
            LambdaQueryWrapper<SkuCategoryItem> lqw2 = new LambdaQueryWrapper<>();
            //根据商品id查询商品分类详情表，找到对应的商品分类id
            lqw2.eq(SkuCategoryItem::getSkuId,sku.getSkuId());
            //同一个商品可能有多个分类
            List<SkuCategoryItem> skuCategoryItems = skuCategoryItemDao.selectList(lqw2);
            System.out.println(skuCategoryItems);

            for (SkuCategoryItem skuCategoryItem : skuCategoryItems) {
                //根据商品分类id去查商品分类表查询分类名
                LambdaQueryWrapper<SkuCategory> lqw3 = new LambdaQueryWrapper<>();
                lqw3.eq(SkuCategory::getSkuCategoryId,skuCategoryItem.getSkuCategoryId());
                SkuCategory skuCategory = skuCategoryDao.selectOne(lqw3);
                SkuAndCategory skuAndCategory = new SkuAndCategory();
                skuAndCategory.setSkuCategoryName(skuCategory.getSkuCategoryName());
                skuAndCategory.setSkuName(sku.getSkuName());
                skuAndCategory.setSkuDetail(sku.getSkuDetail());
                skuAndCategory.setSkuPrice(sku.getSkuPrice());
                skuAndCategory.setSkuStock(sku.getSkuStock());
                skuAndCategory.setSkuStatus(sku.getSkuStatus());
                showSkus.add(skuAndCategory);
            }
        }
        return selectAllSkuPage(pageNum,showSkus);
    }


    /**根据价格升序
     * @param sellerId 商家id
     * @param pageNum  页码
     * @return
     */
    @Override
    public List<SkuAndCategory> selectAllSkuASC(Integer sellerId ,Integer pageNum) {
        List<SkuAndCategory> skuAndCategories = selectAllSku(sellerId,pageNum);
        Collections.sort(skuAndCategories, new Comparator<SkuAndCategory>() {
            @Override
            public int compare(SkuAndCategory o1, SkuAndCategory o2) {
                return Double.compare(o1.getSkuPrice(),o2.getSkuPrice());
            }
        });
        return selectAllSkuPage(pageNum,skuAndCategories);
    }


    /**
     * 根据价格降序
     * @param sellerId 商家id
     * @param pageNum
     * @return
     */
    @Override
    public List<SkuAndCategory> selectAllSkuDESC(Integer sellerId ,Integer pageNum) {
        List<SkuAndCategory> skuAndCategories = selectAllSku(sellerId,pageNum);
        Collections.sort(skuAndCategories, new Comparator<SkuAndCategory>() {
            @Override
            public int compare(SkuAndCategory o1, SkuAndCategory o2) {
                return Double.compare(o2.getSkuPrice(),o1.getSkuPrice());
            }
        });
        //调用分页
        return selectAllSkuPage(pageNum,skuAndCategories);
    }


    /**
     *
     * @param pageNum 页码
     * @param skuAndCategories
     * @return 返回当前也得数据，一页6条数据
     */
    @Override
    public List<SkuAndCategory> selectAllSkuPage(Integer pageNum,List<SkuAndCategory> skuAndCategories) {
        List<SkuAndCategory> skuAndCategoriesDataPage = new ArrayList<>();
        for(int i =(pageNum-1)*3;i<pageNum*3 && i<skuAndCategories.size();i++){
            skuAndCategoriesDataPage.add(skuAndCategories.get(i));
        }
        return skuAndCategoriesDataPage;
    }

}
