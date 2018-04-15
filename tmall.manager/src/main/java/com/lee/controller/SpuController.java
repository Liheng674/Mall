package com.lee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lee.bean.T_MALL_PRODUCT;
import com.lee.service.SpuService;
import com.lee.util.MyFileUpload;

@Controller
public class SpuController {
	@Autowired
	private SpuService spuService;
	
	@RequestMapping("/goSpuAdd")
	public String goSpuAdd(Model model, T_MALL_PRODUCT tmp) {
		model.addAttribute("T_MALL_PRODUCT", tmp);
		return "spuAdd";
	}
	
	/**
	* @Title: get_spu_list
	* @Description: TODO(调用spu列表异步方法->传递品牌和二级分类id->查询spu列表->返回json->页面加载json数据)
	* @param @param pp_id
	* @param @param flbh2
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/get_spu_list")
	public Object get_spu_list(int pp_id, int flbh2) {
		List<T_MALL_PRODUCT> list =  spuService.get_spu_list(pp_id,flbh2);
		return list;
	}
	
	/**
	* @Title: spuAdd
	* @Description: TODO(添加spu信息)
	* @param @param img_cover
	* @param @param tmp
	* @param @param files
	* @param @return    参数
	* @return ModelAndView    返回类型
	* @throws
	 */
	@RequestMapping("/spuAdd")
	public ModelAndView spuAdd(@RequestParam("img_cover") Integer img_cover, T_MALL_PRODUCT tmp, @RequestParam("files")MultipartFile[] files) {
		// 上传文件
//		List<String> list = MyFileUpload.myUpload(files);
		// 保存数据
//		spuService.insertIntoProduct(tmp, list, img_cover);
		ModelAndView mv = new ModelAndView("redirect:/index.do");//goSpuAdd.do
//		mv.addObject("flbh1", tmp.getFlbh1());
//		mv.addObject("flbh2", tmp.getFlbh2());
//		mv.addObject("pp_id", tmp.getPp_id());
		// 跳转到main页面再加载之前的页面, 传入之前的url和title
		mv.addObject("url", "goSpuAdd.do?flbh1="+tmp.getFlbh1()+"&flbh2="+tmp.getFlbh2()+"&pp_id="+tmp.getPp_id());
		mv.addObject("title", "添加商品信息");
		return mv;
	}
}
