package store.WEB;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import store.POJO.Goods;
import store.Service.GoodsService;
import store.Service.Message;

@RestController
@CrossOrigin
@RequestMapping("/savegood")
public class savegoods {
	
	@Value("${goods.image}") private String path;
	
	@Autowired
	GoodsService gs;
	@Transactional
	@PostMapping
	public Message savegood(@RequestParam String form,@RequestParam MultipartFile image) throws IOException {
		//前台发送的JSON字符串做转换
		Goods good=JSON.parseObject(form, Goods.class);
		//创建消息对象
		Message mes=new Message();
		//保证保存的商品图片的根路径先创建出来
		File goodfile=new File(path);
		if(!goodfile.exists()) {
			goodfile.mkdir();
		}
		//图片文件保存的真实路径
		String readpath=path+"/"+image.getOriginalFilename();
		File gf=new File(readpath);
		//执行写入
		if(!gf.exists()) {
			gf.createNewFile();
			image.transferTo(gf);
		}
		//设置其为映射路径后再将其持久化到数据库
		good.setImages("/goodsimg/"+image.getOriginalFilename());
		gs.save(good);
		mes.setMessage("上传成功");
		return mes;
	}
}
