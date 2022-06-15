package com.myproject.manager.controller;

import com.myproject.manager.db.entity.Item;
import com.myproject.manager.vo.QueryParam;
import com.myproject.manager.vo.R;
import com.myproject.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myproject.manager.db.entity.Material;
import com.myproject.manager.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 Description: 这个类是对前端的操作进行映射的通信控制器
 */
/**一个vue前端示例，其method(类型)为post,所以会转入到PostMapping中,
 *   addItem(item) {
 *     return request({
 *       url: '/item',
 *       method: 'post',
 *       data: item
 *     })
 *   },
 **/
//这个似乎是负责拦截所有的前端的request的一个拦截器，只需要在这里设置拦截，前端返回的对应url会进入对应函数
@RestController
//下面这个确定request的根路径，在WebMVCConfig第二十行里面设置拦截器路径
@RequestMapping("/item")
@CrossOrigin
public class CommunicationController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;
    // 添加用户 对R本身进行操作
    @PostMapping("")
    public R insertItem(Item item) {
        return itemService.insertItem(item);
    }

    // 删除单个用户
    @DeleteMapping("{id}")
    public R deleteItem(@PathVariable(value = "id") String id) {
        return itemService.deleteItem(id);
    }




    // 删除多个用户
    @DeleteMapping("")
    public R deleteItemMore(@RequestBody List<String> ids) {
        return itemService.deleteItemMore(ids);
    }//页码

    // 编辑用户
    @PutMapping("")
    public R modifyItem(@RequestBody Item item) {
        return itemService.modifyItem(item);
    }

    // 查询用户列表 ps:这个就是那个大映射
    @PostMapping("{index}/{size}")
    public R findItemList(@PathVariable(value = "index") Integer index,
                          @PathVariable(value = "size") Integer size,
                          @RequestBody(required = false) QueryParam queryParam) {
        System.out.println("findItemList"+ queryParam.toString()+"index"+Integer.toString(index)+Integer.toString(size));
        return itemService.findItemList(index, size, queryParam);
    }
    //这里改过6.8

    // 根据用户编号查询用户信息
    @PostMapping("{id}")
    public R getItemInfo(@PathVariable(value = "id") String id) {
        return itemService.getItemInfoById(id);
    }

    @ResponseBody
    @GetMapping("/file/download")
    public R download(@RequestParam("id") Long id, HttpServletResponse res) throws IOException {
        System.out.println("id"+id.toString());
        Material material = this.fileService.getMaterial(id);
        String filename = material.getFileShortName();
        System.out.println("filename"+filename);
        String URI = material.getFileUri();
        System.out.println("URI"+URI);
        File file = new File(URI);
        String h = URLEncoder.encode(filename, "UTF-8");
        res.setHeader("Content-Disposition", "attachment;filename=" + h);
        res.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        this.fileService.download(file, res);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/file/upload")
    public R upload(@RequestParam("file") MultipartFile file) {
        Material ret = this.fileService.uploadFile(file);
        return R.ok().data("file",ret);
    }
}
